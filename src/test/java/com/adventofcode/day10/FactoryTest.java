package com.adventofcode.day10;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.adventofcode.TestUtils.getRows;
import static com.adventofcode.TestUtils.readFile;
import static org.assertj.core.api.Assertions.assertThat;

class FactoryTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideFirstInput")
    void firstTestCase(String description, List<List<String>> rows, long expected) {
        // given
        Factory solution = new Factory();

        List<Machine> machines = rows.stream().map(r -> {
            List<List<Integer>> buttons = r.subList(1, r.size() - 1).stream()
                    .map(b -> Arrays.stream(b.substring(1, b.length() - 1).split(","))
                            .map(Integer::parseInt)
                            .toList())
                    .toList();
            return new Machine(new LightDiagram(r.getFirst()), buttons);
        }).toList();

        // when
        long result = solution.resolve(machines);

        // then
        assertThat(result).isEqualTo(expected);
    }

//    @ParameterizedTest(name = "{0}")
//    @MethodSource("provideSecondInput")
//    void secondTestCase(String description, String input, long expected) {
//        // given
//        Factory solution = new Factory();
//
//        // when
//        long result = solution.resolve(input);
//
//        // then
//        assertThat(result).isEqualTo(expected);
//    }

    private static Stream<Arguments> provideFirstInput() {
        return Stream.of(
                Arguments.of("0.in", getRows("/day10/0.in", " "), 7),
                Arguments.of("1.in", getRows("/day10/1.in", " "), -1L)
        );
    }

    private static Stream<Arguments> provideSecondInput() {
        return Stream.of(
                Arguments.of("0.in", readFile("/day10/0.in"), -1L),
                Arguments.of("1.in", readFile("/day10/1.in"), -1L)
        );
    }
}