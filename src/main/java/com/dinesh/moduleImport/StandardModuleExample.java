package com.dinesh.moduleImport;

import module java.base;
import module java.se;

// Instead importing each class we can import module
//import java.nio.file.Path;
//we need to explicitly add this as module contains 2 list implementations
//java.util and java.awt
import java.util.List;

public class StandardModuleExample {

    public static  void main(String[] args) {


        System.out.println("---------------------------------------------");

        List<Integer> list = List.of(1, 2, 3);

        Path filePath = Path.of("/temp/sample.txt");

        System.out.println(" Given list of elements: " + list);
        System.out.println(" Given file path: " + filePath);


        System.out.println("---------------------------------------------");

    }
}
