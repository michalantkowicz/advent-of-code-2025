package com.adventofcode.day4;

import com.adventofcode.common.StringMatrix;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.adventofcode.TestUtils.readFile;
import static org.assertj.core.api.Assertions.assertThat;

class ForkliftDiagramTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideFirstInput")
    void firstTestCase(String description, StringMatrix diagram, long expected) {
        // given
        ForkliftDiagram solution = new ForkliftDiagram();

        // when
        long result = solution.countAccessibleRolls(diagram);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideSecondInput")
    void secondTestCase(String description, StringMatrix diagram, long expected) {
        // given
        ForkliftDiagram solution = new ForkliftDiagram();

        // when
        long result = solution.countAccessibleRollsWithIterations(diagram);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideFirstInput() {
        return Stream.of(
                Arguments.of("0.in", new StringMatrix(readFile("/day4/0.in")), 13),
                Arguments.of("1.in", new StringMatrix(readFile("/day4/1.in")), 1489)
        );
    }

    private static Stream<Arguments> provideSecondInput() {
        return Stream.of(
                Arguments.of("0.in", readFile("/day4/0.in"), 43),
                Arguments.of("1.in", readFile("/day4/1.in"), 8890)
        );
    }
}