package com.adventofcode.day6;

import com.adventofcode.common.StringMatrix;

import java.util.ArrayList;
import java.util.List;

class MathHomework {
    long resolve(List<List<Long>> problems, List<String> operators) {
        long result = 0;
        for(int i = 0; i < problems.size(); i++) {
            List<Long> problem = problems.get(i);
            String operator = operators.get(i);
            result += problem.stream()
                    .skip(1)
                    .reduce(problem.getFirst(), (a, b) -> "+".equals(operator)? a + b : a * b);
        }
        return result;
    }

    long decodeAndResolve(StringMatrix matrix) {
        List<List<Long>> problems = new ArrayList<>();
        List<String> operators = new ArrayList<>();
        List<Long> problem = new ArrayList<>();
        String operator = null;

        boolean isSeparator = true;
        for(int x = matrix.width() - 1; x >= 0; x--) {
            StringBuilder number = new StringBuilder();
            isSeparator = true;
            for(int y = matrix.height() - 1; y >= 0; y--) {
                if(!matrix.at(x, y).equals(" ")) {
                    isSeparator = false;
                    if(y == matrix.height() - 1) {
                        operator = matrix.at(x, y);
                    } else {
                        number.append(matrix.at(x, y));
                    }
                }
            }
            if(isSeparator) {
                if(operator == null) {
                    throw new IllegalStateException();
                }
                problems.add(problem);
                operators.add(operator);
                problem = new ArrayList<>();
                operator = null;
            } else {
                problem.add(Long.parseLong(number.reverse().toString().trim()));
            }
        }
        problems.add(problem);
        operators.add(operator);

        return resolve(problems, operators);
    }
}