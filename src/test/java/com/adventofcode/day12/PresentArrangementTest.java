package com.adventofcode.day12;

import com.adventofcode.common.StringMatrix;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.adventofcode.TestUtils.getLines;
import static org.assertj.core.api.Assertions.assertThat;

class PresentArrangementTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideFirstInput")
    void firstTestCase(String description, List<String> input, List<Present> presents, long expected) {
        // given
        PresentArrangement solution = new PresentArrangement();

        // when
        long result = solution.resolve(parseRegions(input, presents));

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static List<Region> parseRegions(List<String> input, List<Present> presents) {
        return input.stream().map(l -> {
            String regionSize = l.split(":")[0];
            int width = Integer.parseInt(regionSize.split("x")[0]);
            int height = Integer.parseInt(regionSize.split("x")[1]);
            List<String> countList = Arrays.stream(l.split(":")[1].split(" ")).skip(1).toList();
            Map<Present, Integer> presentCount = new HashMap<>();
            for (int i = 0; i < countList.size(); i++) {
                presentCount.put(presents.get(i), Integer.parseInt(countList.get(i)));
            }
            return new Region(width, height, presentCount);
        }).toList();
    }

    private static Stream<Arguments> provideFirstInput() {
        return Stream.of(
                Arguments.of("0.in", getLines("/day12/0.in"), getPresentsExample(), 2),
                Arguments.of("1.in", getLines("/day12/1.in"), getPresents(), 433)
        );
    }

    private static List<Present> getPresentsExample() {
        return List.of(
                new Present("A", generateManipulations(new StringMatrix(
                        """
                                ###
                                ##.
                                ##.
                                """))),
                new Present("B", generateManipulations(new StringMatrix(
                        """
                                ###
                                ##.
                                .##
                                """))),
                new Present("C", generateManipulations(new StringMatrix(
                        """
                                .##
                                ###
                                ##.
                                """))),
                new Present("D", generateManipulations(new StringMatrix(
                        """
                                ##.
                                ###
                                ##.
                                """))),
                new Present("E", generateManipulations(new StringMatrix(
                        """
                                ###
                                #..
                                ###
                                """))),
                new Present("F", generateManipulations(new StringMatrix(
                        """
                                ###
                                .#.
                                ###
                                """))));
    }

    private static List<Present> getPresents() {
        return List.of(
                new Present("A", generateManipulations(new StringMatrix(
                        """
                                ..#
                                .##
                                ###
                                """))),
                new Present("B", generateManipulations(new StringMatrix(
                        """
                                ###
                                .##
                                .##
                                """))),
                new Present("C", generateManipulations(new StringMatrix(
                        """
                                ..#
                                .##
                                ##.
                                """))),
                new Present("D", generateManipulations(new StringMatrix(
                        """
                                ##.
                                .##
                                ###
                                """))),
                new Present("E", generateManipulations(new StringMatrix(
                        """
                                ###
                                #.#
                                #.#
                                """))),
                new Present("F", generateManipulations(new StringMatrix(
                        """
                                ###
                                .#.
                                ###
                                """))));
    }

    private static List<StringMatrix> generateManipulations(StringMatrix matrix) {
        return List.of(
                matrix,
                matrix.rotate(),
                matrix.rotate().rotate()
//                matrix.flipHorizontally(),
//                matrix.flipHorizontally().rotate(),
//                matrix.flipHorizontally().rotate().rotate(),
//                matrix.flipHorizontally().rotate().rotate(),
//                matrix.flipVertically(),
//                matrix.flipVertically().rotate(),
//                matrix.flipVertically().rotate().rotate(),
//                matrix.flipVertically().rotate().rotate(),
//                matrix.flipHorizontally().flipVertically(),
//                matrix.flipHorizontally().flipVertically().rotate(),
//                matrix.flipHorizontally().flipVertically().rotate().rotate(),
//                matrix.flipHorizontally().flipVertically().rotate().rotate(),
//                matrix.flipVertically().flipHorizontally(),
//                matrix.flipVertically().flipHorizontally().rotate(),
//                matrix.flipVertically().flipHorizontally().rotate().rotate(),
//                matrix.flipVertically().flipHorizontally().rotate().rotate()
        );
    }
}