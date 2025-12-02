package com.adventofcode.day2;

import com.adventofcode.common.Pair;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.LongStream;

class ProductProcessor {
    long sumInvalidIds(List<Pair<Long>> ranges) {
        return ranges.stream()
                .flatMapToLong(r -> LongStream.range(r.a(), r.b() + 1))
                .filter(this::isInvalid)
                .sum();
    }

    long sumInvalidIdsWithNewRule(List<Pair<Long>> ranges) {
        return ranges.stream()
                .flatMapToLong(r -> LongStream.range(r.a(), r.b() + 1))
                .filter(this::isInvalidNewRule)
                .sum();
    }

    private boolean isInvalid(Long id) {
        String idString = String.valueOf(id);
        int len = idString.length();
        return len % 2 == 0 && idString.substring(0, len / 2).equals(idString.substring(len / 2));
    }

    private boolean isInvalidNewRule(Long id) {
        String idString = String.valueOf(id);
        int len = idString.length();
        for (int i = 1; i <= idString.length() / 2; i++) {
            if (len % i == 0 && splitByN(idString, i).size() == 1) {
                return true;
            }
        }
        return false;
    }

    private Set<String> splitByN(String string, int n) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < string.length(); i += n) {
            result.add(string.substring(i, i + n));
        }
        return result;
    }
}
