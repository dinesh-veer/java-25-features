package com.dinesh.moduleImport;

import module java.base;
import module java.se;

//For Ambiguous import need to specify the full class name
public class AmbiguousImport {

    public static void main(String[] args) {

        System.out.println("---------------------------------------------");


        //Reference to 'Date' is ambiguous, both 'java.sql.Date' and 'java.util.Date' match
        //Date date = new Date();

        //To fix this we need to specify full name
        java.util.Date fullName = new java.util.Date();


        System.out.println("---------------------------------------------");

    }
}
