package com.dinesh.scopedValues;

import static java.lang.ScopedValue.where;

public class ScopedValueExample {
    static final ScopedValue<String> USER = ScopedValue.newInstance();

     static void main(String[] args) {

         System.out.println("---------------------------------------------");

         //The binding exists only inside the run block.
            where(USER, "Dinesh").run(() ->
            {
                System.out.println("User Inside scope: " + USER.get());// Alice
                nested();
            });
            // USER.get(); // ❌ throws exception (out of scope) }

         System.out.println("---------------------------------------------");
    }
    static void nested() {
        System.out.println("User Nested scope: " + USER.get()); // Alice
    }
}
