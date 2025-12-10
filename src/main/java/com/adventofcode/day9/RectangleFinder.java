package com.adventofcode.day9;

import com.adventofcode.common.Direction;
import com.adventofcode.common.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class RectangleFinder {
    long findLargest(List<Pair<Integer>> tiles) {
        long result = Long.MIN_VALUE;
        for (int i = 0; i < tiles.size(); i++) {
            for (int j = i + 1; j < tiles.size(); j++) {
                result = Math.max(area(new Pair<>(tiles.get(i), tiles.get(j))), result);
            }
        }
        return result;
    }

    long findLargestOfRedAndGreen(List<Pair<Integer>> tiles) {
        List<Pair<Pair<Integer>>> allRectangles = new ArrayList<>();
        for (int i = 0; i < tiles.size(); i++) {
            for (int j = i + 1; j < tiles.size(); j++) {
                allRectangles.add(new Pair<>(tiles.get(i), tiles.get(j)));
            }
        }
        allRectangles.sort(Comparator.comparingLong(this::area).reversed());

        for (int i = 0; i < tiles.size(); i++) {
            Pair<Integer> current = tiles.get(i);
            Pair<Integer> next = tiles.get((i + 1) % tiles.size());

            Direction leftDirection = Direction.detect(current, next).turnLeft();

            Pair<Pair<Integer>> line = new Pair<>(
                    leftDirection.moveFrom((current)),
                    leftDirection.moveFrom((next))
            );

            allRectangles.removeIf(rectangle -> intersects(rectangle, line));
        }

        return allRectangles.stream().mapToLong(this::area).max().orElseThrow();
    }

    private long area(Pair<Pair<Integer>> pair) {
        long aa = (long) pair.a().a();
        long ab = (long) pair.a().b();
        long ba = (long) pair.b().a();
        long bb = (long) pair.b().b();
        return (Math.max(aa, ba) - Math.min(aa, ba) + 1) * (Math.max(ab, bb) - Math.min(ab, bb) + 1);
    }

    private boolean intersects(Pair<Pair<Integer>> container, Pair<Pair<Integer>> line) {
        int maxLeft = Math.min(container.a().a(), container.b().a());
        int maxRight = Math.max(container.a().a(), container.b().a());
        int maxTop = Math.min(container.a().b(), container.b().b());
        int maxBottom = Math.max(container.a().b(), container.b().b());
        int aa = line.a().a();
        int ab = line.a().b();
        int ba = line.b().a();
        int bb = line.b().b();

        Direction lineDirection = Direction.detect(line.a(), line.b());

        if (List.of(Direction.TOP, Direction.DOWN).contains(lineDirection)) {
            boolean maxTopInLine = maxTop > Math.min(ab, bb) && maxTop < Math.max(ab, bb);
            boolean maxBottomInLine = maxBottom > Math.min(ab, bb) && maxBottom < Math.max(ab, bb);
            boolean lineInContainer = maxTop <= Math.min(ab, bb) && maxBottom >= Math.max(ab, bb);
            return aa >= maxLeft && aa <= maxRight && (maxBottomInLine || maxTopInLine || lineInContainer);
        } else {
            boolean maxLeftInLine = maxLeft > Math.min(aa, ba) && maxLeft < Math.max(aa, ba);
            boolean maxRightInLine = maxRight > Math.min(aa, ba) && maxRight < Math.max(aa, ba);
            boolean lineInContainer = maxLeft <= Math.min(aa, ba) && maxRight >= Math.max(aa, ba);
            return line.a().b() >= maxTop && line.a().b() <= maxBottom && (maxLeftInLine || maxRightInLine || lineInContainer);
        }
    }
}