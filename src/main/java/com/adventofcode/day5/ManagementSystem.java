package com.adventofcode.day5;

import com.adventofcode.common.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class ManagementSystem {
    long countFresh(List<Pair<Long>> ranges, List<Long> ids) {
        return ids.stream().filter(
                i -> ranges.stream().anyMatch(r -> i >= r.a() && i <= r.b())
        ).count();
    }

    long countAllFresh(List<Pair<Long>> ranges) {
        ranges = ranges.stream().sorted(Comparator.comparingLong(Pair::a)).toList();
        List<Pair<Long>> effectiveRanges = new ArrayList<>();
        Pair<Long> current = ranges.getFirst();
        for (int i = 1; i < ranges.size(); i++) {
            Pair<Long> next = ranges.get(i);
            if (next.a() <= current.b()) {
                if (next.b() > current.b()) {
                    current = new Pair<>(current.a(), next.b());
                }
            } else {
                effectiveRanges.add(current);
                current = next;
            }
        }
        effectiveRanges.add(current);
        return effectiveRanges.stream().mapToLong(this::length).sum();
    }

    private long length(Pair<Long> range) {
        return range == null ? 0 : range.b() - range.a() + 1;
    }
}