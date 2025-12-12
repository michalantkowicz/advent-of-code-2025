package com.adventofcode.day10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

record Machine(LightDiagram lightDiagram, List<Button> buttons, JoltageDiagram joltageDiagram, int depth) {
    Machine of(List<Button> appliedButtons) {
        JoltageDiagram copy = joltageDiagram.copy();
        appliedButtons.forEach(b -> b.indices().forEach(i -> copy.expected()[i]--));
        Set<String> appliedButtonsIds = appliedButtons.stream().map(Button::id).collect(Collectors.toSet());
        List<Button> newButtons = new ArrayList<>(buttons).stream().filter(b -> !appliedButtonsIds.contains(b.id())).toList();
        return new Machine(lightDiagram, newButtons, copy, depth + appliedButtons.size());
    }
    
    boolean isEmpty() {
        return Arrays.stream(joltageDiagram.expected()).allMatch(i -> i == 0);
    }
    
    boolean isValid() {
        return Arrays.stream(joltageDiagram.expected()).allMatch(i -> i >= 0) && !buttons.isEmpty();
    }
    
    List<Integer> buttonsIntegers() {
        return buttons.stream().map(Button::toInteger).toList();
    }

    DimensionWithButtons minDimensionWithButtons() {
        int index = 0;
        int minSum = Integer.MAX_VALUE;
        List<Button> minSumButtons = new ArrayList<>();

        for (int i = 0; i < joltageDiagram.expected().length; i++) {
            if(joltageDiagram.expected()[i] == 0) continue;
            int finalI = i;
            List<Button> list = buttons.stream().filter(b -> b.indices().contains(finalI)).toList();
            if (list.size() < minSum || (list.size() == minSum && joltageDiagram.expected()[i] < joltageDiagram.expected()[index])) {
                minSum = list.size();
                index = i;
                minSumButtons = new ArrayList<>(list);
            }
        }

        // System.out.println("Dimension " + index + " buttons count: " + minSumButtons.size());

        return new DimensionWithButtons(index, joltageDiagram.expected()[index], joltageDiagram, minSumButtons);
    }
}
