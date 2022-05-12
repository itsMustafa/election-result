package com.swf.training.oomd.problems.electionresult.runner;

import com.swf.training.oomd.problems.electionresult.db.InMemoryConstituencyDB;
import com.swf.training.oomd.problems.electionresult.domain.service.ElectionResultCalculator;
import com.swf.training.oomd.problems.electionresult.factory.PartyFactory;
import com.swf.training.oomd.problems.electionresult.formatter.ResultFormatter;
import com.swf.training.oomd.problems.electionresult.formatter.impl.ElectionResultFormatter;
import com.swf.training.oomd.problems.electionresult.parser.impl.ElectionDataParser;
import com.swf.training.oomd.problems.electionresult.printer.ResultPrinter;
import com.swf.training.oomd.problems.electionresult.printer.impl.ElectionResultPrinter;
import com.swf.training.oomd.problems.electionresult.reader.InputReader;
import com.swf.training.oomd.problems.electionresult.reader.impl.FileInputReader;
import com.swf.training.oomd.problems.electionresult.repositories.ConstituencyRepository;
import com.swf.training.oomd.problems.electionresult.repositories.impl.InMemoryConstituencyRepository;
import com.swf.training.oomd.problems.electionresult.runner.Runner;
import com.swf.training.oomd.problems.electionresult.service.ElectionService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RunnerTest {

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

    private String getExpectedResult() {
        String expected = "Constituency,\tWinning Party,\tVote Share";
        expected += "\nJaipur,\tBhartiya Janta Party,\t48.8";
        expected += "\nBangalore,\tIndian National Congress,\t49.7";
        expected += "\nPune,\tBhartiya Janta Party,\t44.1";

        return expected;
    }

    @Test
    void testRun() throws IOException {

        InputReader inputReader = new FileInputReader();

        ElectionDataParser electionDataParser = new ElectionDataParser();

        ElectionResultCalculator electionResultCalculator = new ElectionResultCalculator();

        PartyFactory partyFactory = new PartyFactory();

        InMemoryConstituencyDB constituencyDB = InMemoryConstituencyDB.getConstituencyDB();

        ConstituencyRepository constituencyRepository = InMemoryConstituencyRepository.getConstituencyRepo(constituencyDB);

        ElectionService electionService = new ElectionService(electionResultCalculator, partyFactory, constituencyRepository);

        ResultFormatter electionResultFormatter = new ElectionResultFormatter();

        ResultPrinter electionResultPrinter = new ElectionResultPrinter();

        Runner runner = new Runner(inputReader, electionDataParser, constituencyRepository, electionService, electionResultFormatter, electionResultPrinter);

        runner.run("src/test/resources/input.txt");

        assertEquals(getExpectedResult(), newOut.toString());
    }
}

