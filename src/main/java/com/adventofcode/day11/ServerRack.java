package com.adventofcode.day11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class ServerRack {
    private static final Map<String, Long> CACHE = new HashMap<>(); 
    
    public long countPaths(Map<String, Device> input, String entry, String out, List<String> mandatoryNodes) {
        CACHE.clear();
        return countPaths(input, input.get(entry), out, mandatoryNodes);
    }

    private long countPaths(Map<String, Device> graph, Device current, String out, List<String> mandatoryNodes) {
        if(CACHE.containsKey(calculateId(current.name(), mandatoryNodes))) return CACHE.get(calculateId(current.name(), mandatoryNodes));
        
        if (current.outputs().size() == 1 && out.equals(current.outputs().getFirst())) {
            return mandatoryNodes.isEmpty() ? 1 : 0;
        }
        
        List<String> currentMandatoryNodes = new ArrayList<>(mandatoryNodes);
        currentMandatoryNodes.remove(current.name());
        
        return current.outputs().stream().mapToLong(name -> {
            long result = countPaths(graph, graph.get(name), out, currentMandatoryNodes);
            CACHE.put(calculateId(name, currentMandatoryNodes), result);
            return result;
        }).sum();
    }
    
    String calculateId(String name, List<String> mandatoryNodes) {
        return name + "_" + mandatoryNodes.stream().sorted().collect(Collectors.joining());
    }
}

// 15506631 - too low