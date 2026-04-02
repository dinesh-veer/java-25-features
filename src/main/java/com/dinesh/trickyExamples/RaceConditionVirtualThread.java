package com.dinesh.trickyExamples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class RaceConditionVirtualThread {

    // Shared mutable state - NOT thread-safe!
    static int counter = 0;

    // Thread-safe counter
    static AtomicInteger counterAutomic = new AtomicInteger(0);

    static int counterSync = 0;

    // An object to act as our "lock" or "key"
    static final Object lock = new Object();

    static void main(String[] args) throws InterruptedException {


        System.out.println("----------------------------------------------------");

        System.out.println("Without Thread safe");

        // try-with-resources ensures executor.close() is called,
        // which waits for all virtual threads to complete.
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 1000; i++) {
                executor.submit(() -> {
                    counter++; // The race condition happens right here
                });
            }
        }

        System.out.println("Expected counter value: 1000");
        System.out.println("Actual counter value:   " + counter);
        //likely get a different result every time you run it, and it will rarely be exactly 1000.
        // You might see 984, 992, or 970.

        //Even though two threads just did their job, the counter only went up by one. This is called a
        // lost update. Because it's completely random when and how often the threads overlap like this,
        // you lose a random number of updates every time you run the program, which is why your final total
        // fluctuates (e.g., 984, 992, 970).

        System.out.println("----------------------------------------------------");
        System.out.println("----------------------------------------------------");

        System.out.println("With Thread safe");

        //below is correct example with Automic
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 1000; i++) {
                executor.submit(() -> {
                    counterAutomic.incrementAndGet(); // This is an atomic, thread-safe operation
                });
            }
        }

        System.out.println("Expected counter value: 1000");
        System.out.println("Actual counter value:   " + counterAutomic.get());
        //The output will confidently and reliably print 1000 every single time.

        //operations are never allowed to overlap or interleave, no updates are ever lost.
        // Every single one of the 1,000 threads successfully adds its 1, meaning the final output
        // is guaranteed to be exactly 1,000 every single time.
        System.out.println("----------------------------------------------------");
        System.out.println("Using Synchronisation");

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 1000; i++) {
                executor.submit(() -> {

                    // Only one thread can enter this block at a time.
                    // The other 999 threads must wait outside until the lock is released.
                    synchronized (lock) {
                        counterSync++; // Read, Add, and Write happen safely
                    }

                });
            }
        }

        System.out.println("Expected counter value: 1000");
        System.out.println("Actual counter value:   " + counterSync);

        System.out.println("---------------------------------------------------");


    }
}
