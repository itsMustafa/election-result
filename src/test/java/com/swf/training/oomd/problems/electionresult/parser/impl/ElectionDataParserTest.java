package com.swf.training.oomd.problems.electionresult.parser.impl;

import com.swf.training.oomd.problems.electionresult.dto.RawElectionDataDTO;
import com.swf.training.oomd.problems.electionresult.parser.impl.ElectionDataParser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElectionDataParserTest {

    @Test
    void shouldBeAbleToParseInputDTO() {
        List<String> lines = new ArrayList<>(List.of(
                "myConstituency1-myParty1=100,myParty2=200",
                "myConstituency2-myParty1=100,myParty2=200"
        ));

        String[] parties1 = {"myParty1=100", "myParty2=200"};

        List<RawElectionDataDTO> expected = new ArrayList<>(List.of(
                new RawElectionDataDTO("myConstituency1", parties1),
                new RawElectionDataDTO("myConstituency2", parties1)
        ));

        ElectionDataParser electionDataParser = new ElectionDataParser();
        List<RawElectionDataDTO> actual = electionDataParser.parseInputDTO(lines);

        assertEquals(expected, actual);
    }
}
