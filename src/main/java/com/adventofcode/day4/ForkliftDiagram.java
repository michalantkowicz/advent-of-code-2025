package com.adventofcode.day4;

import com.adventofcode.common.Pair;
import com.adventofcode.common.StringMatrix;

import java.util.List;

class ForkliftDiagram {
    long countAccessibleRolls(StringMatrix diagram) {
        return filterAccessibleRolls(diagram).size();
    }

    long countAccessibleRollsWithIterations(StringMatrix diagram) {
        long result = 0L;
        List<Pair<Integer>> accessibleRolls = filterAccessibleRolls(diagram);
        while (!accessibleRolls.isEmpty()) {
            result += accessibleRolls.size();
            accessibleRolls.forEach(p -> diagram.set(p.a(), p.b(), "."));
            accessibleRolls = filterAccessibleRolls(diagram);
        }
        return result;
    }

    private List<Pair<Integer>> filterAccessibleRolls(StringMatrix diagram) {
        return diagram.streamIndices()
                .filter(p -> "@".equals(diagram.at(p)))
                .filter(p -> diagram.streamAdjacentIndices(p).filter(a -> "@".equals(diagram.at(a))).count() < 4)
                .toList();
    }
}