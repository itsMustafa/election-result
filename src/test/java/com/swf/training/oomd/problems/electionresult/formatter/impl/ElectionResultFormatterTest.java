package com.swf.training.oomd.problems.electionresult.formatter.impl;

import com.swf.training.oomd.problems.electionresult.domain.valueobject.Party;
import com.swf.training.oomd.problems.electionresult.dto.ElectionResultDTO;
import com.swf.training.oomd.problems.electionresult.error.InvalidConstituency;
import com.swf.training.oomd.problems.electionresult.error.InvalidParty;
import com.swf.training.oomd.problems.electionresult.error.InvalidVoteShare;
import com.swf.training.oomd.problems.electionresult.formatter.ResultFormatter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ElectionResultFormatterTest {

    @Test
    void shouldAbleToFormatElectionResultFromDTO() throws InvalidVoteShare, InvalidParty, InvalidConstituency {
        List<ElectionResultDTO> electionResultDTOS = List.of(new ElectionResultDTO("myConstituency", Party.IND, 50.0));
        String expected = "Constituency,\tWinning Party,\tVote Share\nmyConstituency,\tIndependent,\t50.0";
        ResultFormatter electionResultFormatter = new ElectionResultFormatter();

        String actual = electionResultFormatter.format(electionResultDTOS);

        assertEquals(expected, actual);
    }

}