package com.adventofcode;

import com.adventofcode.common.Pair;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class TestUtils {
    public static String readFile(String path) {
        try {
            Path filePath = Paths.get(Objects.requireNonNull(TestUtils.class.getResource(path)).toURI());
            return Files.readString(filePath);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> getColumn(String path, int columnIndex, final String delimiter) {
        String input = readFile(path);
        return getColumnFromInput(input, columnIndex, delimiter);
    }

    public static List<List<String>> getColumns(String path) {
        List<List<String>> columns = new ArrayList<>();
        final String input = readFile(path).replaceAll("[^\\S\\r\\n]+", " ").lines().map(String::trim).collect(Collectors.joining("\n"));
        int indices = input.lines().limit(1).mapToInt(l -> l.split(" ").length).sum();
        IntStream.range(0, indices).forEach(i -> columns.add(getColumnFromInput(input, i, " ")));
        return columns;
    }

    public static List<Integer> getIntColumn(String path, int columnIndex, String delimiter) {
        return new ArrayList<>(getColumn(path, columnIndex, delimiter).stream().map(Integer::valueOf).toList());
    }

    public static List<Integer> getIntColumn(String path, int columnIndex) {
        return getIntColumn(path, columnIndex, "\\s+");
    }

    public static List<Long> getLongColumn(String path, int columnIndex, String delimiter) {
        return new ArrayList<>(getColumn(path, columnIndex, delimiter).stream().map(Long::valueOf).toList());
    }

    public static List<Long> getLongColumn(String path, int columnIndex) {
        return getLongColumn(path, columnIndex, "\\s+");
    }

    private static List<String> getColumnFromInput(String input, int columnIndex, final String delimiter) {
        return input.lines().map(line -> line.split(delimiter)[columnIndex]).toList();
    }

    public static List<String> getLines(String path) {
        return readFile(path).lines().toList();
    }

    public static List<List<String>> getRows(String path, String delimiter) {
        String input = readFile(path);
        List<List<String>> result = new ArrayList<>();
        input.lines().forEach(line -> result.add(Arrays.asList(line.split(delimiter))));
        return result;
    }

    public static List<List<Integer>> getIntRows(String path, String delimiter) {
        return new ArrayList<>(
                getRows(path, delimiter).stream()
                        .map(line -> new ArrayList<>(
                                line.stream().map(Integer::parseInt).toList()
                        ))
                        .toList()
        );
    }

    /**
     * @return Lists of lists integers - in each line these integers are split by whitespaces
     */
    public static List<List<Integer>> getIntRows(String path) {
        return getIntRows(path, "\\s+");
    }

    public static List<Pair<Integer>> getIntPairs(String path, String delimiter) {
        return new ArrayList<>(
                readFile(path).lines()
                        .map(line -> new Pair<>(parseInt(line.split(delimiter)[0]), parseInt(line.split(delimiter)[1])))
                        .toList()
        );
    }

    public static List<Pair<Long>> getLongPairs(String path, String delimiter) {
        return new ArrayList<>(
                readFile(path).lines()
                        .map(line -> new Pair<>(parseLong(line.split(delimiter)[0]), parseLong(line.split(delimiter)[1])))
                        .toList()
        );
    }
}
