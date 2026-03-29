package com.dinesh.trickyExamples;

public class FlexibleConstructor {

    int x;

    private int getX() {
        return x;
    }

    FlexibleConstructor(int x) {
        if (x < 0) throw new IllegalArgumentException();
        //x=x+10;// This is allowed in Java 25
        this.x = x;
        super(); // valid in Java 25?
        //Java 25 allows code before super()
    }

    static void main() {
        FlexibleConstructor f = new FlexibleConstructor(5);

        System.out.println("----------------------------------------------------");

        System.out.println("Value of X :"+ f.getX());

        System.out.println("----------------------------------------------------");
    }
}
