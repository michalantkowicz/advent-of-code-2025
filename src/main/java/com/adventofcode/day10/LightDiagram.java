package com.adventofcode.day10;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class LightDiagram {
    Map<Integer, Boolean> diagram = new HashMap<>();

    LightDiagram(String diagramString) {
        int i = 0;
        for (String s : diagramString.substring(1, diagramString.length() - 1).split("")) {
            if(!".".equals(s) && !"#".equals(s)) {
                throw new IllegalArgumentException();
            }
            this.diagram.put(i++, "#".equals(s));
        }
    }
    
    boolean isValid(List<List<Integer>> buttons) {
        Map<Integer, Boolean> result = new HashMap<>();
        for(int key : diagram.keySet()) {
            result.put(key, false);
        }
        
        for(List<Integer> button : buttons) {
            for(int light : button) {
                result.compute(light, (k, v) -> v == null || !v);
            }
        }
        
        for(int key : result.keySet()) {
            if(!result.get(key).equals(diagram.get(key))) {
                return false;
            }
        }
        return true;
    }
}
