package com.adventofcode.day10;

import java.util.*;

class Factory {
    long resolve(List<Machine> machines) {
        return machines.stream().mapToLong(this::calculateMinButtons).sum();
    }

    private int calculateMinButtons(Machine machine) {
        int min = 20;
        for (int button : machine.buttonsIntegers()) {
            min = Math.min(min, inspect(machine, List.of(button), 1, min));
        }
        return min;
    }

    private int inspect(Machine machine, List<Integer> current, int depth, int maxDepth) {
        if (depth >= maxDepth) {
            return maxDepth;
        } else if (machine.lightDiagram().isValid(current)) {
            return depth;
        } else {
            int min = maxDepth;
            for (int button : machine.buttonsIntegers()) {
                if (button != current.getLast()) {
                    List<Integer> temp = new ArrayList<>(current);
                    temp.add(button);
                    min = Math.min(min, inspect(machine, temp, depth + 1, min));
                }
            }
            return min;
        }
    }

    static int GLOBAL_DEPTH;

    long resolveForJoltage(List<Machine> machines) {
        long result = 0L;
        for(int i = 0; i < machines.size(); i++) {
            GLOBAL_DEPTH = 1000;
            int min = calculateMinButtonsForJoltage(machines.get(i));
            System.out.println("machine " + i + ": " + min);
            result += min;
        }
        return result;
    }

    private int calculateMinButtonsForJoltage(Machine machine) {
        DimensionWithButtons minDimension = machine.minDimensionWithButtons();
        if(minDimension.buttons().isEmpty()) return GLOBAL_DEPTH;
        Set<List<Button>> allPossibleCombinations = switch (minDimension.buttons().size()) {
            case 1 -> getAllPossibleCombinations1(minDimension);
            case 2 -> getAllPossibleCombinations2(minDimension);
            case 3 -> getAllPossibleCombinations3(minDimension);
            case 4 -> getAllPossibleCombinations4(minDimension);
            case 5 -> getAllPossibleCombinations5(minDimension);
            case 6 -> getAllPossibleCombinations6(minDimension);
            case 7 -> getAllPossibleCombinations7(minDimension);
            default -> throw new IllegalStateException();
        };
        for (List<Button> combination : allPossibleCombinations) {
            Machine newMachine = machine.of(minDimension, combination);

            if (newMachine.isEmpty()) {
                GLOBAL_DEPTH = Math.min(GLOBAL_DEPTH, newMachine.depth());
            }
            else if (newMachine.depth() < GLOBAL_DEPTH && newMachine.isValid()) {
                    calculateMinButtonsForJoltage(newMachine);
            }
            //List<String> ids = combination.stream().map(Button::id).toList();
            //min = Math.min(min, inspectJoltage(machine, ids, combination, combination.size(), min));
            // min = Math.min(min, inspectJoltage(machine, Collections.emptyList(), Collections.emptyList(), 0, min));
        }
        return GLOBAL_DEPTH;
    } //2200 - too low
//
//    private int inspectJoltage(Machine machine, List<String> combination, List<Button> current, int depth, int maxDepth) {
//        if (depth >= maxDepth) {
//            return maxDepth;
//        } else if (machine.joltageDiagram().isValid(current)) {
//            return depth;
//        } else if (!machine.joltageDiagram().isLower(current)) {
//            return maxDepth;
//        } else {
//            int min = maxDepth;
//            for (Button button : machine.buttons()) {
//                if (!combination.contains(button.id())) {
//                    List<Button> temp = new ArrayList<>(current);
//                    temp.add(button);
//                    if (machine.joltageDiagram().isLower(temp)) {
//                        min = Math.min(min, inspectJoltage(machine, combination, temp, depth + 1, min));
//                    }
//                }
//            }
//            return min;
//        }
//    }

    private Set<List<Button>> getAllPossibleCombinations1(DimensionWithButtons minDimension) {
        List<Button> result = new ArrayList<>();
        insert(result, minDimension.buttons().get(0), minDimension.value());
        return Set.of(result);
    }

    private Set<List<Button>> getAllPossibleCombinations2(DimensionWithButtons minDimension) {
        Set<List<Button>> result = new HashSet<>();
        for (int i = 0; i <= minDimension.value(); i++) {
            for (int i1 = 0; i1 <= minDimension.value(); i1++) {
                if (i + i1 == minDimension.value()) {
                    List<Button> temp = new ArrayList<>();
                    insert(temp, minDimension.buttons().get(0), i);
                    insert(temp, minDimension.buttons().get(1), i1);
                    if (minDimension.diagram().isLower(temp)) {
                        result.add(temp);
                    }
                }
            }
        }
        return result;
    }

    private Set<List<Button>> getAllPossibleCombinations3(DimensionWithButtons minDimension) {
        Set<List<Button>> result = new HashSet<>();
        for (int i = 0; i <= minDimension.value(); i++) {
            for (int i1 = 0; i1 <= minDimension.value(); i1++) {
                for (int i2 = 0; i2 <= minDimension.value(); i2++) {
                    if (i + i1 + i2 == minDimension.value()) {
                        List<Button> temp = new ArrayList<>();
                        insert(temp, minDimension.buttons().get(0), i);
                        insert(temp, minDimension.buttons().get(1), i1);
                        insert(temp, minDimension.buttons().get(2), i2);
                        if (minDimension.diagram().isLower(temp)) {
                            result.add(temp);
                        }
                    }
                }
            }
        }
        return result;
    }

