package util;

import java.util.Scanner;

public class Input {
    //---scope---type----name-----
    private Scanner scanner;

    //-------constructor---------
    public Input() {
        this.scanner = new Scanner(System.in).useDelimiter("\n");


    }

    //----method for prompting user for string input---
    public String getString() {
        return this.scanner.next();
    }

    public String getString(String prompt) {
        System.out.println(prompt);
        return this.scanner.next();

    }

    //-----method for prompting user to continue--------
    public boolean yesNo() {
        String userInput = this.scanner.next();
        return "y".equals(userInput) || "yes".equalsIgnoreCase(userInput);
    }

    public boolean yesNo(String prompt) {
        System.out.println(prompt);
        String userInput = this.scanner.next();
        //---------example of auto boxing---------|
        return "y".equals(userInput) || "yes".equalsIgnoreCase(userInput);

    }

    //-----method for prompting user for int-----
    public int getInt() {
        String input = getString();
        try {
            int number = Integer.valueOf(input);
            return number;
        } catch (NumberFormatException e) {
            System.out.println("input needs to be an integer");
            return getInt();
        }


    }

    //------method for user input validation for int-----------
    public int getInt(int min, int max) {
        int value = getInt();
        if (value < min || value > max) {
            System.out.printf("Enter a number between %d and %d%n", min, max);
            return getInt(min, max);
        }
        return value;
    }

    //-----method for prompting user for double-----
    public double getDouble() {
        String input = getString();
        try {
            double number = Double.valueOf(input);
            return number;
        } catch (NumberFormatException e) {
            System.out.println("number needs to be in double format 00.00");
            return getDouble();
        }

    }

    //------method for validating user input for double------
    public double getDouble(double min, double max) {
        double value = getDouble();
        if (value < min || value > max) {
            System.out.printf("Enter a number between %f and %f%n", min, max);
            return getDouble(min, max);
        }
        return value;

    }


}
