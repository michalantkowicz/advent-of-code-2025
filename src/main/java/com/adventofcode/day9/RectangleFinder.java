package com.adventofcode.day9;

import com.adventofcode.common.Direction;
import com.adventofcode.common.Pair;

import java.util.*;
import java.util.stream.Collectors;

class RectangleFinder {
    long findLargest(List<Pair<Long>> tiles) {
        long result = Long.MIN_VALUE;
        for (int i = 0; i < tiles.size(); i++) {
            for (int j = i + 1; j < tiles.size(); j++) {
                result = Math.max(area(new Pair<>(tiles.get(i), tiles.get(j))), result);
            }
        }
        return result;
    }

    long findLargestOfRedAndGreen(List<Pair<Integer>> tiles) {
        Set<Pair<Pair<Integer>>> allRectangles = new HashSet<>();
        for (int i = 0; i < tiles.size(); i++) {
            for (int j = i + 1; j < tiles.size(); j++) {
                allRectangles.add(new Pair<>(tiles.get(i), tiles.get(j)));
            }
        }
        
        Pair<Integer> current = tiles.getFirst();
        
        for(int i = 0; i < tiles.size(); i++) {
            int next = (i == tiles.size() - 1) ? 0 : (i + 1);
            Direction direction = Direction.detect(tiles.get(i), tiles.get(next));
            Direction leftDirection = Direction.detect(tiles.get(i), tiles.get(next)).turnLeft();
            while(!current.equals(tiles.get(next))) {
                current = direction.moveFrom(current);
                if(current.equals(tiles.get(next))) break;
                Pair<Integer> firstOut = leftDirection.moveFrom(current);
                allRectangles.removeIf(rectangle -> containsInt(rectangle, new Pair<>(firstOut, firstOut)));
            }
        }
        
        return allRectangles.stream().mapToLong(this::areaInt).max().getAsLong();
    }

    private long areaInt(Pair<Pair<Integer>> pair) {
        Pair<Integer> a = pair.a();
        Pair<Integer> b = pair.b();
        return (Math.max(a.a(), b.a()) - Math.min(a.a(), b.a()) + 1) * (Math.max(a.b(), b.b()) - Math.min(a.b(), b.b()) + 1);
    }
    
    private long area(Pair<Pair<Long>> pair) {
        Pair<Long> a = pair.a();
        Pair<Long> b = pair.b();
        return (Math.max(a.a(), b.a()) - Math.min(a.a(), b.a()) + 1) * (Math.max(a.b(), b.b()) - Math.min(a.b(), b.b()) + 1);
    }

    private boolean containsInt(Pair<Pair<Integer>> container, Pair<Pair<Integer>> item) {
        int containerMaxLeft = Math.min(container.a().a(), container.b().a());
        int containerMaxRight = Math.max(container.a().a(), container.b().a());
        int containerMaxTop = Math.min(container.a().b(), container.b().b());
        int containerMaxBottom = Math.max(container.a().b(), container.b().b());
        int itemMaxLeft = Math.min(item.a().a(), item.b().a());
        int itemMaxRight = Math.max(item.a().a(), item.b().a());
        int itemMaxTop = Math.min(item.a().b(), item.b().b());
        int itemMaxBottom = Math.max(item.a().b(), item.b().b());
        return (containerMaxLeft <= itemMaxLeft && containerMaxRight >= itemMaxRight && containerMaxTop <= itemMaxTop && containerMaxBottom >= itemMaxBottom);
    }
    
    private boolean contains(Pair<Pair<Long>> container, Pair<Pair<Long>> item) {
        long containerMaxLeft = Math.min(container.a().a(), container.b().a());
        long containerMaxRight = Math.max(container.a().a(), container.b().a());
        long containerMaxTop = Math.min(container.a().b(), container.b().b());
        long containerMaxBottom = Math.max(container.a().b(), container.b().b());
        long itemMaxLeft = Math.min(item.a().a(), item.b().a());
        long itemMaxRight = Math.max(item.a().a(), item.b().a());
        long itemMaxTop = Math.min(item.a().b(), item.b().b());
        long itemMaxBottom = Math.max(item.a().b(), item.b().b());
        return (containerMaxLeft <= itemMaxLeft && containerMaxRight >= itemMaxRight && containerMaxTop >= itemMaxTop && containerMaxBottom <= itemMaxBottom);
    }
}