package com.dinesh.trickyExamples;

public class VirtaualThreadIdentity {

    static void main(String[] args) {

        System.out.println("---------------------------------------------------");

        Thread t1 = Thread.startVirtualThread(() -> {});
        Thread t2 = Thread.startVirtualThread(() -> {});

        System.out.println("Thread t1 and t2 are equal : "+ (t1 == t2));//false : Each virtual thread is distinct

        System.out.println("---------------------------------------------------");
    }
}
