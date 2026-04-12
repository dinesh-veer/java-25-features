package com.dinesh.trickyExamples;

public class ThreadExample {

    static void main() throws InterruptedException {

        System.out.println("---------------------------------------------------");

        ThreadLocal<String> tl = new ThreadLocal<>();
        tl.set("A");

        Thread vThread =Thread.startVirtualThread(() -> {
            System.out.println(tl.get()); // null : as virtual thread cann't get thread local values
        });//use scoped values as an alternative

        vThread.join();

        Thread virtual = Thread.startVirtualThread(() -> {
            try {
                Thread.sleep(1000);//✅ No: this is not Blocking, as Virtual threads release carrier thread
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        virtual.join();

        System.out.println("---------------------------------------------------");
    }
}
