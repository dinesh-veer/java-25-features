package com.dinesh.scopedValues;

import static java.lang.ScopedValue.where;

public class NestedBinding {

    static final ScopedValue<String> X = ScopedValue.newInstance();

    void foo() {
        where(X, "Java 25").run(() -> bar());
    }

    void bar() {
        System.out.println("Scoped value before change : " + X.get()); // Java 25
        where(X, "Welcome").run(() -> baz());
        System.out.println("Scoped value after returning : "+ X.get()); // Java 25 (restored)
    }

    void baz() {
        System.out.println("Scoped value after change : "+ X.get()); // Welcome
    }

    static void main(String[] args) {
        System.out.println("---------------------------------------------");

        new NestedBinding().foo();

        System.out.println("---------------------------------------------");
    }
}
