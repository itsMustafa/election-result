package com.swf.training.oomd.problems.electionresult.reader.impl;

import com.swf.training.oomd.problems.electionresult.reader.InputReader;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InputReaderTest {

    @Test
    void shouldBeAbleToReadInputFile() throws IOException {
        List<String> expected = List.of("Hello World!");

        InputReader inputReader = new FileInputReader();
        List<String> lines = inputReader.read("src/test/resources/TestInput.txt");

        assertEquals(expected, lines);
    }

    @Test
    void shouldThrowIOExceptionWhenNoFileFound() {
        assertThrows(FileNotFoundException.class, () -> new FileInputReader().read("NoAnyFile.txt"));
    }
}
