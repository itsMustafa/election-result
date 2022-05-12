package com.swf.training.oomd.problems.electionresult.printer.impl;

import com.swf.training.oomd.problems.electionresult.printer.ResultPrinter;

public class ElectionResultPrinter implements ResultPrinter {

    @Override
    public void printResult(String electionResult) {
        System.out.print(electionResult);
    }
}
