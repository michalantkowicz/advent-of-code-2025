package com.adventofcode.day10;

import java.util.List;

record Button(String id, List<Integer> indices) {
    int toInteger() {
        int result = 0;
        for(int i : indices) result |= 1 << i;
        return result;
    }
}
