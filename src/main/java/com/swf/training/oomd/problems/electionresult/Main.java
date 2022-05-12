package com.swf.training.oomd.problems.electionresult;

import com.swf.training.oomd.problems.electionresult.domain.service.ElectionResultCalculator;
import com.swf.training.oomd.problems.electionresult.factory.PartyFactory;
import com.swf.training.oomd.problems.electionresult.formatter.ResultFormatter;
import com.swf.training.oomd.problems.electionresult.formatter.impl.ElectionResultFormatter;
import com.swf.training.oomd.problems.electionresult.parser.InputParser;
import com.swf.training.oomd.problems.electionresult.parser.impl.ElectionDataParser;
import com.swf.training.oomd.problems.electionresult.printer.ResultPrinter;
import com.swf.training.oomd.problems.electionresult.printer.impl.ElectionResultPrinter;
import com.swf.training.oomd.problems.electionresult.reader.InputReader;
import com.swf.training.oomd.problems.electionresult.reader.impl.FileInputReader;
import com.swf.training.oomd.problems.electionresult.repositories.ConstituencyRepository;
import com.swf.training.oomd.problems.electionresult.repositories.impl.InMemoryConstituencyRepository;
import com.swf.training.oomd.problems.electionresult.runner.Runner;
import com.swf.training.oomd.problems.electionresult.service.ElectionService;

import java.io.IOException;

import static com.swf.training.oomd.problems.electionresult.db.InMemoryConstituencyDB.getConstituencyDB;

public class Main {

    public static void main(String[] args) {

        InputReader inputReader = new FileInputReader();

        InputParser electionDataParser = new ElectionDataParser();

        ElectionResultCalculator electionResultCalculator = new ElectionResultCalculator();

        PartyFactory partyFactory = new PartyFactory();

        ConstituencyRepository constituencyRepository = InMemoryConstituencyRepository.getConstituencyRepo(getConstituencyDB());

        ElectionService electionService = new ElectionService(electionResultCalculator, partyFactory, constituencyRepository);

        ResultFormatter electionResultFormatter = new ElectionResultFormatter();

        ResultPrinter electionResultPrinter = new ElectionResultPrinter();

        Runner runner = new Runner(
                inputReader,
                electionDataParser,
                constituencyRepository,
                electionService,
                electionResultFormatter,
                electionResultPrinter
        );

        try {
            runner.run(args[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
