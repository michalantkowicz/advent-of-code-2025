package com.adventofcode.day12;

import com.adventofcode.common.StringMatrix;

import java.util.List;
import java.util.Objects;

public record Present(String id, List<StringMatrix> manipulations) {
    long area() {
        StringMatrix matrix = manipulations.getFirst();
        return matrix.streamIndices().mapToInt(i -> "#".equals(matrix.at(i)) ? 1 : 0).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Present present)) return false;
        return Objects.equals(id, present.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
