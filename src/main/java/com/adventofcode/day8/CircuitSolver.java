package com.adventofcode.day8;

import com.adventofcode.common.Pair;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class CircuitSolver {
    long resolve(List<Position> jBoxes, long limit) {
        List<PairDistance> distances = calculateDistances(jBoxes, limit);
        
        long circuitId = 0L;
        Map<Position, Long> circuits = new HashMap<>();
        for (PairDistance pairDistance : distances) {
            Position positionA = pairDistance.pair().a();
            Position positionB = pairDistance.pair().b();
            if (!circuits.containsKey(positionA) && !circuits.containsKey(positionB)) {
                long newId = circuitId++;
                circuits.put(positionA, newId);
                circuits.put(positionB, newId);
            } else if (!circuits.containsKey(positionB)) {
                circuits.put(positionB, circuits.get(positionA));
            } else if (!circuits.containsKey(positionA)) {
                circuits.put(positionA, circuits.get(positionB));
            } else {
                long oldCircuit = circuits.get(positionA);
                for (Position k : circuits.keySet()) {
                    if (circuits.get(k).equals(oldCircuit)) {
                        circuits.put(k, circuits.get(positionB));
                    }
                }
            }
        }

        Map<Long, Long> circuitCounts = circuits.values().stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return circuitCounts
                .values().stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .reduce(1L, (a, b) -> a * b);
    }

    long resolveLongest(List<Position> jBoxes) {
        List<PairDistance> distances = calculateDistances(jBoxes, Integer.MAX_VALUE);

        long circuitId = 0L;
        Map<Position, Long> circuits = new HashMap<>();
        for (PairDistance pairDistance : distances) {
            Position positionA = pairDistance.pair().a();
            Position positionB = pairDistance.pair().b();
            if (!circuits.containsKey(positionA) && !circuits.containsKey(positionB)) {
                long newId = circuitId++;
                circuits.put(positionA, newId);
                circuits.put(positionB, newId);
            } else if (!circuits.containsKey(positionB)) {
                circuits.put(positionB, circuits.get(positionA));
            } else if (!circuits.containsKey(positionA)) {
                circuits.put(positionA, circuits.get(positionB));
            } else {
                long oldCircuit = circuits.get(positionA);
                for (Position k : circuits.keySet()) {
                    if (circuits.get(k).equals(oldCircuit)) {
                        circuits.put(k, circuits.get(positionB));
                    }
                }
            }
            if (new HashSet<>(circuits.values()).size() == 1 && circuits.size() == jBoxes.size()) {
                return positionA.x() * positionB.x();
            }
        }
        throw new IllegalStateException();
    }

    private static List<PairDistance> calculateDistances(List<Position> jBoxes, long limit) {
        List<PairDistance> distances = new ArrayList<>();
        for (int i = 0; i < jBoxes.size(); i++) {
            for (int j = i + 1; j < jBoxes.size(); j++) {
                PairDistance pairDistance = new PairDistance(jBoxes.get(i).distanceSquare(jBoxes.get(j)), new Pair<>(jBoxes.get(i), jBoxes.get(j)));
                distances.add(pairDistance);
            }
        }
        distances = distances.stream().sorted(Comparator.comparingLong(PairDistance::distance)).limit(limit).toList();
        return distances;
    }
}