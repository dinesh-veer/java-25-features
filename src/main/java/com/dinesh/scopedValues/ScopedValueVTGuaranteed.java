package com.dinesh.scopedValues;

import java.lang.ScopedValue;
import java.util.concurrent.StructuredTaskScope;

import static java.lang.ScopedValue.where;

public class ScopedValueVTGuaranteed {

    static final ScopedValue<String> REQ_ID = ScopedValue.newInstance();

    public static void main(String[] args) throws Exception {

        where(REQ_ID, "REQ-101").run(() -> {

            log("start");

            try (var scope =
                  StructuredTaskScope.open()) {

                scope.fork(() -> {
                    log("inside virtual thread");
                    return null;
                });

                try {
                    scope.join();           // waits for child

   //                 scope.throwIfFailed();  // propagates failure
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
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