    private Set<List<Button>> getAllPossibleCombinations4(DimensionWithButtons minDimension) {
        Set<List<Button>> result = new HashSet<>();
        for (int i = 0; i <= minDimension.value(); i++) {
            for (int i1 = 0; i1 <= minDimension.value(); i1++) {
                for (int i2 = 0; i2 <= minDimension.value(); i2++) {
                    for (int i3 = 0; i3 <= minDimension.value(); i3++) {
                        if (i + i1 + i2 + i3 == minDimension.value()) {
                            List<Button> temp = new ArrayList<>();
                            insert(temp, minDimension.buttons().get(0), i);
                            insert(temp, minDimension.buttons().get(1), i1);
                            insert(temp, minDimension.buttons().get(2), i2);
                            insert(temp, minDimension.buttons().get(3), i3);
                            if (minDimension.diagram().isLower(temp)) {
                                result.add(temp);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    private Set<List<Button>> getAllPossibleCombinations5(DimensionWithButtons minDimension) {
        Set<List<Button>> result = new HashSet<>();
        for (int i = 0; i <= minDimension.value(); i++) {
            for (int i1 = 0; i1 <= minDimension.value(); i1++) {
                for (int i2 = 0; i2 <= minDimension.value(); i2++) {
                    for (int i3 = 0; i3 <= minDimension.value(); i3++) {
                        for (int i4 = 0; i4 <= minDimension.value(); i4++) {
                            if (i + i1 + i2 + i3 + i4 == minDimension.value()) {
                                List<Button> temp = new ArrayList<>();
                                insert(temp, minDimension.buttons().get(0), i);
                                insert(temp, minDimension.buttons().get(1), i1);
                                insert(temp, minDimension.buttons().get(2), i2);
                                insert(temp, minDimension.buttons().get(3), i3);
                                insert(temp, minDimension.buttons().get(4), i4);
                                if (minDimension.diagram().isLower(temp)) {
                                    result.add(temp);
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    private Set<List<Button>> getAllPossibleCombinations6(DimensionWithButtons minDimension) {
        Set<List<Button>> result = new HashSet<>();
        for (int i = 0; i <= minDimension.value(); i++) {
            for (int i1 = 0; i1 <= minDimension.value(); i1++) {
                for (int i2 = 0; i2 <= minDimension.value(); i2++) {
                    for (int i3 = 0; i3 <= minDimension.value(); i3++) {
                        for (int i4 = 0; i4 <= minDimension.value(); i4++) {
                            for (int i5 = 0; i5 <= minDimension.value(); i5++) {
                                if (i + i1 + i2 + i3 + i4 + i5 == minDimension.value()) {
                                    List<Button> temp = new ArrayList<>();
                                    insert(temp, minDimension.buttons().get(0), i);
                                    insert(temp, minDimension.buttons().get(1), i1);
                                    insert(temp, minDimension.buttons().get(2), i2);
                                    insert(temp, minDimension.buttons().get(3), i3);
                                    insert(temp, minDimension.buttons().get(4), i4);
                                    insert(temp, minDimension.buttons().get(5), i5);
                                    if (minDimension.diagram().isLower(temp)) {
                                        result.add(temp);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    private Set<List<Button>> getAllPossibleCombinations7(DimensionWithButtons minDimension) {
        Set<List<Button>> result = new HashSet<>();
        for (int i = 0; i <= minDimension.value(); i++) {
            for (int i1 = 0; i1 <= minDimension.value(); i1++) {
                for (int i2 = 0; i2 <= minDimension.value(); i2++) {
                    for (int i3 = 0; i3 <= minDimension.value(); i3++) {
                        for (int i4 = 0; i4 <= minDimension.value(); i4++) {
                            for (int i5 = 0; i5 <= minDimension.value(); i5++) {
                                for (int i6 = 0; i6 <= minDimension.value(); i6++) {
                                    if (i + i1 + i2 + i3 + i4 + i5 + i6 == minDimension.value()) {
                                        List<Button> temp = new ArrayList<>();
                                        insert(temp, minDimension.buttons().get(0), i);
                                        insert(temp, minDimension.buttons().get(1), i1);
                                        insert(temp, minDimension.buttons().get(2), i2);
                                        insert(temp, minDimension.buttons().get(3), i3);
                                        insert(temp, minDimension.buttons().get(4), i4);
                                        insert(temp, minDimension.buttons().get(5), i5);
                                        insert(temp, minDimension.buttons().get(6), i6);
                                        if (minDimension.diagram().isLower(temp)) {
                                            result.add(temp);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    void insert(List<Button> set, Button button, int count) {
        for (int i = 0; i < count; i++) set.add(new Button(button.id(), new ArrayList<>(button.indices())));
    }
}