package com.dinesh.trickyExamples;

import java.util.concurrent.StructuredTaskScope;

public class StructuredScopeFix {
     static void main(String[] args) {

        System.out.println("---------------------------------------------------");
        // Use Java 25 .open() instead of new
        try (var scope = StructuredTaskScope.open()) {

            // Fork the task
            var task = scope.fork(() -> {
                Thread.sleep(500); // Simulate work
                return "Hello";
            });

            /*
             * ❓ Problem? We commented out join()!
             * ❌ Task may never complete before the next line runs
             * 👉 Must call join()
             */
             //scope.join();

            // ❌ DANGER: Because we didn't join(), the scope is not synchronized.
            // This line will instantly crash the program!
            System.out.println("Result: " + task.get());

            System.out.println("---------------------------------------------------");

        } catch (IllegalStateException e ) {//| InterruptedException e) {//required for join call

            // Java 25 catches your mistake and throws this exception!
            System.err.println("CRASH! IllegalStateException thrown.");
            System.err.println("Reason: " + e.getMessage());

            System.err.println("---------------------------------------------------");
        }
    }
}