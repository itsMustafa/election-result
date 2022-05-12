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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ElectionServiceTest {

    @Mock
    ConstituencyRepository constituencyRepository;

    @Mock
    PartyFactory partyFactory;

    @Mock
    ElectionResultCalculator electionResultCalculator;

    @InjectMocks
    ElectionService electionService;

    @Test
    void shouldBeAbleToAddConstituency() throws InvalidParty, InvalidPartyAndVotesDTO, InvalidVotes, InvalidConstituency {
        String[] partiesAndVotes = {"IND=100", "CPI=200"};

        RawElectionDataDTO rawElectionDataDTO = new RawElectionDataDTO("myConstituency1", partiesAndVotes);

        when(partyFactory.createFrom("IND")).thenReturn(Party.IND);
        when(partyFactory.createFrom("CPI")).thenReturn(Party.CPI);

        electionService.addConstituency(rawElectionDataDTO);

        verify(constituencyRepository, times(1)).add(any());

    }

    @Test
    void shouldBeAbleToFindResult() throws InvalidParty, InvalidVotes, InvalidPartyAndVotesDTO, InvalidConstituency, InvalidVoteShare {
        List<VotesShare> votesShares = new ArrayList<>(List.of(
                new VotesShare(Party.IND, 100),
                new VotesShare(Party.CPI, 200)
        ));

        List<Constituency> constituencies = new ArrayList<>(List.of(
                new Constituency("myConstituency", votesShares)
        ));

        when(electionResultCalculator.getPartyWithMaxVotes(any())).thenReturn(new VotesShare(Party.IND, 100));
        when(electionResultCalculator.getVotesShare(any())).thenReturn(50.0);

        List<ElectionResultDTO> electionResultDTOS = List.of(new ElectionResultDTO("myConstituency", Party.IND, 50.0));

        List<ElectionResultDTO> actual = electionService.findResult(constituencies);

        assertEquals(electionResultDTOS, actual);
    }

}
