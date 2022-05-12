package com.swf.training.oomd.problems.electionresult.parser;

import com.swf.training.oomd.problems.electionresult.dto.RawElectionDataDTO;

import java.util.List;

public interface InputParser {

    List<RawElectionDataDTO> parseInputDTO(List<String> lines);

}
