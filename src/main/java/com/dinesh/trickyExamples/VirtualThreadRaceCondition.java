package com.dinesh.trickyExamples;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class VirtualThreadRaceCondition {
    // A standard, thread-unsafe integer
    private static int counter = 0;

    // Thread-safe integer
    private static final AtomicInteger atomic = new AtomicInteger(0);

    // 1. Initialize the LongAdder
    // It starts at 0 by default.
    private static final LongAdder longAdder = new LongAdder();

    static void main(String[] args) {

        System.out.println("----------------------------------------------------");

        long startTime = System.currentTimeMillis();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

                // Spawn 100,000 virtual threads
                for (int i = 0; i < 100_000; i++) {
                    executor.submit(() -> {
                        // ❌ DANGER: Thousands of threads are overwriting each other
                        counter++;
                    });
                }
            } // Waits for all threads to finish

            // You expect 100,000. You will likely get something like 86,421!
            System.out.println("Final Counter in Unsafe way: " + counter);
            System.out.println("Time taken in unsafe way: " + (System.currentTimeMillis() - startTime) + " ms");

        System.out.println("----------------------------------------------------");

        startTime = System.currentTimeMillis();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            for (int i = 0; i < 100_000; i++) {
                executor.submit(() -> {
                    // ✅ SAFE: Threads line up perfectly to increment
                    atomic.incrementAndGet();
                });
            }
        }

        // This is guaranteed to print exactly 100,000 every single time.
        System.out.println("Final Counter in Atomic integer: " + atomic.get());
        System.out.println("Time taken in Atomic way: " + (System.currentTimeMillis() - startTime) + " ms");

        System.out.println("----------------------------------------------------");

        startTime = System.currentTimeMillis();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            // Spawn 100,000 virtual threads
            for (int i = 0; i < 100_000; i++) {
                executor.submit(() -> {
                    // 2. SAFE and ULTRA-FAST: Threads do not block each other!
                    longAdder.increment();
                });
            }
        } // The main thread waits here for all 100,000 tasks to finish

        long endTime = System.currentTimeMillis();

        // 3. Get the final result using .sum()
        // This adds up all the hidden internal variables to give you the grand total.
        System.out.println("Final Counter using LongAdder: " + longAdder.sum());
        System.out.println("Time taken using LongAdder: " + (endTime - startTime) + " ms");

        System.out.println("----------------------------------------------------");
    }
}
