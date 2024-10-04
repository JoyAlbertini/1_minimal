//Joy Albertini
/*
1. Start with n = 2 and 𝝙 (CF) as test
2. Test each 𝝙i and each ∇i
3. Three possible outcomes:
    a. Some 𝝙i causes failure: Goto 1 with 𝝙 = 𝝙i and n = 2
    b. Some ∇i causes failure: Goto 1 with 𝝙 = ∇i and n = n - 1
    c. No test causes failure: If granularity can be redefined (n*2 <= | 𝝙 |)
        go tostep 1 with 𝝙 = 𝝙 and n = n * 2
    Otherwise: Done, return the 1-minimum test

//corner case

private static boolean doesTheTestFails(String test) {
    if (test.matches(regex: *<select.*>.**))
        return true;
    else
        return false;
}
 */

package com.OneMinimal;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class OneMinimal {
    private final TableCreation tableCreation;
    private static int index = 1;

    Function<String,Boolean> failingCondition;


    public OneMinimal(TableCreation tableCreation, Function<String,Boolean> failingCondition) {
        this.tableCreation = tableCreation;
        this.failingCondition = failingCondition;
    }

    public String getMinimalFailingString(int n, String str) {
        return computeRecursive(n, str, n);
    }

    private String computeRecursive(int n, String delta, int initial_n) {
        List<String> deltas = generateDeltas(delta, n);
        List<String> lambdas = generateLambdas(delta, deltas);

        tableCreation.addTableEntry(index, n, delta, deltas, lambdas, null);

        // Some 𝝙i causes failure: Goto 1 with 𝝙 = 𝝙i and n = 2
        for (String delta_i : deltas) {
            if (testFailsInput(delta_i)) {
                tableCreation.addTableEntry(index++, n, delta, deltas, lambdas, "Δi causes failure");
                return computeRecursive(initial_n, delta_i, initial_n);
            }
        }
        // Some ∇i causes failure: Goto 1 with 𝝙 = ∇i and n = n - 1
        for (String lambdas_i : lambdas) {
            if (testFailsInput(lambdas_i)) {
                tableCreation.addTableEntry(index++, n, delta, deltas, lambdas, "∇i causes failure");
                return computeRecursive(n-1, lambdas_i, initial_n);
            }
        }
        // No test causes failure and granularity can be redefined as (n*2 <= | 𝝙 |)
        if (n * 2 <= delta.length()) {
            tableCreation.addTableEntry(index++, n, delta, deltas, lambdas, "Increase granularity");
            return computeRecursive(n * 2, delta, initial_n);
        } else {
            // Done, return the 1-minimum test
            tableCreation.addTableEntry(index++, n, delta, deltas, lambdas, "Minimal failing input found");
            return delta;
        }
    }

    // Failing Condition
    private boolean testFailsInput(String test) {
        return failingCondition.apply(test);
        // test.matches("(?i).*<select.*>.*");
    }

    private List<String> generateDeltas(String delta, int n) {
        int length = delta.length();
        List<String> deltas = new ArrayList<>();
        int baseChunkSize = length / n;
        int remainder = length % n;
        int start = 0;

        // Splits the input string delta into n contiguous chunks
        for (int i = 0; i < n; i++) {
            int extraChar = (i < remainder) ? 1 : 0;
            int end = start + baseChunkSize + extraChar;
            deltas.add(delta.substring(start, end));
            start = end;
        }

        return deltas;
    }


    private List<String> generateLambdas(String delta, List<String> deltas) {
        List<String> complements = new ArrayList<>();
        int start = 0;

        for (String delta_i : deltas) {
            int end = start + delta_i.length();

            // Remove delta_i from delta by its position
            String complement = delta.substring(0, start) + delta.substring(end);
            complements.add(complement);

            start = end;
        }

        return complements;
    }
}
