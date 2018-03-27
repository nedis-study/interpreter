package net.devstudy.interpreter.model;

public class SourceLine {
    private final String line;
    private final int number;

    public SourceLine(String line, int number) {
        this.line = line;
        this.number = number;
    }

    public String getLine() {
        return line;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "SourceLine{" +
                "line='" + line + '\'' +
                ", number=" + number +
                '}';
    }
}
