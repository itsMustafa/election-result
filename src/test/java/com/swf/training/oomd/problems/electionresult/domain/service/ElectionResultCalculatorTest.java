package com.swf.training.oomd.problems.electionresult.domain.service;

import com.swf.training.oomd.problems.electionresult.domain.entity.Constituency;
import com.swf.training.oomd.problems.electionresult.domain.valueobject.Party;
import com.swf.training.oomd.problems.electionresult.domain.valueobject.VotesShare;
import com.swf.training.oomd.problems.electionresult.error.InvalidConstituency;
import com.swf.training.oomd.problems.electionresult.error.InvalidParty;
import com.swf.training.oomd.problems.electionresult.error.InvalidPartyAndVotesDTO;
import com.swf.training.oomd.problems.electionresult.error.InvalidVotes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.swf.training.oomd.problems.electionresult.builder.PartyAndVotesBuilder.partyAndVotesDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElectionResultCalculatorTest {

    ElectionResultCalculator electionResultCalculator;

    @BeforeEach
    void setUp() {
        electionResultCalculator = new ElectionResultCalculator();
    }

    @AfterEach()
    void tearDown() {
        electionResultCalculator = null;
    }

    @Test
    void shouldGetMaxVotesParty() throws InvalidParty, InvalidVotes, InvalidPartyAndVotesDTO, InvalidConstituency {

        List<VotesShare> votesShares = new ArrayList<>(
                List.of(
                        partyAndVotesDTO(Party.BJP, 100),
                        partyAndVotesDTO(Party.BSP, 200),
                        partyAndVotesDTO(Party.CPI, 300)
                )
        );

        Constituency constituency = new Constituency("Constituency1", votesShares);

        VotesShare expected = partyAndVotesDTO(Party.CPI, 300);

        assertEquals(expected, electionResultCalculator.getPartyWithMaxVotes(constituency));
    }

    @Test
    void shouldBeAbleToGetVoteShare() throws InvalidParty, InvalidVotes, InvalidPartyAndVotesDTO, InvalidConstituency {
        List<VotesShare> votesShares = new ArrayList<>(
                List.of(
                        partyAndVotesDTO(Party.BJP, 100),
                        partyAndVotesDTO(Party.BSP, 200),
                        partyAndVotesDTO(Party.CPI, 300)
                )
        );

        Constituency constituency = new Constituency("Constituency1", votesShares);

        double expected = 50.0;

        assertEquals(expected, electionResultCalculator.getVotesShare(constituency));
    }
}
