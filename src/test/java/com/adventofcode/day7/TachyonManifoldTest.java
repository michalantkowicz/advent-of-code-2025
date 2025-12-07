package com.adventofcode.day7;

import com.adventofcode.common.Pair;
import com.adventofcode.common.StringMatrix;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.adventofcode.TestUtils.readFile;
import static org.assertj.core.api.Assertions.assertThat;

class TachyonManifoldTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideFirstInput")
    void firstTestCase(String description, String input, long expected) {
        // given
        TachyonManifold solution = new TachyonManifold();

        StringMatrix manifold = new StringMatrix(input);
        Pair<Integer> entry = input.lines().findFirst().map(l -> new Pair<>(l.indexOf('S'), 0)).orElseThrow();
        
        // when
        long result = solution.countSplits(manifold, entry);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideSecondInput")
    void secondTestCase(String description, String input, long expected) {
        // given
        TachyonManifold solution = new TachyonManifold();

        StringMatrix manifold = new StringMatrix(input);
        Pair<Integer> entry = input.lines().findFirst().map(l -> new Pair<>(l.indexOf('S'), 0)).orElseThrow();

        // when
        long result = solution.countTimelines(manifold, entry);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideFirstInput() {
        return Stream.of(
                Arguments.of("0.in", readFile("/day7/0.in"), 21),
                Arguments.of("1.in", readFile("/day7/1.in"), 1615)
        );
    }

    private static Stream<Arguments> provideSecondInput() {
        return Stream.of(
                Arguments.of("0.in", readFile("/day7/0.in"), 40),
                Arguments.of("1.in", readFile("/day7/1.in"), 43560947406326L)
        );
    }
}