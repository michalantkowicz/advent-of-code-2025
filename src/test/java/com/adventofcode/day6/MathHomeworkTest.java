package com.adventofcode.day6;

import com.adventofcode.common.StringMatrix;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.adventofcode.TestUtils.getColumns;
import static com.adventofcode.TestUtils.readFile;
import static org.assertj.core.api.Assertions.assertThat;

class MathHomeworkTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideFirstInput")
    void firstTestCase(String description, List<List<String>> columns, long expected) {
        // given
        MathHomework solution = new MathHomework();
        List<List<Long>> problems = columns.stream().map(p -> p.reversed().stream().skip(1).mapToLong(Long::valueOf).boxed().toList().reversed()).toList();
        List<String> operators = columns.stream().map(p -> p.reversed().getFirst()).toList();
        // when
        long result = solution.resolve(problems, operators);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideSecondInput")
    void secondTestCase(String description, String input, long expected) {
        // given
        MathHomework solution = new MathHomework();

        // when
        long result = solution.decodeAndResolve(new StringMatrix(input));

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideFirstInput() {
        return Stream.of(
                Arguments.of("0.in", getColumns("/day6/0.in"), 4277556),
                Arguments.of("1.in", getColumns("/day6/1.in"), 4693419406682L)
        );
    }

    private static Stream<Arguments> provideSecondInput() {
        return Stream.of(
                Arguments.of("0.in", readFile("/day6/0.in"), 3263827),
                Arguments.of("1.in", readFile("/day6/1.in"), 9029931401920L)
        );
    }
}