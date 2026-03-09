package com.dinesh.patternMatchingWithPrimitive;

public class SwitchWithPrimitives2 {

    static String validateObject(Object obj) {
        return switch (obj) {
            case Integer i when i.intValue() instanceof byte b ->
                    "Integer fits in byte: " + b;
            //Explicit casting is not needed anymore
            //  if (x >= Byte.MIN_VALUE && x <= Byte.MAX_VALUE)
            //    byte b = (byte) x;
            // instead just use
            //if (x instanceof byte b)

            case Integer i ->
                    "Integer too large: " + i;

            case String s ->
                    "String: " + s;

            default ->
                    "Unknown";
        };
    }

    public static void main(String[] args) {

        System.out.println("---------------------------------------------");

        System.out.println("Object type for integer 51 :" + validateObject(51));
        System.out.println("Object type for integer 127 :" + validateObject(127));
        System.out.println("Object type for integer 128 :" + validateObject(128));

        System.out.println("Object type for \"Java 25\" :" + validateObject("Java 25"));
        System.out.println("Object type for SwitchWithPrimitives2 :" + validateObject( new SwitchWithPrimitives2()));

        System.out.println("---------------------------------------------");

    }
}
