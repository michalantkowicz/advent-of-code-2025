package com.adventofcode.day10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class JoltageMachine {
    private final List<Button> buttons;
    private final Map<Integer, Integer> expectedJoltage;
    private Map<String, Button> lookup;
    private final Map<String, Integer> maxPerButton = new HashMap<>();

    JoltageMachine(List<Button> buttons, Map<Integer, Integer> expectedJoltage) {
        this.buttons = buttons;
        this.expectedJoltage = expectedJoltage;
        this.lookup = new HashMap<>();

        for(Button button : buttons) lookup.put(button.id(), button);

        for(Button button : buttons) {
            int i = 0;
            Map<String, Integer> temp = new HashMap<>(Map.of(button.id(), 0));
            while(isLower(temp)) {
                i++;
                temp.put(button.id(), i);
            }
            maxPerButton.put(button.id(), i);
        }
    }

    List<Button> buttons() {
        return this.buttons;
    }

    Map<String, Integer> maxPerButton() {
        return this.maxPerButton;
    }

    boolean isValid(Map<String, Integer> buttons) {
        Map<Integer, Integer> result = new HashMap<>();
        for(Map.Entry<String, Integer> e : buttons.entrySet()) {
            Button b = lookup.get(e.getKey());
            for(int i = 0; i < e.getValue(); i++) {
                for(int c : b.counters()) {
                    result.compute(c, (k, v) -> (v == null) ? 1 : v+1);
                }
            }
        }

        for(Map.Entry<Integer, Integer> e : expectedJoltage.entrySet()) {
            Integer value = result.get(e.getKey());
            value = (value == null) ? 0 : value;
            if(!value.equals(e.getValue())) {
                return false;
            }
        }
        return true;
    }

    boolean isLower(Map<String, Integer> buttons) {
        Map<Integer, Integer> result = new HashMap<>();
        for(Map.Entry<String, Integer> e : buttons.entrySet()) {
            Button b = lookup.get(e.getKey());
            for(int i = 0; i < e.getValue(); i++) {
                for(int c : b.counters()) {
                    result.compute(c, (k, v) -> (v == null) ? 1 : v+1);
                }
            }
        }

        for(Map.Entry<Integer, Integer> e : expectedJoltage.entrySet()) {
            Integer value = result.get(e.getKey());
            value = (value == null) ? 0 : value;
            if(value > e.getValue()) {
                return false;
            }
        }
        return true;
    }

    private static Map<Integer, Integer> getResult(List<Button> buttons) {
        Map<Integer, Integer> result = new HashMap<>();
        for (Button b : buttons) {
            for (int c : b.counters()) {
                result.compute(c, (k,v) -> (v == null) ? 1 : v + 1);
            }
        }
        return result;
    }
}
