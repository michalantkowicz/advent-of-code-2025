package com.adventofcode.day2;

import com.adventofcode.common.Pair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.adventofcode.TestUtils.readFile;
import static org.assertj.core.api.Assertions.assertThat;

class ProductProcessorTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideFirstInput")
    void checkInvalidProductIdsSum(String description, String input, long expected) {
        // given
        ProductProcessor productProcessor = new ProductProcessor();

        // when
        long result = productProcessor.sumInvalidIds(parseRanges(input.split(",")));

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideSecondInput")
    void checkInvalidProductIdsSumWithNewRule(String description, String input, long expected) {
        // given
        ProductProcessor productProcessor = new ProductProcessor();

        // when
        long result = productProcessor.sumInvalidIdsWithNewRule(parseRanges(input.split(",")));

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static List<Pair<Long>> parseRanges(String[] input) {
        return Stream.of(input).map(r -> new Pair<>(Long.parseLong(r.split("-")[0]), Long.parseLong(r.split("-")[1]))).toList();
    }

    private static Stream<Arguments> provideFirstInput() {
        return Stream.of(
                Arguments.of("0.in", readFile("/day2/0.in"), 1227775554),
                Arguments.of("1.in", readFile("/day2/1.in"), 26255179562L)
        );
    }

    private static Stream<Arguments> provideSecondInput() {
        return Stream.of(
                Arguments.of("0.in", readFile("/day2/0.in"), 4174379265L),
                Arguments.of("1.in", readFile("/day2/1.in"), 31680313976L)
        );
    }
}