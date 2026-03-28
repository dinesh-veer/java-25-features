package com.dinesh.trickyExamples;

import java.util.concurrent.StructuredTaskScope;

//First task fails
//
//Second task gets cancelled
public class StrctureTaskScopeExample {

    static void main() throws InterruptedException {
            // 1. Use open() instead of 'new StructuredTaskScope.ShutdownOnFailure()'
            try (var scope = StructuredTaskScope.open()) {

                scope.fork(() -> {
                    throw new RuntimeException("Fail");
                });

                scope.fork(() -> {
                    Thread.sleep(1000);
                    return "Success";
                });

                // 2. Just call join().
                // If the first task fails, join() will automatically cancel the
                // second task and throw a StructuredTaskScope.FailedException.
                scope.join();
            }
    }
}
