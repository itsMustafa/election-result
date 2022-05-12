package com.swf.training.oomd.problems.electionresult.parser.impl;

import com.swf.training.oomd.problems.electionresult.dto.RawElectionDataDTO;
import com.swf.training.oomd.problems.electionresult.parser.InputParser;

import java.util.ArrayList;
import java.util.List;

public class ElectionDataParser implements InputParser {

    private RawElectionDataDTO generateRawInputDTO(String line) {

        String[] stringArr = line.split("-");
        String rawConstituency = stringArr[0];
        String partyAndVotesList = stringArr[1];
        String[] rawPartyAndVotes = partyAndVotesList.split(",");

        return new RawElectionDataDTO(rawConstituency, rawPartyAndVotes);

    }

    @Override
    public List<RawElectionDataDTO> parseInputDTO(List<String> lines) {
        List<RawElectionDataDTO> rawElectionDataDTOS = new ArrayList<>();

        lines.forEach(line -> rawElectionDataDTOS.add(
                        generateRawInputDTO(line)
                )
        );

        return rawElectionDataDTOS;
    }

}
