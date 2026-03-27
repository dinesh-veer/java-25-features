package com.dinesh.trickyExamples;
import module java.base;      // Brings in java.util.List
import module java.desktop;   // Brings in java.awt.List

//it cannot resolve List class as it is preent in both modules
//leads to compile time error
//can be resolved using explicit dependecncy

//Below line is commented or not present while asking question
import java.util.List;

public class ModuleImport {
    static void main(String[] args) {


        List list = new ArrayList();
        list.add("java.base");
        list.add("com.dinesh.trickyExamples");

        System.out.println(list);
    }
}
