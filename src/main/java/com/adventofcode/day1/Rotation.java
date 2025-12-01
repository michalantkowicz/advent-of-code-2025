package com.adventofcode.day1;

record Rotation(Boolean isRight, Long steps) {
    long rotate(long start) {
        return rotateBy(start, steps);
    }

    long rotateBy(long start, long by) {
        return isRight ? start + by : start - by;
    }
}