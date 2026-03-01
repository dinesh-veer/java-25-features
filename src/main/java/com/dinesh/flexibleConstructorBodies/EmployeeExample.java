package com.dinesh.flexibleConstructorBodies;

class Person {
    int age;
    Person(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        this.age = age;
    }
}
class Employee extends Person {
    String name;
//    Employee(String name, int age) {
//        super(age);//must be first statement before Java 25
//        this.name = name;
//        if (age < 17 || age > 58) {
//            throw new IllegalArgumentException("Age should be between 17 and 58");
//        }
//        this.age = age;
//    }

    Employee(String name, int age) {
        this.name = name;
        if (age < 17 || age > 58) {
            throw new IllegalArgumentException("Age should be between 17 and 58");
        }
        super( age);//must be first statement before Java 25

    }
}
public class EmployeeExample {

    static void main() {
        System.out.println("---------------------------------------------");

        Employee emp = new Employee("John", 25);
        System.out.println("Employee name :"+emp.name);
        System.out.println("Employee Age : "+emp.age);

        try {
        Person person = new Person( -4);
        }catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            emp = new Employee("Rhyn", 12);
        }catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("---------------------------------------------");

    }


}
