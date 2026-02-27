package com.dinesh.scopedValues;

public class ChildVirtualThread {

    static final ScopedValue<String> REQ_ID = ScopedValue.newInstance();

     static void main(String[] args) throws Exception {

        ScopedValue.where(REQ_ID, "REQ-101").run(() -> {

            log("start");

            Thread vt = Thread.startVirtualThread(() -> {
                log("inside virtual thread");
            });

            try {
                vt.join();   // ✅ ensure scope is still active
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            log("end");
        });
    }

    static void log(String msg) {
        System.out.println(
                "[" + REQ_ID.get() + "] " + msg +
                        " | " + Thread.currentThread()
        );
    }
}
