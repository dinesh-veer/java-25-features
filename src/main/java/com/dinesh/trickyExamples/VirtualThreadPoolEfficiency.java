package com.dinesh.trickyExamples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadPoolEfficiency {

    static void main(String[] args) {

        System.out.println("---------------------------------------------------");

        long starttime = System.currentTimeMillis();
        //This is OS extensive tasks not efficient as virtual thread
        //roughly takes 2020 ms
        // 1. Create a Fixed Thread Pool with exactly 10 standard OS threads
        try (ExecutorService executor = Executors.newFixedThreadPool(10)) {

            // We submit 15 tasks again to see what happens when we exceed the pool size
            for (int i = 1; i <= 15; i++) {
                int taskId = i;

                executor.submit(() -> {
                    try {
                        // Print thread info before sleeping
                        System.out.println("Task " + taskId + " started on: " + Thread.currentThread().getName());

                        // 2. The Blocking Operation
                        // The entire OS thread is completely frozen here. It cannot be reused
                        // by the executor until this sleep finishes.
                        Thread.sleep(1000);

                        // Print thread info after waking up
                        System.out.println("Task " + taskId + " finished on: " + Thread.currentThread().getName());

                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.err.println("Task interrupted");
                    }
                });
            }
        }

        System.out.println("---------------------------------------------------");
        System.out.println("All tasks completed.");
        System.out.println("Time taken to execute above code : " + (System.currentTimeMillis() - starttime) + " ms");

        System.out.println("---------------------------------------------------");

        //Using Virtual threads takes less time compared to normal thread
        // roughfly takes 1020 ms
        // Record the start time
        long startTime1 = System.currentTimeMillis();

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 1; i <= 15; i++) {
                int taskId = i;

                executor.submit(() -> {
                    try {
                        System.out.println("Task " + taskId + " started on: " + Thread.currentThread());
                        Thread.sleep(1000);
                        System.out.println("Task " + taskId + " finished on: " + Thread.currentThread());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.err.println("Task interrupted");
                    }
                });
            }
        } // The main thread blocks here until all virtual threads finish

        System.out.println("--------------------------------------------------");
        System.out.println("All tasks completed.");
        System.out.println("Total Virtual Thread Execution Time: " + (System.currentTimeMillis()-startTime1) + " ms");
        System.out.println("--------------------------------------------------");
    }
}