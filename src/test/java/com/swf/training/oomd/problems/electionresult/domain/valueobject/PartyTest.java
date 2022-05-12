package com.swf.training.oomd.problems.electionresult.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PartyTest {

    @Test
    void shouldBeAbleToSayTrueForValidParty() {
        String partyCode = "BJP";
        assertTrue(Party.isValidPartyCode(partyCode));
    }

    @Test
    void shouldBeAbleToSayFalseForInvalidParty() {
        String partyCode = "ABC";
        assertFalse(Party.isValidPartyCode(partyCode));
    }

    @Test
    void shouldBeAbleToGetFullName() {
        String expected = "Independent";
        assertEquals(expected, Party.IND.getFullName());
    }
}
