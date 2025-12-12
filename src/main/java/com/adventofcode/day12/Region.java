package com.adventofcode.day12;

import java.util.Map;

public record Region(int width, int height, Map<String, Integer> expectedPresents) {
    long area() {
        return (long) width * height;
    }
}
