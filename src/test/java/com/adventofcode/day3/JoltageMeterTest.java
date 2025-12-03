package com.adventofcode.day3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.adventofcode.TestUtils.getIntRows;
import static org.assertj.core.api.Assertions.assertThat;

class JoltageMeterTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideFirstInput")
    void shouldCalculateMaxJoltage(String description, List<List<Integer>> banks, long expected) {
        // given
        JoltageMeter joltageMeter = new JoltageMeter();

        // when
        long result = joltageMeter.sumMaxJoltages(banks, 2);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideSecondInput")
    void shouldCalculateMaxJoltageForLongBattery(String description, List<List<Integer>> banks, long expected) {
        // given
        JoltageMeter joltageMeter = new JoltageMeter();

        // when
        long result = joltageMeter.sumMaxJoltages(banks, 12);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideFirstInput() {
        return Stream.of(
                Arguments.of("0.in", getIntRows("/day3/0.in", ""), 357),
                Arguments.of("1.in", getIntRows("/day3/1.in", ""), 17321)
        );
    }

    private static Stream<Arguments> provideSecondInput() {
        return Stream.of(
                Arguments.of("0.in", getIntRows("/day3/0.in", ""), 3121910778619L),
                Arguments.of("1.in", getIntRows("/day3/1.in", ""), 171989894144198L)
        );
    }
}