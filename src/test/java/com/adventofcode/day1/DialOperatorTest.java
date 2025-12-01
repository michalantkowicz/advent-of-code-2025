package com.adventofcode.day1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.adventofcode.TestUtils.readFile;
import static org.assertj.core.api.Assertions.assertThat;

class DialOperatorTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideFirstInput")
    void checkResultWhenStopsAtZero(String description, String input, long expected) {
        // given
        DialOperator dialOperator = new DialOperator();

        // when
        long result = dialOperator.countStopAtZero(parseRotations(input));

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideSecondInput")
    void checkResultWhenPassingZero(String description, String input, long expected) {
        // given
        DialOperator dialOperator = new DialOperator();

        // when
        long result = dialOperator.countPassingZero(parseRotations(input));

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static List<Rotation> parseRotations(String input) {
        return input.lines().map(l -> new Rotation(l.startsWith("R"), Long.parseLong(l.substring(1)))).toList();
    }

    private static Stream<Arguments> provideFirstInput() {
        return Stream.of(
                Arguments.of("0.in", readFile("/day1/0.in"), 3),
                Arguments.of("1.in", readFile("/day1/1.in"), 1135)
        );
    }

    private static Stream<Arguments> provideSecondInput() {
        return Stream.of(
                Arguments.of("0.in", readFile("/day1/0.in"), 6),
                Arguments.of("1.in", readFile("/day1/1.in"), 6558)
        );
    }
}