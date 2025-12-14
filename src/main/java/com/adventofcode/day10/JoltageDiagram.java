package com.adventofcode.day10;

record JoltageDiagram(int[] expected) {
    boolean isLower(JoltageDiagram other) {
        for(int i = 0; i < expected.length; i++) {
            if(other.expected[i] > expected[i] ) {
                return false;
            }
        }
        return true;
    }
}
