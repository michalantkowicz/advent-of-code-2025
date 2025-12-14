package com.adventofcode.day12;

import java.util.Map;

public record Region(int width, int height, Map<Present, Integer> expectedPresents) {
    long area() {
        return (long) width * height;
    }

    boolean hasEnoughSpace() {
        long presentsTotalArea = this.expectedPresents().entrySet().stream()
                .mapToLong(e -> e.getKey().area() * e.getValue())
                .sum();
        return this.area() > presentsTotalArea;
    }
}
