package com.swf.training.oomd.problems.electionresult.formatter.impl;

import com.swf.training.oomd.problems.electionresult.dto.ElectionResultDTO;
import com.swf.training.oomd.problems.electionresult.formatter.ResultFormatter;

import java.util.List;

public class ElectionResultFormatter implements ResultFormatter {

    @Override
    public String format(List<ElectionResultDTO> electionResultDTOS) {

        String HEADER = "Constituency,\tWinning Party,\tVote Share";
        String NEWLINE = "\n";
        String COMMA = ",";
        String TAB = "\t";

        StringBuilder stringBuilder = new StringBuilder(HEADER);

        electionResultDTOS.forEach(electionResult -> stringBuilder.append(NEWLINE).append(electionResult.getConstituency()).append(COMMA).append(TAB)
                        .append(electionResult.getParty().getFullName()).append(COMMA).append(TAB)
                        .append(String.format("%.1f", electionResult.getVotesShare()))
        );

        return stringBuilder.toString();
    }
}
