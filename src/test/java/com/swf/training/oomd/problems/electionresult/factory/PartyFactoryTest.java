package com.swf.training.oomd.problems.electionresult.factory;

import com.swf.training.oomd.problems.electionresult.domain.valueobject.Party;
import com.swf.training.oomd.problems.electionresult.error.InvalidParty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PartyFactoryTest {

    PartyFactory partyFactory;

    @BeforeEach
    void setUp() {
        partyFactory = new PartyFactory();
    }

    @AfterEach
    void tearDown() {
        partyFactory = null;
    }

    @Test
    void shouldBeAbleToCreatePartyFromValidRawInput() throws InvalidParty {
        Party party = partyFactory.createFrom("IND");
        assertEquals(Party.IND, party);
    }

    @Test
    void shouldThrowInvalidPartyWhenInvalidRawInputGiven() {
        assertThrows(InvalidParty.class, () -> partyFactory.createFrom("XYZ"));
    }
}
