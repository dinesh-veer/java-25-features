package com.dinesh.flexibleConstructorBodies;


 record EmployeeRecord(String name, int age) {

    // Custom constructor using JEP 513 flexible rules
    public EmployeeRecord {
        // 1. Validation logic happens first
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        // 2. Complex age validation
        if (age < 18 || age > 67) {
            throw new IllegalArgumentException("Employee age must be 18–67");
        }

        // In a compact record constructor, the fields (this.name, this.age)
        // are assigned automatically after this block ends.
    }
}

public class RecordExample {
     public static void main(String[] args) {
         System.out.println("---------------------------------------------");

         EmployeeRecord employeeRecord = new EmployeeRecord("Rick", 30);
         System.out.println("Employee with valid data : "+employeeRecord);
         try {
             System.out.println("Employee with empty name : " + new EmployeeRecord("", 30));
         }catch (IllegalArgumentException e) {
             System.out.println("Employee with empty name : "+e.getMessage());
         }

         try {
             System.out.println("Employee with invalid age : " + new EmployeeRecord("Sam", 15));
         }catch (IllegalArgumentException e) {
             System.out.println("Employee with invalid age : "+ e.getMessage());
         }



         System.out.println("---------------------------------------------");

     }
}
