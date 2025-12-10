package com.adventofcode.day10;

import java.util.List;

class LightDiagram {
    int diagram = 0;

    LightDiagram(String diagramString) {
        String[] diagramLights = diagramString.substring(1, diagramString.length() - 1).split("");
        for (int i = 0; i < diagramLights.length; i++) {
            String s = diagramLights[i];
            if (!".".equals(s) && !"#".equals(s)) {
                throw new IllegalArgumentException();
            }
            if ("#".equals(s)) {
                this.diagram |= 1 << i;
            }
        }
    }

    boolean isValid(List<Integer> buttons) {
        int result = 0;
        for (int button : buttons) result ^= button;
        return this.diagram == result;
    }
}
