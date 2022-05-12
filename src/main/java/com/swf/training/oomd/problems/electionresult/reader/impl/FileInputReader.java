package com.swf.training.oomd.problems.electionresult.reader.impl;

import com.swf.training.oomd.problems.electionresult.reader.InputReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileInputReader implements InputReader {

    @Override
    public List<String> read(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }

        lines.removeIf(String::isEmpty);
        return lines;
    }
}
