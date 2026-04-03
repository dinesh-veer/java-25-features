package com.dinesh.trickyExamples;

public class NestedScopeValues {

    static final ScopedValue<String> USER = ScopedValue.newInstance();

    static void main(String[] args) {


        System.out.println("---------------------------------------------------");

        System.out.print("Value of User :");
        ScopedValue.where(USER, "A").run(() -> {
            ScopedValue.where(USER, "B").run(() -> {
                System.out.println(USER.get()); //B : Inner binding overrides outer
            });
            System.out.println(USER.get());//A : Outer binding
        });


        System.out.println("---------------------------------------------------");

    }
}
