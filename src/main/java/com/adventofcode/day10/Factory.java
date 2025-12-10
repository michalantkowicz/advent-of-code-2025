package com.adventofcode.day10;

import java.util.*;
import java.util.stream.Collectors;

class Factory {
    Set<String> CACHE = new HashSet<>();
    long resolve(List<LightMachine> machines) {
        return machines.stream().mapToLong(this::calculateMinButtons).sum();
    }

    private int calculateMinButtons(LightMachine machine) {
        int min = 20;
        List<Integer> toCheck = machine.buttons();
        Collections.shuffle(toCheck);
        for (int button : toCheck) {
            min = Math.min(min, inspect(machine, List.of(button), 1, min));
        }
        return min;
    }

    private int inspect(LightMachine machine, List<Integer> current, int depth, int maxDepth) {
        if (depth >= maxDepth) {
            return maxDepth;
        } else if (machine.diagram().isValid(current)) {
            return depth;
        } else {
            int min = maxDepth;
            for (int button : machine.buttons()) {
                if (button != current.getLast()) {
                    List<Integer> temp = new ArrayList<>(current);
                    temp.add(button);
                    min = Math.min(min, inspect(machine, temp, depth + 1, min));
                }
            }
            return min;
        }
    }

    long resolveForJoltage(List<JoltageMachine> machines) {
        long result = 0L;
        int i = 0;
        for (JoltageMachine machine : machines) {
            result += calculateMinButtons(machine);
            System.out.println(i++);
        }
        return result;
    }

    private int calculateMinButtons(JoltageMachine machine) {
        int min = 100;
        for (Button button : machine.buttons()) {
            if(machine.maxPerButton().get(button.id()) > 0) {
                min = Math.min(min, inspect(machine, Map.of(button.id(), 1), 1, min));
            }
        }
        return min;
    }

    private int inspect(JoltageMachine machine, Map<String, Integer> current, int depth, int maxDepth) {
        if (depth >= maxDepth) {
            CACHE.add(calculateId(current));
            return maxDepth;
        } else if (machine.isValid(current)) {
            return depth;
        } else if(!machine.isLower(current)) {
            CACHE.add(calculateId(current));
            return maxDepth;
        } else {
            int min = maxDepth;
            for (Button button : machine.buttons()) {
                Integer buttonCount = current.get(button.id());
                buttonCount = (buttonCount == null) ? 0 : buttonCount;
                if (machine.maxPerButton().get(button.id()) >= buttonCount + 1) {
                    Map<String, Integer> temp = new HashMap<>(current);
                    temp.compute(button.id(), (k, v) -> (v == null)? 1 : v+1);
                    if(!CACHE.contains(calculateId(temp))) {
                        min = Math.min(min, inspect(machine, temp, depth + 1, min));
                    }
                }
            }
            if(min == maxDepth) CACHE.add(calculateId(current));
            return min;
        }
    }

    private String calculateId(Map<String, Integer> map) {
        StringBuilder result = new StringBuilder();
        for(String k : map.keySet()) {
            result.append(k.repeat(map.get(k)));
        }
        return Arrays.stream(result.toString().split("")).sorted().collect(Collectors.joining());
    }
}