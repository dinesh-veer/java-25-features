package com.dinesh.patternMatchingWithPrimitive;

/**
 * Demonstrates pattern matching in switch expressions with primitive types.
 * Requires Java 21+ with preview features enabled.
 */
public class SwitchWithPrimitives {

    /**
     * Returns a string describing the type of the given object using pattern matching in a switch.
     * Supports int, long, double, null, and defaults to showing the object's class name.
     *
     * @param object the input object to be analyzed
     * @return a string describing the object's type and value
     */
    public static String getObjectType(Object object) {
        return switch(object){
            // Pattern match for int
            case int i -> "Given type is int :" +i;
            // // Pattern match for long
            case long l -> "Given type is long :" +l;
            // Pattern match for double
            case double d -> "Given type is double :" +d;
            // Explicit handling of null
            case null -> "Given type is null";
            // Default case for all other object types
            default -> "Given type is default :" +object.getClass().getTypeName();
        };
    }

    private static String getStatusType(int status) {

        return switch (status) {
            case 200 -> "Success";
            case 300 -> "redirection";
            case 400 -> "User Error";
            case 500 -> "Server Error";
            default -> "Unknown status code :" +status;
        };
    }

    private static String getGrades(int marks) {

        return switch (marks) {
          case int mark when mark >=90  && marks <=100 -> "Ex";
          case int mark when mark >=80 -> "A";
          case int mark when mark >=70 -> "B";
          case int mark when mark >=60 -> "C";
          case int mark when mark >=50 -> "D";
          case int mark when mark >=40 -> "E";
          case int mark  -> "F";
        };

    }
    public static void main(String[] args) {

        System.out.println("---------------------------------------------");

        System.out.println("Get type of given object :");
        System.out.println("For int i 99 :" + getObjectType(99));
        System.out.println("For long l 1L :" + getObjectType(1L));
        System.out.println("For double d 7.90 :" + getObjectType(7.90));
        System.out.println("For given null :" + getObjectType(null));
        System.out.println("For String Welcome to java 25 :" + getObjectType("Welcome to java 25"));

        System.out.println("---------------------------------------------");

        System.out.println("Status code information:");

        System.out.println("200 series status code is of type : " + getStatusType(200));
        System.out.println("300 series status code is of type : " + getStatusType(300));
        System.out.println("400 series status code is of type : " + getStatusType(400));
        System.out.println("500 series status code is of type : " + getStatusType(500));
        System.out.println("109 status code is of type : " + getStatusType(109));

        System.out.println("---------------------------------------------");

        System.out.println("Get grades of students using pattern matching with guard");

        System.out.println("For 92 marks grade is " + getGrades(92));
        System.out.println("For 75 marks grade is " + getGrades(75));
        System.out.println("For 55 marks grade is " + getGrades(55));
        System.out.println("For 45 marks grade is " + getGrades(45));
        System.out.println("For 30 marks grade is " + getGrades(30));

        System.out.println("---------------------------------------------");

    }


}
