package com.adventofcode.common;

import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public interface Matrix<T> {
    int width();

    int height();

    /**
     * @return value at [x, y] or null if index is out of bounds
     */
    T at(int x, int y);

    void set(int x, int y, T value);

    default Stream<Pair<Integer>> streamIndices() {
        return IntStream.range(0, width())
                .mapToObj(i -> IntStream.range(0, height()).mapToObj(j -> new Pair<>(i, j)))
                .flatMap(Function.identity());
    }

    default Stream<Pair<Integer>> streamAdjacentIndices(Pair<Integer> position) {
        return Stream.of(Direction.values())
                .map(d -> d.moveFrom(position))
                .filter(p -> p.a() >= 0 && p.a() < width() && p.b() >= 0 && p.b() < height());
    }
}
