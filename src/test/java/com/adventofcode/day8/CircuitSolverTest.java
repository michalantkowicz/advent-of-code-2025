package com.adventofcode.day8;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.adventofcode.TestUtils.readFile;
import static org.assertj.core.api.Assertions.assertThat;

class CircuitSolverTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideFirstInput")
    void firstTestCase(String description, String input, long limit, long expected) {
        // given
        CircuitSolver solution = new CircuitSolver();

        List<Position> positions = input.lines()
                .map(l -> new Position(
                                Long.parseLong(l.split(",")[0]),
                                Long.parseLong(l.split(",")[1]),
                                Long.parseLong(l.split(",")[2])
                        )
                ).toList();

        // when
        long result = solution.resolve(new ArrayList<>(positions), limit);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideSecondInput")
    void secondTestCase(String description, String input, long expected) {
        // given
        CircuitSolver solution = new CircuitSolver();

        List<Position> positions = input.lines()
                .map(l -> new Position(
                                Long.parseLong(l.split(",")[0]),
                                Long.parseLong(l.split(",")[1]),
                                Long.parseLong(l.split(",")[2])
                        )
                ).toList();

        // when
        long result = solution.resolveLongest(new ArrayList<>(positions));

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideFirstInput() {
        return Stream.of(
                Arguments.of("0.in", readFile("/day8/0.in"), 10, 40),
                Arguments.of("1.in", readFile("/day8/1.in"), 1000, 50568)
        );
    }

    private static Stream<Arguments> provideSecondInput() {
        return Stream.of(
                Arguments.of("0.in", readFile("/day8/0.in"), 25272),
                Arguments.of("1.in", readFile("/day8/1.in"), 36045012)
        );
    }
}