package com.swf.training.oomd.problems.electionresult.domain.entity;

import com.swf.training.oomd.problems.electionresult.domain.entity.Constituency;
import com.swf.training.oomd.problems.electionresult.domain.valueobject.Party;
import com.swf.training.oomd.problems.electionresult.domain.valueobject.VotesShare;
import com.swf.training.oomd.problems.electionresult.error.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.swf.training.oomd.problems.electionresult.builder.PartyAndVotesBuilder.partyAndVotesDTO;
import static org.junit.jupiter.api.Assertions.*;

public class ConstituencyTest {

    @Test
    void shouldBeAbleToCreateConstituency() throws InvalidParty, InvalidVotes, InvalidPartyAndVotesDTO, InvalidConstituency {

        List<VotesShare> votesShares = new ArrayList<>(List.of(
                partyAndVotesDTO(Party.BJP, 100),
                partyAndVotesDTO(Party.BSP, 100),
                partyAndVotesDTO(Party.CPI, 100)
        ));

        Constituency constituency = new Constituency("myConstituency", votesShares);

        assertFalse(constituency.getName().isEmpty());
    }

    @Test
    void shouldBeAbleToGetName() throws InvalidParty, InvalidVotes, InvalidPartyAndVotesDTO, InvalidConstituency {
        List<VotesShare> votesShares = new ArrayList<>(List.of(
                partyAndVotesDTO(Party.BJP, 100),
                partyAndVotesDTO(Party.BSP, 100),
                partyAndVotesDTO(Party.CPI, 100)
        ));

        Constituency constituency = new Constituency("myConstituency", votesShares);

        assertEquals("myConstituency", constituency.getName());
    }

    @Test
    void shouldBeAbleToGetPartyAndVotes() throws InvalidParty, InvalidVotes, InvalidPartyAndVotesDTO, InvalidConstituency {
        List<VotesShare> votesShares = new ArrayList<>(List.of(
                partyAndVotesDTO(Party.BJP, 100),
                partyAndVotesDTO(Party.BSP, 100),
                partyAndVotesDTO(Party.CPI, 100)
        ));

        Constituency constituency = new Constituency("myConstituency", votesShares);

        assertEquals(votesShares, constituency.getPartyAndVotes());
    }

    @Test
    void shouldThrowInvalidConstituencyWhenNameIsEmpty() throws InvalidParty, InvalidVotes {
        List<VotesShare> votesShares = new ArrayList<>(List.of(
                partyAndVotesDTO(Party.BJP, 100),
                partyAndVotesDTO(Party.BSP, 100),
                partyAndVotesDTO(Party.CPI, 100)
        ));

        assertThrows(InvalidConstituency.class, () -> new Constituency("", votesShares));
    }

    @Test
    void shouldThrowInvalidPartyAndVotesDTOWhenListIsEmpty() {
        assertThrows(InvalidPartyAndVotesDTO.class, () -> new Constituency("Pune", new ArrayList<VotesShare>()));
    }

    @Test
    void similarObjectsShouldHaveSameHashcode() throws InvalidParty, InvalidVotes, InvalidPartyAndVotesDTO, InvalidConstituency {
        List<VotesShare> votesShares = new ArrayList<>(List.of(
                partyAndVotesDTO(Party.BJP, 100),
                partyAndVotesDTO(Party.BJP, 100)
        ));

        Constituency constituency1 = new Constituency("myConstituency1", votesShares);

        Constituency constituency2 = new Constituency("myConstituency1", votesShares);

        assertEquals(constituency1.hashCode(), constituency2.hashCode());

    }

    @Test
    void shouldAbleToSayEqualsForSameObjectOnly() throws InvalidParty, InvalidConstituency, InvalidPartyAndVotesDTO, InvalidVotes {
        List<VotesShare> votesShares = new ArrayList<>(List.of(
                partyAndVotesDTO(Party.BJP, 100),
                partyAndVotesDTO(Party.BJP, 100)
        ));

        Constituency constituency = new Constituency("myConstituency1", votesShares);

        assertEquals(constituency, constituency);
        assertNotEquals(null, constituency);
    }
}
