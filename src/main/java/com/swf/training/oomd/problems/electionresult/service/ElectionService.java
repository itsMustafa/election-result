package com.swf.training.oomd.problems.electionresult.service;

import com.swf.training.oomd.problems.electionresult.domain.service.ElectionResultCalculator;
import com.swf.training.oomd.problems.electionresult.domain.entity.Constituency;
import com.swf.training.oomd.problems.electionresult.domain.valueobject.Party;
import com.swf.training.oomd.problems.electionresult.domain.valueobject.VotesShare;
import com.swf.training.oomd.problems.electionresult.dto.ElectionResultDTO;
import com.swf.training.oomd.problems.electionresult.dto.RawElectionDataDTO;
import com.swf.training.oomd.problems.electionresult.error.*;
import com.swf.training.oomd.problems.electionresult.factory.PartyFactory;
import com.swf.training.oomd.problems.electionresult.repositories.ConstituencyRepository;

import java.util.ArrayList;
import java.util.List;

public class ElectionService {

    private final ElectionResultCalculator electionResultCalculator;
    private final PartyFactory partyFactory;
    private final ConstituencyRepository constituencyRepository;

    public ElectionService(ElectionResultCalculator electionResultCalculator, PartyFactory partyFactory, ConstituencyRepository constituencyRepository) {
        this.electionResultCalculator = electionResultCalculator;
        this.partyFactory = partyFactory;
        this.constituencyRepository = constituencyRepository;
    }

    private Party generateParty(String rawParty) throws InvalidParty {
        return partyFactory.createFrom(rawParty);
    }

    private List<VotesShare> getVotesShares(RawElectionDataDTO rawElectionDataDTO) throws InvalidParty, InvalidVotes {
        List<VotesShare> votesShares = new ArrayList<>();

        for (String partyAndVotes : rawElectionDataDTO.getPartyAndVotes()) {
            String[] partyVotesSplit = partyAndVotes.split("=");
            votesShares.add(new VotesShare(generateParty(partyVotesSplit[0]), Integer.parseInt(partyVotesSplit[1])));
        }

        return votesShares;
    }

    public void addConstituency(RawElectionDataDTO rawElectionDataDTO) throws InvalidConstituency, InvalidParty, InvalidVotes, InvalidPartyAndVotesDTO {
        constituencyRepository.add(
                new Constituency(rawElectionDataDTO.getConstituency(), getVotesShares(rawElectionDataDTO))
        );
    }

    public List<ElectionResultDTO> findResult(List<Constituency> constituencies) {
        List<ElectionResultDTO> electionElectionResultDTOS = new ArrayList<>();

        constituencies.forEach(
                constituency -> {
                    try {
                        electionElectionResultDTOS.add(
                                new ElectionResultDTO(constituency.getName(), electionResultCalculator.getPartyWithMaxVotes(constituency).getParty(), electionResultCalculator.getVotesShare(constituency))
                        );
                    } catch (InvalidConstituency | InvalidParty | InvalidVoteShare e) {
                        e.printStackTrace();
                    }
                }
        );

        return electionElectionResultDTOS;
    }
}
