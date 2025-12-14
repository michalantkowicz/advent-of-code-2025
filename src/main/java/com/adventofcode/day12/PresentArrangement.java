package com.adventofcode.day12;

import com.adventofcode.common.StringMatrix;

import java.util.*;
import java.util.stream.Collectors;

class PresentArrangement {
    static boolean ANY_FOUND = false;

    long resolve(List<Region> regions) {
        List<Region> possibleRegions = regions.stream()
                .filter(Region::hasEnoughSpace)
                .toList();

        long result = 0;

        for (Region possibleRegion : possibleRegions) {
            ANY_FOUND = false;
            Set<PackedRegion> allRegions = getPacked(new PackedRegion(possibleRegion), 0, 0);
            if (!allRegions.isEmpty()) {
                result++;
            }
        }
        return result;
    }

    private Set<PackedRegion> getPacked(PackedRegion region, int x, int y) {
        if (ANY_FOUND || region.matrix() == null || x >= region.matrix().width() || y >= region.matrix().height()) {
            return Collections.emptySet();
        } else if (region.allPacked()) {
            ANY_FOUND = true;
            return Set.of(region);
        }

        Set<PackedRegion> result = new HashSet<>();
        Set<Present> expectedPresentsType = region.expectedPresents().entrySet().stream()
                .filter(e -> e.getValue() > 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        for (Present present : expectedPresentsType) {
            for (StringMatrix manipulation : present.manipulations()) {
                StringMatrix newMatrix = place(new StringMatrix(region.matrix()), manipulation, present.id(), x, y);
                Map<Present, Integer> newMap = new HashMap<>(region.expectedPresents());
                if (newMatrix != null) {
                    newMap.compute(present, (k, v) -> v - 1);
                }
                for (int x2 = x + 1; x2 < region.matrix().width(); x2++) {
                    result.addAll(getPacked(new PackedRegion(newMatrix, newMap), x2, y));
                }
                for (int y2 = y + 1; y2 < region.matrix().height(); y2++) {
                    for (int x2 = 0; x2 < region.matrix().width(); x2++) {
                        result.addAll(getPacked(new PackedRegion(newMatrix, newMap), x2, y2));
                    }
                }
            }
        }
        return result;
    }

    private StringMatrix place(StringMatrix matrix, StringMatrix manipulation, String id, int x, int y) {
        if (matrix.width() < x + manipulation.width() || matrix.height() < y + manipulation.height()) {
            return null;
        } else {
            for (int x2 = 0; x2 < manipulation.width(); x2++) {
                for (int y2 = 0; y2 < manipulation.height(); y2++) {
                    String character = manipulation.at(x2, y2);
                    if (!".".equals(character)) {
                        if (!".".equals(matrix.at(x + x2, y + y2))) {
                            return null;
                        } else {
                            matrix.set(x + x2, y + y2, id);
                        }
                    }
                }
            }
        }
        return matrix;
    }
}