package ru.learnup.nexigntask.callscdrplus.parsers.contracts;

import java.io.File;
import java.io.IOException;

public interface Parser {

    void parseFile(File file) throws IOException;
}
