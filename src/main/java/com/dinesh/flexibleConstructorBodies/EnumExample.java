package com.dinesh.flexibleConstructorBodies;

enum Priority {
    LOW(1), MEDIUM(5), HIGH(10);

    private final int level;

    Priority(int level) {
        // JEP 447/513 allows validation before assignment
        if (level < 1) throw new IllegalArgumentException("Level too low");
        this.level = level;
    }
}
public class EnumExample {

    public static void main(String[] args) {
        System.out.println("---------------------------------------------");

        System.out.println("High priority :"+  Priority.HIGH);

        System.out.println("Invalid status :"+ Priority.valueOf("LOW"));


        System.out.println("---------------------------------------------");
    }
}
