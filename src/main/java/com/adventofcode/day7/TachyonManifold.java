package com.adventofcode.day7;

import com.adventofcode.common.Direction;
import com.adventofcode.common.Pair;
import com.adventofcode.common.StringMatrix;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class TachyonManifold {
    long countSplits(StringMatrix manifold, Pair<Integer> entry) {
        Set<Integer> beams = Set.of(entry.a());
        long result = 0;
        for (int y = 0; y < manifold.height() - 1; y++) {
            Set<Integer> nextBeams = new HashSet<>();
            for (int beam : beams) {
                Pair<Integer> nextPosition = Direction.DOWN.moveFrom(new Pair<>(beam, y));
                if (".".equals(manifold.at(nextPosition))) {
                    nextBeams.add(beam);
                } else {
                    result++;
                    nextBeams.add(beam - 1);
                    nextBeams.add(beam + 1);
                }
            }
            beams = nextBeams.stream().filter(b -> b >= 0 && b < manifold.width()).collect(Collectors.toSet());
        }
        return result;
    }

    long countTimelines(StringMatrix manifold, Pair<Integer> entry) {
        final Map<Integer, Long> beams = new HashMap<>(Map.of(entry.a(), 1L));
        for (int y = 1; y < manifold.height() - 1; y++) {
            Map<Integer, Long> newBeams = new HashMap<>();
            for (int beam : new HashSet<>(beams.keySet())) {
                Pair<Integer> nextPosition = Direction.DOWN.moveFrom(new Pair<>(beam, y - 1));
                if ("^".equals(manifold.at(nextPosition))) {
                    newBeams.compute(beam - 1, (k, v) -> (v == null) ? beams.get(beam) : v + beams.get(beam));
                    newBeams.compute(beam + 1, (k, v) -> (v == null) ? beams.get(beam) : v + beams.get(beam));
                } else {
                    newBeams.compute(beam, (k, v) -> (v == null) ? beams.get(beam) : v + beams.get(beam));
                }
            }
            beams.clear();
            beams.putAll(newBeams);
        }
        return beams.keySet().stream().mapToLong(beams::get).sum();
    }
}