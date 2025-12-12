package com.adventofcode.day12;

import com.adventofcode.common.StringMatrix;

public record Present(String id, StringMatrix matrix) {
    long area() {
        return matrix.streamIndices().mapToInt(i -> "#".equals(matrix.at(i)) ? 1 : 0).sum();
    }
}
