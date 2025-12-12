package com.adventofcode.day10;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static com.adventofcode.TestUtils.getRows;
import static org.assertj.core.api.Assertions.assertThat;

class FactoryTest {
    @Disabled
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideFirstInput")
    void firstTestCase(String description, List<List<String>> rows, long expected) {
        // given
        Factory solution = new Factory();

        List<Machine> machines = rows.stream().map(this::parseMachine).toList();

        // when
        long result = solution.resolve(new ArrayList<>(machines));

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideSecondInput")
    void secondTestCase(String description, List<List<String>> rows, long expected) {
        // given
        Factory solution = new Factory();

        List<Machine> machines = rows.stream().map(this::parseMachine).toList();

        // when
        long result = solution.resolveForJoltage(machines);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private Machine parseMachine(List<String> inputRow) {
        List<Button> buttons = inputRow.subList(1, inputRow.size() - 1).stream()
                .map(b -> new Button(
                        UUID.randomUUID().toString(),
                        Arrays.stream(b.substring(1, b.length() - 1).split(","))
                                .map(Integer::parseInt)
                                .toList()
                ))
                .toList();

        String joltageString = inputRow.subList(inputRow.size() - 1, inputRow.size()).getFirst();
        String[] joltageSplit = joltageString.substring(1, joltageString.length() - 1).split(",");
        int[] expectedJoltages = new int[joltageSplit.length];
        for (int i = 0; i < joltageSplit.length; i++) {
            expectedJoltages[i] = Integer.parseInt(joltageSplit[i]);
        }

        return new Machine(new LightDiagram(inputRow.getFirst()), buttons, new JoltageDiagram(expectedJoltages), 0);
    }

    private static Stream<Arguments> provideFirstInput() {
        return Stream.of(
                Arguments.of("0.in", getRows("/day10/0.in", " "), 7),
                Arguments.of("1.in", getRows("/day10/1.in", " "), 545)
        );
    }

    private static Stream<Arguments> provideSecondInput() {
        return Stream.of(
                Arguments.of("0.in", getRows("/day10/0.in", " "), 33),
                Arguments.of("1.in", getRows("/day10/1.in", " "), -1L)
        );
    }
}