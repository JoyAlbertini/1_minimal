package com.OneMinimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableCreation {
    private final List<Map<String, Object>> table = new ArrayList<>();

    public void addTableEntry(int iteration, int n, String delta, List<String> deltas, List<String> complements, String reason) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Iteration", iteration);
        entry.put("n", n);
        entry.put("Δ", deltaSummary(delta));
        entry.put("Δi", deltas != null ? arraySummary(deltas) : "");
        entry.put("∇i", complements != null ? arraySummary(complements) : "");
        entry.put("Reason", reason != null ? reason : "");
        table.add(entry);
    }

    public void printTable() {
        System.out.printf("%-10s %-5s %-20s %-20s %-20s %-25s%n", "Iteration", "n", "Δ", "Δi", "∇i", "Reason");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        for (Map<String, Object> entry : table) {
            System.out.printf("%-10d %-5d %-20s %-20s %-20s %-25s%n",
                    entry.get("Iteration"), entry.get("n"), entry.get("Δ"), entry.get("Δi"), entry.get("∇i"), entry.get("Reason"));
        }
    }

    private String deltaSummary(String delta) {
        return delta.length() > 15 ? delta.substring(0, 12) + "..." : delta;
    }

    private String arraySummary(List<String> list) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            String item = list.get(i);
            item = item.length() > 5 ? item.substring(0, 5) + "..." : item;
            sb.append(item);
            if (i < list.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
