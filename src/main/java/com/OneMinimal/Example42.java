// Joy Albertini
package com.OneMinimal;

import java.util.function.Function;

public class Example42 {
    public static void main(String[] args) {
        Function<String, Boolean> failingCondition = test -> test.matches(".*42.*");

        TableCreation tableCreation = new TableCreation();
        OneMinimal oneMinimal = new OneMinimal(tableCreation, failingCondition);

        String minimalFailingInput = oneMinimal.getMinimalFailingString(2, "2424");
        System.out.println("1-Minimal Result: " + minimalFailingInput);

        tableCreation.printTable();
    }
}
