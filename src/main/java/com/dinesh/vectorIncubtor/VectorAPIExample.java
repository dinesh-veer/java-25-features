package com.dinesh.vectorIncubtor;

import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.VectorSpecies;

/**
 * Run using below command:
 *java --add-modules jdk.incubator.vector VectorAPIExample.java
 *
 */
public class VectorAPIExample {

    // 1. Define the "Species" (the data type and the optimal shape/size for your CPU)
    private static final VectorSpecies<Integer> SPECIES = IntVector.SPECIES_PREFERRED;

    public static void addArrays(int[] a, int[] b, int[] c) {
        int i = 0;
        
        // 2. Find the upper bound for our vector loop
        int upperBound = SPECIES.loopBound(a.length);
        
        // 3. Vectorized loop: Process chunks of the array simultaneously
        for (; i < upperBound; i += SPECIES.length()) {
            
            // Load a block of data from array 'a' and array 'b' into vectors
            IntVector va = IntVector.fromArray(SPECIES, a, i);
            IntVector vb = IntVector.fromArray(SPECIES, b, i);

            // Perform the mathematical operation (SIMD addition)
            IntVector vc = va.add(vb);

            // Store the resulting vector block back into array 'c'
            vc.intoArray(c, i);
        }

        // 4. The "Tail" Loop: Process any remaining elements normally
        for (; i < a.length; i++) {
            c[i] = a[i] + b[i];
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int[] b = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110};
        int[] c = new int[a.length];

        addArrays(a, b, c);

        System.out.println("---------------------------------------------");

        System.out.print("Result: ");
        for (int val : c) {
            System.out.print(val + " ");
        }

        System.out.println("\n---------------------------------------------");
    }
}