package com.dinesh.trickyExamples;

public class ScopedValueExample {


    static final ScopedValue<String> USER = ScopedValue.newInstance();

    static void main(String[] args) {

        System.out.println("----------------------------------------------------");

        ScopedValue.where(USER, "A").run(() -> {
         //   USER.set("B"); // ScopedValue is immutable will get compile time error
        });

        System.out.println(USER.get());//Exception will thrown NoSuchElementException

        //ScopedValue does not have a "default" value, and it is completely empty when you create it with newInstance().
        // Before you can call .get(), you must bind a value to it for your current execution scope.
        //
        //Because your code calls USER.get() without ever binding a value to USER first, Java panics and throws a NoSuchElementException
        // because there is nothing to get.

        //To fix this you can use below code

        System.out.println("----------------------------------------------------");

        // 2. Bind a value to it ("Alice") and run a block of code
        ScopedValue.where(USER, "Alice").run(() -> {

            // 3. Inside this block, USER is bound to "Alice"
            System.out.println("Inside scope: " + USER.get());

        });


        // Note: If you tried to call USER.get() out here,
        // it would throw the exception again because the scope has ended!

        System.out.println("----------------------------------------------------");
    }
}
