package com.swf.training.oomd.problems.electionresult.printer.impl;

import com.swf.training.oomd.problems.electionresult.domain.valueobject.Party;
import com.swf.training.oomd.problems.electionresult.dto.ElectionResultDTO;
import com.swf.training.oomd.problems.electionresult.error.InvalidConstituency;
import com.swf.training.oomd.problems.electionresult.error.InvalidParty;
import com.swf.training.oomd.problems.electionresult.error.InvalidVoteShare;
import com.swf.training.oomd.problems.electionresult.printer.ResultPrinter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ElectionResultPrinterTest {

    static PrintStream oldOut;
    static ByteArrayOutputStream newOut;

    @BeforeAll
    static void setUp() {
        oldOut = System.out;
        newOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOut));
    }

    @AfterAll
    static void tearDown() {
        System.setOut(oldOut);
    }

    @Test
    void shouldBeAbleToPrintWinningParty() {
        String result = "Constituency, \tWinning Party, \tVote Share\nmyConstituency,\tIndependent,\t50.0";
        ResultPrinter electionResultPrinter = new ElectionResultPrinter();
        electionResultPrinter.printResult(result);

        assertEquals(result, newOut.toString());

    }
}

