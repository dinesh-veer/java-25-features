package com.dinesh.scopedValues;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExample {
    static final ScopedValue<String> USER = ScopedValue.newInstance();

     static void main(String[] args) throws Exception {
        try (ExecutorService exec = Executors.newVirtualThreadPerTaskExecutor()) {
            exec.submit(() -> ScopedValue.where(USER, "Alice").run(() -> {
                System.out.println("Executing Thread : " + Thread.currentThread() + " USER=" + USER.get());
            }));
            exec.submit(() -> ScopedValue.where(USER, "Bob").run(() -> {
                System.out.println("Executing Thread : " + Thread.currentThread() + " USER=" + USER.get());
            }));
            Thread.sleep(200);
        }
    }
}
