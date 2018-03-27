package net.devstudy.interpreter.component.impl;

import net.devstudy.interpreter.component.SignificantLineVerifier;
import net.devstudy.interpreter.component.SourceLineReader;
import net.devstudy.interpreter.model.SourceLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SourceLineReaderImpl implements SourceLineReader {

    private final SignificantLineVerifier significantLineVerifier;

    public SourceLineReaderImpl(SignificantLineVerifier significantLineVerifier) {
        this.significantLineVerifier = significantLineVerifier;
    }

    @Override
    public SourceLine[] read(String fileName) {
        try {
            List<String> list = Files.readAllLines(Paths.get(fileName));
            List<SourceLine> result = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                String line = list.get(i);
                if (significantLineVerifier.isSignificant(line)) {
                    result.add(new SourceLine(line, i + 1));
                }
            }
            return result.toArray(new SourceLine[result.size()]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
