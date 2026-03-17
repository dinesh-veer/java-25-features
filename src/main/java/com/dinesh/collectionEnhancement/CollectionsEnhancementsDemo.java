package com.dinesh.collectionEnhancement;// File: CollectionsEnhancementsDemo.java

import java.util.ArrayList;
import java.util.List;

public class CollectionsEnhancementsDemo {
     static void main(String[] args) {
        System.out.println("----------------------------------------------------");

        List<String> list = new ArrayList<>(List.of("alpha", "beta", "gamma"));

        // Example: getFirst / getLast semantics (if your JDK provides them)
        // If the API provides getFirst/getLast, they'd behave like:
        String first = list.getFirst(); // equivalent to getFirst()
        String last  = list.getLast(); // equivalent to getLast()

        System.out.println("First: " + first);
        System.out.println("Last: " + last);

        System.out.println("----------------------------------------------------");

        // reversed view (if API provides a reversed() convenience)
        // Fallback: Collections.reverse to demonstrate the idea
        //List<String> reversed = new ArrayList<>(list);
        //Collections.reverse(reversed);
        List<String> reversed = list.reversed();
        System.out.println("Reversed: " + reversed);

        System.out.println("----------------------------------------------------");

        // Immutable convenience: List.copyOf and List.of remain useful
        List<String> immutable = List.copyOf(list);
        System.out.println("Immutable: " + immutable);
        List<String> immutable1 = List.of("alpha", "beta", "gamma");
        System.out.println("Immutable1: " + immutable1);

        System.out.println("----------------------------------------------------");
    }
}
