package com.adventofcode.day3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class JoltageMeter {
    long sumMaxJoltages(List<List<Integer>> banks, int batteryLength) {
        long result = 0;
        for (List<Integer> bank : banks) {
            List<Integer> indices = new ArrayList<>();
            for (int i = 0; i < batteryLength; i++) {
                int r = findFirstMax(bank, indices.isEmpty() ? -1 : indices.getLast(), batteryLength - 1 - i);
                indices.add(r);
            }
            String joltageString = indices.stream().map(i -> String.valueOf(bank.get(i))).collect(Collectors.joining());
            result += Long.parseLong(joltageString);
        }
        return result;
    }

    int findFirstMax(List<Integer> list, int leftPad, int rightPad) {
        int index = list.size() - 1 - rightPad;
        for (int i = index; i > leftPad; i--) {
            if (list.get(i) >= list.get(index)) {
                index = i;
            }
        }
        return index;
    }
}
