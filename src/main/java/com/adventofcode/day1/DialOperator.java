package com.adventofcode.day1;

import java.util.List;

class DialOperator {
    long countStopAtZero(List<Rotation> rotations) {
        long result = 0;
        long dial = 50;
        for (Rotation rotation : rotations) {
            dial = rotation.rotate(dial);
            if (dial % 100 == 0) {
                result++;
            }
        }
        return result;
    }

    long countPassingZero(List<Rotation> rotations) {
        long result = 0;
        long dial = 50;
        for (Rotation rotation : rotations) {
            while (rotation.steps() > 0) {
                rotation = new Rotation(rotation.isRight(), rotation.steps() - 1);
                dial = rotation.rotateBy(dial, 1);
                if (dial % 100 == 0) {
                    result++;
                }
            }
        }
        return result;
    }
}
