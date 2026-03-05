package com.dinesh.patternMatchingWithPrimitive;

record Area(double value){}
public class RecordPatternMatching {

    public static String check(Object obj) {
        if (obj instanceof Area(int v)) {
            return "Integer measurement: " + v;
        } else {
            return "Not an integer measurement";
        }

    }

    public static void main() {

        System.out.println("---------------------------------------------");

        System.out.println("Check object of Record Area(5) :" + check(new Area(5)));
        System.out.println("Check object of Record Area(2.9) :" + check(new Area(2.9)));
        System.out.println("Check object of String : \"String type\":" + check("String type"));

        System.out.println("---------------------------------------------");


    }
}


