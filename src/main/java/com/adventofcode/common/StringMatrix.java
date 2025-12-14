package com.adventofcode.common;

import java.util.ArrayList;
import java.util.List;

public class StringMatrix implements Matrix<String> {
    private final List<String> lines = new ArrayList<>();
    
    public StringMatrix(String string) {
        string.lines().forEach(line -> {
            lines.add(line);
            if (line.length() != lines.getFirst().length()) {
                throw new IllegalArgumentException("Provided string does not represent Matrix! " +
                        "Line number " + lines.size() + " has length of " + line.length() +
                        " while the first line has length " + lines.getFirst().length());
            }
        });
    }

    private StringMatrix(List<String> lines) {
        this.lines.addAll(lines);
    }

    public StringMatrix(StringMatrix other) {
        this(other.lines);
    }

    @Override
    public int width() {
        return lines.isEmpty() ? 0 : lines.getFirst().length();
    }

    @Override
    public int height() {
        return lines.isEmpty() ? 0 : lines.size();
    }

    public String at(Pair<Integer> position) {
        return at(position.a(), position.b());
    }

    @Override
    public String at(int x, int y) {
        if (x < 0 || x >= width() || y < 0 || y >= height()) {
            return null;
        }
        return String.valueOf(lines.get(y).charAt(x));
    }

    @Override
    public void set(int x, int y, String value) {
        if (value.length() != 1) {
            throw new IllegalArgumentException("value length must be 1");
        }
        String line = lines.get(y);
        lines.set(y, line.substring(0, x) + value + line.substring(x + 1));
    }

    public StringMatrix rotate() {
        List<String> newLines = new ArrayList<>();
        for (int x = 0; x < this.width(); x++) {
            StringBuilder line = new StringBuilder();
            for (int y = this.height() - 1; y >= 0; y--) {
                line.append(lines.get(y).charAt(x));
            }
            newLines.add(line.toString());
        }
        return new StringMatrix(newLines);
    }

    public StringMatrix flipHorizontally() {
        return new StringMatrix(lines.reversed());
    }

    public StringMatrix flipVertically() {
        List<String> newLines = lines.stream().map(l -> new StringBuilder(l).reverse().toString()).toList();
        return new StringMatrix(newLines);
    }

    @Override
    public String toString() {
        return String.join("\n", lines);
    }
}
