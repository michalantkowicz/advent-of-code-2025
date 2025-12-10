package com.adventofcode.day10;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static com.adventofcode.TestUtils.getRows;
import static com.adventofcode.TestUtils.readFile;
import static org.assertj.core.api.Assertions.assertThat;

class FactoryTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideFirstInput")
    void firstTestCase(String description, List<List<String>> rows, long expected) {
        // given
        Factory solution = new Factory();

        List<LightMachine> machines = rows.stream().map(r -> {
            List<List<Integer>> buttons = r.subList(1, r.size() - 1).stream()
                    .map(b -> Arrays.stream(b.substring(1, b.length() - 1).split(","))
                            .map(Integer::parseInt)
                            .toList())
                    .toList();
            List<Integer> xorButtons = new ArrayList<>();
            for(List<Integer> button : buttons) {
                int result = 0;
                for(int b : button) result |= 1 << b;
                xorButtons.add(result);
            }
            return new LightMachine(new LightDiagram(r.getFirst()), xorButtons);
        }).toList();

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

        List<JoltageMachine> machines = rows.stream().map(r -> {
            List<Button> buttons = r.subList(1, r.size() - 1).stream()
                    .map(b -> new Button(
                            UUID.randomUUID().toString(),
                            Arrays.stream(b.substring(1, b.length() - 1).split(","))
                            .map(Integer::parseInt)
                            .toList()
                    ))
                    .toList();
            String last = r.getLast();
            Map<Integer, Integer> expectedJoltage = new HashMap<>();
            String[] values = last.substring(1, last.length() - 1).split(",");
            for(int i = 0; i < values.length; i++) {
                expectedJoltage.put(i, Integer.parseInt(values[i]));
            }
            return new JoltageMachine(buttons, expectedJoltage);
        }).toList();

        // when
        long result = solution.resolveForJoltage(machines);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideFirstInput() {
        return Stream.of(
                Arguments.of("0.in", getRows("/day10/0.in", " "), 7)
//                ,
//                Arguments.of("1.in", getRows("/day10/1.in", " "), 545)
        );
    }

    private static Stream<Arguments> provideSecondInput() {
        return Stream.of(
                Arguments.of("0.in", getRows("/day10/0.in", " "), 33),
                Arguments.of("1.in", getRows("/day10/1.in", " "), -1L)
        );
    }
}