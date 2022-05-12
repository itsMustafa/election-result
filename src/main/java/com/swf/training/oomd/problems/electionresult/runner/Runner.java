package com.swf.training.oomd.problems.electionresult.runner;

import com.swf.training.oomd.problems.electionresult.dto.ElectionResultDTO;
import com.swf.training.oomd.problems.electionresult.dto.RawElectionDataDTO;
import com.swf.training.oomd.problems.electionresult.error.InvalidConstituency;
import com.swf.training.oomd.problems.electionresult.error.InvalidParty;
import com.swf.training.oomd.problems.electionresult.error.InvalidPartyAndVotesDTO;
import com.swf.training.oomd.problems.electionresult.error.InvalidVotes;
import com.swf.training.oomd.problems.electionresult.formatter.ResultFormatter;
import com.swf.training.oomd.problems.electionresult.parser.InputParser;
import com.swf.training.oomd.problems.electionresult.printer.ResultPrinter;
import com.swf.training.oomd.problems.electionresult.reader.InputReader;
import com.swf.training.oomd.problems.electionresult.repositories.ConstituencyRepository;
import com.swf.training.oomd.problems.electionresult.service.ElectionService;

import java.io.IOException;
import java.util.List;

public class Runner {

    private final InputReader inputReader;
    private final InputParser electionDataParser;
    private final ConstituencyRepository constituencyRepository;
    private final ElectionService electionService;
    private final ResultFormatter electionResultFormatter;
    private final ResultPrinter electionResultPrinter;

    public Runner(InputReader inputReader, InputParser electionDataParser, ConstituencyRepository constituencyRepository, ElectionService electionService, ResultFormatter electionResultFormatter, ResultPrinter electionResultPrinter) {
        this.inputReader = inputReader;
        this.electionDataParser = electionDataParser;
        this.constituencyRepository = constituencyRepository;
        this.electionService = electionService;
        this.electionResultFormatter = electionResultFormatter;
        this.electionResultPrinter = electionResultPrinter;
    }

    public void run(String filePath) throws IOException {

        List<String> lines = inputReader.read(filePath);

        List<RawElectionDataDTO> rawElectionDatumDTOS = electionDataParser.parseInputDTO(lines);

        generateConstituency(electionService, rawElectionDatumDTOS);

        List<ElectionResultDTO> electionsElectionResultDTOS = electionService.findResult(constituencyRepository.getConstituencies());

        printResult(formatResult(electionsElectionResultDTOS), electionResultPrinter);

    }

    private String formatResult(List<ElectionResultDTO> electionResultDTOS) {
        return electionResultFormatter.format(electionResultDTOS);
    }

    private void printResult(String electionResult, ResultPrinter electionResultPrinter) {
        electionResultPrinter.printResult(electionResult);
    }

    private void generateConstituency(ElectionService electionService, List<RawElectionDataDTO> rawElectionDatumDTOS) {
        rawElectionDatumDTOS.forEach(rawInputDTO -> {
            try {
                electionService.addConstituency(rawInputDTO);
            } catch (InvalidConstituency | InvalidParty | InvalidVotes | InvalidPartyAndVotesDTO e) {
                e.printStackTrace();
            }
        });
    }
}
