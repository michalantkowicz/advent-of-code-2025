package com.adventofcode.day9;

import com.adventofcode.common.Pair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.adventofcode.TestUtils.getIntPairs;
import static com.adventofcode.TestUtils.getLongPairs;
import static org.assertj.core.api.Assertions.assertThat;

class RectangleFinderTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideFirstInput")
    void firstTestCase(String description, List<Pair<Long>> input, long expected) {
        // given
        RectangleFinder solution = new RectangleFinder();

        // when
        long result = solution.findLargest(input);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideSecondInput")
    void secondTestCase(String description, List<Pair<Integer>> input, long expected) {
        // given
        RectangleFinder solution = new RectangleFinder();

        // when
        long result = solution.findLargestOfRedAndGreen(input);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideFirstInput() {
        return Stream.of(
                Arguments.of("0.in", getLongPairs("/day9/0.in", ","), 50),
                Arguments.of("1.in", getLongPairs("/day9/1.in", ","), 4750092396L)
        );
    }

    private static Stream<Arguments> provideSecondInput() {
        return Stream.of(
                Arguments.of("0.in", getIntPairs("/day9/0.in", ","), 24),
                Arguments.of("1.in", getIntPairs("/day9/1.in", ","), 1468516555)
        );
    }
}