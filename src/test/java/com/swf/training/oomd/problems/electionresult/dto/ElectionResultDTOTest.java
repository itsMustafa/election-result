package com.swf.training.oomd.problems.electionresult.dto;

import com.swf.training.oomd.problems.electionresult.domain.valueobject.Party;
import com.swf.training.oomd.problems.electionresult.error.InvalidConstituency;
import com.swf.training.oomd.problems.electionresult.error.InvalidParty;
import com.swf.training.oomd.problems.electionresult.error.InvalidVoteShare;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ElectionResultDTOTest {

    @Test
    void shouldBeAbleToCreateResult() throws InvalidParty, InvalidVoteShare, InvalidConstituency {
        ElectionResultDTO electionResultDTO = new ElectionResultDTO("constituency1", Party.IND, 48);
        assertFalse(electionResultDTO.getConstituency().isEmpty());
    }

    @Test
    void shouldBeAbleToGetConstituency() throws InvalidParty, InvalidVoteShare, InvalidConstituency {
        ElectionResultDTO electionResultDTO = new ElectionResultDTO("Constituency1", Party.IND, 48);
        String expectedConstituency = "Constituency1";
        assertEquals(expectedConstituency, electionResultDTO.getConstituency());
    }

    @Test
    void shouldBeAbleToGetParty() throws InvalidParty, InvalidVoteShare, InvalidConstituency {
        ElectionResultDTO electionResultDTO = new ElectionResultDTO("Constituency1", Party.IND, 48);
        Party expectedParty = Party.IND;
        assertEquals(expectedParty, electionResultDTO.getParty());
    }

    @Test
    void shouldBeAbleToVoteShare() throws InvalidParty, InvalidVoteShare, InvalidConstituency {
        ElectionResultDTO electionResultDTO = new ElectionResultDTO("constituency1", Party.IND, 48.0);
        double expectedVoteShare = 48.0;
        assertEquals(expectedVoteShare, electionResultDTO.getVotesShare());
    }

    @Test
    void shouldThrowInvalidConstituencyWhenEmpty() {
        assertThrows(InvalidConstituency.class, () -> new ElectionResultDTO("", Party.IND, 48.0));
    }

    @Test
    void shouldThrowInvalidPartyWhenNull() {
        assertThrows(InvalidParty.class, () -> new ElectionResultDTO("Constituency", null, 48.0));
    }

    @Test
    void shouldThrowInvalidVoteShareWhenLessThanZero() {
        assertThrows(InvalidVoteShare.class, () -> new ElectionResultDTO("Constituency", Party.IND, -1));
    }

    @Test
    void similarObjectsShouldHaveSameHashcode() throws InvalidVoteShare, InvalidParty, InvalidConstituency {
        ElectionResultDTO result1 = new ElectionResultDTO("Constituency1", Party.IND, 48);
        ElectionResultDTO result2 = new ElectionResultDTO("Constituency1", Party.IND, 48);

        assertEquals(result1.hashCode(), result2.hashCode());
    }

    @Test
    void shouldAbleToSayEqualsForSameObjectOnly() throws InvalidVoteShare, InvalidParty, InvalidConstituency {
        ElectionResultDTO result = new ElectionResultDTO("Constituency1", Party.IND, 48);

        assertEquals(result, result);
        assertNotEquals(null, result);
    }
}
