package com.dinesh.patternMatchingWithPrimitive;

/**
 * This example shows conversion without losing data
 * ✔ Matches only if conversion is lossless
 * ❌ No cast, no manual range check
 */
public class InstanceofPrimitivePattern {

    static void checkByteType(int value){
        if (value instanceof byte b) {
            System.out.println("Fits in byte: " + b);
        } else {
            System.out.println(String.format("%d Does not fit in byte",value));
        }
    }
    static void main() {

        System.out.println("---------------------------------------------");

        System.out.println("Will Integer 100 fits into byte : " );
        checkByteType(100);

        System.out.println("Will Integer 1000 fits into byte : " );
        checkByteType(1000);


        System.out.println("---------------------------------------------");

    }
}
