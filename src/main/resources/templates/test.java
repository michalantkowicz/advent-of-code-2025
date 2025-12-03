package com.adventofcode.day@dayNumber@;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.adventofcode.TestUtils.readFile;
import static org.assertj.core.api.Assertions.assertThat;

class @className@Test {
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideFirstInput")
    void firstTestCase(String description, String input, long expected) {
        // given
        @className@ solution = new @className@();

        // when
        long result = solution.resolve(input);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideSecondInput")
    void secondTestCase(String description, String input, long expected) {
        // given
        @className@ solution = new @className@();

        // when
        long result = solution.resolve(input);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideFirstInput() {
        return Stream.of(
                Arguments.of("0.in", readFile("/day@dayNumber@/0.in"), -1L),
                Arguments.of("1.in", readFile("/day@dayNumber@/1.in"), -1L)
        );
    }

    private static Stream<Arguments> provideSecondInput() {
        return Stream.of(
                Arguments.of("0.in", readFile("/day@dayNumber@/0.in"), -1L),
                Arguments.of("1.in", readFile("/day@dayNumber@/1.in"), -1L)
        );
    }
}