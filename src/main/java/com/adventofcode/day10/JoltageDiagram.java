package com.adventofcode.day10;

import java.util.Arrays;
import java.util.List;

record JoltageDiagram(int[] expected) {
    JoltageDiagram copy() {
        int[] copy = new int[expected.length];
        for(int i = 0; i < expected.length; i++) copy[i] = expected[i];
        return new JoltageDiagram(copy);
    }
    
    boolean isValid(List<Button> buttons) {
        int[] result = new int[expected.length];
        buttons.forEach(b -> b.indices().forEach(i -> result[i]++));
        return Arrays.equals(result, expected);
    }
    
    boolean isValidForDimension(List<Button> buttons, int index) {
        int[] result = new int[expected.length];
        buttons.forEach(b -> b.indices().forEach(i -> result[i]++));
        return result[index] == expected[index];
    }
    
    boolean isLower(List<Button> buttons) {
        int[] result = new int[expected.length];
        buttons.forEach(b -> b.indices().forEach(i -> result[i]++));
        for(int i = 0; i < expected.length; i++) {
            if(result[i] > expected[i] ) {
                return false;
            }
        }
        return true;
    }
}
