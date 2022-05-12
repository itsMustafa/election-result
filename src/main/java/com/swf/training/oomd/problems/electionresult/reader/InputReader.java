package com.swf.training.oomd.problems.electionresult.reader;

import java.io.IOException;
import java.util.List;

public interface InputReader {

    List<String> read(String fileName) throws IOException;

}
