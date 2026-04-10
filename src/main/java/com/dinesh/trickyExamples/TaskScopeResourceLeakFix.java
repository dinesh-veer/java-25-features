package com.dinesh.trickyExamples;

import java.util.concurrent.StructuredTaskScope;

/**
 * Never instantiate a StructuredTaskScope (whether using new in Java 21 or
 * .open() in Java 25) outside of a try-with-resources block!
 */
public class TaskScopeResourceLeakFix {

    public static void resourceLeak() {
        // ❌ DANGER: No try-with-resources
        var scope = StructuredTaskScope.open(); // (Using Java 25 syntax)

        scope.fork(() -> {
            Thread.sleep(5000); // This thread will run for 5 seconds
            return "Hi";
        });

        // An unexpected error crashes the main thread right here!
        int x = 10 / 0;

        // The code never reaches scope.close() or scope.join().
        // The 5-second task is now a zombie, leaking memory!
    }

    public static void resourceLeakFix() {
        // 👉 FIX: The try-with-resources block guarantees safe cleanup
        try (var scope = StructuredTaskScope.open()) {

            var task = scope.fork(() -> {
                Thread.sleep(1000);
                return "Hi";
            });

            scope.join();
            System.out.println("Result: " + task.get());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // 🔒 The moment we hit this closing brace, scope.close() is called.
        // If 'task' was somehow still running, it is instantly terminated.
    }

    static void main(String[] args) {

        System.out.println("---------------------------------------------------");

        //resourceLeak(); //method shows resource leak example

        resourceLeakFix();//method fix resource leak example

        System.out.println("---------------------------------------------------");


    }
}
