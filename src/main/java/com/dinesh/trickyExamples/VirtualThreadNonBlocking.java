package com.dinesh.trickyExamples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadNonBlocking {

    static void main() throws InterruptedException {
        //Is below example is Blocking or Non-Blocking?
        // it is non-blocking as efficient and doesnt block OS level threads

        System.out.println("--------------------------------------");
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        for (int i = 0; i < 3; i++) {
            executor.submit(() -> {

                System.out.println(Thread.currentThread());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        System.out.println("--------------------------------------");
    }
}
