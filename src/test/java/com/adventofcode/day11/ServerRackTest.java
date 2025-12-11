package com.adventofcode.day11;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static com.adventofcode.TestUtils.getLines;
import static org.assertj.core.api.Assertions.assertThat;

class ServerRackTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideFirstInput")
    void firstTestCase(String description, List<String> lines, long expected) {
        // given
        ServerRack solution = new ServerRack();

        // when
        long result = solution.countPaths(mapLinesToGraph(lines), "you", "out", Collections.emptyList());

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideSecondInput")
    void secondTestCase(String description, List<String> lines, long expected) {
        // given
        ServerRack solution = new ServerRack();

        // when
        long result = solution.countPaths(mapLinesToGraph(lines), "svr", "out", List.of("dac", "fft"));

        // then
        assertThat(result).isEqualTo(expected);
    }

    private Map<String, Device> mapLinesToGraph(List<String> lines) {
        Map<String, Device> result = new HashMap<>();
        lines.stream()
                .map(ServerRackTest::mapLineToDevice)
                .forEach(device -> result.put(device.name(), device));
        return result;
    }

    private static Device mapLineToDevice(String line) {
        return new Device(line.split(":")[0].trim(), Arrays.stream(line.split(" ")).skip(1).toList());
    }

    private static Stream<Arguments> provideFirstInput() {
        return Stream.of(
                Arguments.of("0.in", getLines("/day11/0.in"), 5),
                Arguments.of("1.in", getLines("/day11/1.in"), 701)
        );
    }

    private static Stream<Arguments> provideSecondInput() {
        return Stream.of(
                Arguments.of("0.in", getLines("/day11/0b.in"), 2),
                Arguments.of("1.in", getLines("/day11/1.in"), 390108778818526L)
        );
    }
}