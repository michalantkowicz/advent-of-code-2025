package com.adventofcode.day12;

import com.adventofcode.common.StringMatrix;

import java.util.HashMap;
import java.util.Map;

record PackedRegion(StringMatrix matrix, Map<Present, Integer> expectedPresents) {
    PackedRegion(Region region) {
        this(
                new StringMatrix((".".repeat(region.width()) + "\n").repeat(region.height())), 
                new HashMap<>(region.expectedPresents())
        );
    }
    
    boolean allPacked() {
        return expectedPresents.values().stream().noneMatch(i -> i != 0);
    }
}
