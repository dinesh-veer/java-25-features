package com.dinesh.trickyExamples;

public class ScopedValueWithVirtualThread {

    static final ScopedValue<String> USER = ScopedValue.newInstance();

    public static void main(String[] args) throws Exception {
        //ScopedValue is not automatically inherited by new threads
        //
        //Virtual threads don’t carry the binding unless explicitly propagated
        ScopedValue.where(USER, "Dinesh").run(() -> {
            Thread.startVirtualThread(() -> {
                // The virtual thread will now have time to run,
                // and it will crash here because USER is not bound.
                System.out.println(USER.get());
                //NoSuchElementException: ScopedValue not bound
            });

            try {
                // Force the main thread to wait for 1 second before exiting
                Thread.sleep(1000);
            } catch (InterruptedException e) { }
        });


        //to fix above solution we can use below approach

//
//        ScopedValue.where(USER, "Dinesh").run(() -> {
//
//            // 1. Capture the value from the parent thread's scope
//            String capturedUser = USER.get();
//
//            Thread vThread = Thread.startVirtualThread(() -> {
//                // 2. Re-bind the value to a new scope INSIDE the virtual thread
//                ScopedValue.where(USER, capturedUser).run(() -> {
//
//                    System.out.println(USER.get()); // Successfully prints "Dinesh"
//
//                });
//            });
//
//            // 3. Wait for the virtual thread to finish so the JVM doesn't exit silently
//            try {
//                vThread.join();
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        });

//        ScopedValue.where(USER, "Dinesh").run(() -> {
//
//            // 1. Use StructuredTaskScope.open() instead of the 'new' keyword
//            try (var scope = StructuredTaskScope.open()) {
//
//                // 2. You can now pass a standard Runnable (no 'return null;' needed)
//                scope.fork(() -> {
//                    System.out.println(USER.get()); // Safely prints "Dinesh"
//                });
//
//                // 3. Wait for the task to finish
//                scope.join();
//
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//
//        });
    }
}