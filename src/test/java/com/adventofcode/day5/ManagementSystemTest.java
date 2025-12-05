package com.adventofcode.day5;

import com.adventofcode.common.Pair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.adventofcode.TestUtils.readFile;
import static org.assertj.core.api.Assertions.assertThat;

class ManagementSystemTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideFirstInput")
    void firstTestCase(String description, String input, long expected) {
        // given
        ManagementSystem solution = new ManagementSystem();
        List<Pair<Long>> records = input.split("\n\n")[0].lines().map(l -> new Pair<>(Long.parseLong(l.split("-")[0]), Long.parseLong(l.split("-")[1]))).toList();
        List<Long> ids = input.split("\n\n")[1].lines().mapToLong(Long::parseLong).boxed().toList();

        // when
        long result = solution.countFresh(records, ids);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideSecondInput")
    void secondTestCase(String description, String input, long expected) {
        // given
        ManagementSystem solution = new ManagementSystem();
        List<Pair<Long>> records = input.split("\n\n")[0].lines().map(l -> new Pair<>(Long.parseLong(l.split("-")[0]), Long.parseLong(l.split("-")[1]))).toList();

        // when
        long result = solution.countAllFresh(records);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideFirstInput() {
        return Stream.of(
                Arguments.of("0.in", readFile("/day5/0.in"), 3),
                Arguments.of("1.in", readFile("/day5/1.in"), 567)
        );
    }

    private static Stream<Arguments> provideSecondInput() {
        return Stream.of(
                Arguments.of("0.in", readFile("/day5/0.in"), 14),
                Arguments.of("1.in", readFile("/day5/1.in"), 354149806372909L)
        );
    }
}