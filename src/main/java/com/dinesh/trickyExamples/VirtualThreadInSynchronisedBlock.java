package com.dinesh.trickyExamples;

import java.util.concurrent.Executors;

public class VirtualThreadInSynchronisedBlock {
    private static final Object lock = new Object();

    static void main() {


        System.out.println("---------------------------------------------------");

        // If 4 virtual threads execute this at the same time on a 4-core machine,
        // no other virtual threads can run for 1 second!
        Runnable problematicTask = () -> {
            synchronized (lock) { // ⚠️ Thread gets PINNED here
                try {
                    System.out.println(Thread.currentThread() + " is sleeping and pinning the carrier!");

                    // ❌ The carrier thread is now paralyzed. It cannot be reused.
                    Thread.sleep(10000);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } // Thread is unpinned when it exits the block
        };

        // 2. Execute the task
        // We use try-with-resources here so the main thread automatically waits
        // for all the virtual threads to finish before shutting down the program.
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            // Let's spawn 4 virtual threads to run the task simultaneously
            for (int i = 1; i <= 4; i++) {
                executor.submit(problematicTask);
            }
        }
        System.out.println("Program finished.");

        System.out.println("---------------------------------------------------");
    }
}
