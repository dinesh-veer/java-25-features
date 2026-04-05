package com.dinesh.trickyExamples;

import java.util.concurrent.StructuredTaskScope;

public class StructuredConcurrencyDemo {
    public static void main(String[] args) {


        System.out.println("---------------------------------------------------");

        try (var scope = StructuredTaskScope.open()) {
            
            // 1. Fork the task
            var subtask = scope.fork(() -> {
                Thread.sleep(1000); // Task takes 1 second to finish
                return "Hi";
            });

            /*
             * ❓ Problem? 
             * We skipped scope.join(), so the main thread does not wait.
             * It immediately runs the next line while the subtask is still sleeping.
             */


            // ❌ CRASH: IllegalStateException
            // The result is not ready yet!
            System.out.println("Trying to read early: " + subtask.get());

            //to fix this uncomment below code
            // 👉 FIX: Block the main thread here until the task finishes
//            scope.join();
//
//            // ✅ Safe: The task is guaranteed to be finished, so we can read the result.
//            if (subtask.state() == StructuredTaskScope.Subtask.State.SUCCESS) {
//                System.out.println("Result: " + subtask.get());
//            } else {
//                System.out.println("Task failed or was cancelled.");
//            }
//
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
        } catch (IllegalStateException e) {

            System.out.println("❌ CRASH! IllegalStateException");
            System.out.println("👉 Reason: " + e.getMessage());

        }


        System.out.println("---------------------------------------------------");
    }
}