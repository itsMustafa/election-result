package com.swf.training.oomd.problems.electionresult.formatter;

import com.swf.training.oomd.problems.electionresult.dto.ElectionResultDTO;

import java.util.List;

public interface ResultFormatter {

    String format(List<ElectionResultDTO> electionResultDTO);

}
