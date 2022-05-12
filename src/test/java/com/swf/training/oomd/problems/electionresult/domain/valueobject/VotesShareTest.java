package com.swf.training.oomd.problems.electionresult.domain.valueobject;

import com.swf.training.oomd.problems.electionresult.error.InvalidParty;
import com.swf.training.oomd.problems.electionresult.error.InvalidVotes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VotesShareTest {

    @Test
    void shouldBeAbleToCreatePartyAndVotesDTO() throws InvalidParty, InvalidVotes {
        VotesShare votesShare = new VotesShare(Party.CPI, 100);
        assertTrue(votesShare.getVotes() > 0);
    }

    @Test
    void shouldBeAbleToGetParty() throws InvalidParty, InvalidVotes {
        VotesShare votesShare = new VotesShare(Party.CPI, 100);
        Party expected = Party.CPI;
        assertEquals(expected, votesShare.getParty());
    }

    @Test
    void shouldBeAbleToGetVotes() throws InvalidParty, InvalidVotes {
        VotesShare votesShare = new VotesShare(Party.CPI, 100);
        int expected = 100;
        assertEquals(expected, votesShare.getVotes());
    }

    @Test
    void shouldThrowInvalidPartyWhenEmpty() {
        assertThrows(InvalidParty.class, () -> new VotesShare(null, 100));
    }

    @Test
    void shouldThrowInvalidVotesWhenLessThanZero() {
        assertThrows(InvalidVotes.class, () -> new VotesShare(Party.IND, -10));
    }

    @Test
    void shouldBeAbleToCheckEquality() throws InvalidParty, InvalidVotes {
        VotesShare votesShare1 = new VotesShare(Party.IND, 100);
        VotesShare votesShare2 = new VotesShare(Party.IND, 100);

        assertEquals(votesShare1, votesShare2);

    }

    @Test
    void similarObjectsShouldHaveSameHashcode() throws InvalidParty, InvalidVotes {
        VotesShare votesShare1 = new VotesShare(Party.IND, 100);
        VotesShare votesShare2 = new VotesShare(Party.IND, 100);

        assertEquals(votesShare1.hashCode(), votesShare2.hashCode());
    }

    @Test
    void shouldAbleToSayEqualsForSameObjectOnly() throws InvalidParty, InvalidVotes {
        VotesShare votesShare = new VotesShare(Party.IND, 100);

        assertEquals(votesShare, votesShare);
        assertNotEquals(null, votesShare);
    }
}
