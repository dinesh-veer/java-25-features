package com.dinesh.moduleImport;

import module java.base;

// No need of below imports above one module import will fix
//import java.util.Map;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;

public class BaseModuleImport {
    public static void main(String[] args) {
        System.out.println("---------------------------------------------");

        String[] fruits = {"apple", "berry", "citrus", "avocado"};
        System.out.println("Given array of Strings :" + Arrays.toString(fruits));

        //Get the latest Fruits with alphabets
        Map<String, String> m = Stream.of(fruits)
                .collect(Collectors.toMap(s -> s.toUpperCase().substring(0,1),
                        Function.identity(),
                        (oldValue , newValue) -> newValue ));
        System.out.println("Resulted map : " + m);

        System.out.println("---------------------------------------------");

    }
}
