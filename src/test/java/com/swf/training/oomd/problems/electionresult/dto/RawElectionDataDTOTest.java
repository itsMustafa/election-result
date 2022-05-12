package com.swf.training.oomd.problems.electionresult.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RawElectionDataDTOTest {

    @Test
    void shouldBeAbleToCreateRawInput() {
        String[] partyAndVotes = {"BJP=300", "NCP=200", "CPI=100"};
        RawElectionDataDTO rawElectionDataDTO = new RawElectionDataDTO("Constituency1", partyAndVotes);
        assertTrue(rawElectionDataDTO.getPartyAndVotes().length > 0);
    }

    @Test
    void shouldBeAbleToGetConstituency() {
        String[] partyAndVotes = {"BJP=300", "NCP=200", "CPI=100"};
        RawElectionDataDTO rawElectionDataDTO = new RawElectionDataDTO("Constituency1", partyAndVotes);

        String expected = "Constituency1";

        assertEquals(expected, rawElectionDataDTO.getConstituency());
    }

    @Test
    void shouldBeAbleToGetPartyAndVotes() {
        String[] partyAndVotes = {"BJP=300", "NCP=200", "CPI=100"};
        RawElectionDataDTO rawElectionDataDTO = new RawElectionDataDTO("Constituency1", partyAndVotes);

        String[] expectedPartyAndVotes = {"BJP=300", "NCP=200", "CPI=100"};

        assertArrayEquals(expectedPartyAndVotes, rawElectionDataDTO.getPartyAndVotes());
    }

    @Test
    void shouldBeAbleToCheckEquality() {
        String[] partyAndVotes = {"BJP=300", "NCP=200", "CPI=100"};
        RawElectionDataDTO rawElectionDataDTO1 = new RawElectionDataDTO("Constituency1", partyAndVotes);

        RawElectionDataDTO rawElectionDataDTO2 = new RawElectionDataDTO("Constituency1", partyAndVotes);

        assertEquals(rawElectionDataDTO1, rawElectionDataDTO2);

    }

    @Test
    void shouldHaveSameHashcodeForTheObjects() {
        String[] partyAndVotes = {"BJP=300", "NCP=200", "CPI=100"};
        RawElectionDataDTO rawElectionDataDTO1 = new RawElectionDataDTO("Constituency1", partyAndVotes);

        RawElectionDataDTO rawElectionDataDTO2 = new RawElectionDataDTO("Constituency1", partyAndVotes);

        assertEquals(rawElectionDataDTO1.hashCode(), rawElectionDataDTO2.hashCode());
    }

    @Test
    void shouldBeAbleToSayEqualsForSameObjectsOnly() {
        String[] partyAndVotes = {"BJP=300", "NCP=200", "CPI=100"};
        RawElectionDataDTO rawElectionDataDTO = new RawElectionDataDTO("Constituency1", partyAndVotes);

        assertEquals(rawElectionDataDTO, rawElectionDataDTO);
        assertNotEquals(null, rawElectionDataDTO);
    }
}
