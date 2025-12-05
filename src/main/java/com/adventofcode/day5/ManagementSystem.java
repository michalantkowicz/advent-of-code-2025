package com.adventofcode.day5;

import com.adventofcode.common.Pair;

import java.util.Comparator;
import java.util.List;

class ManagementSystem {
    long countFresh(List<Pair<Long>> ranges, List<Long> ids) {
        return ids.stream().filter(
                i -> ranges.stream().anyMatch(r -> i >= r.a() && i <= r.b())
        ).count();
    }

    long countAllFresh(List<Pair<Long>> ranges) {
        long result = 0L;
        ranges = ranges.stream().sorted(Comparator.comparingLong(Pair::a)).toList();
        Pair<Long> current = ranges.getFirst();
        for (Pair<Long> next : ranges.subList(1, ranges.size())) {
            if (next.a() <= current.b()) {
                if (next.b() > current.b()) {
                    current = new Pair<>(current.a(), next.b());
                }
            } else {
                result += length(current);
                current = next;
            }
        }
        return result + length(current);
    }

    private long length(Pair<Long> range) {
        return range == null ? 0 : range.b() - range.a() + 1;
    }
}