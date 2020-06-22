package com.example.calculator;

public class Utility {


    static boolean isParseInt(String input){
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    static boolean containArithmeticSymbol(String input){
        return input.contains("+") || input.contains("-") || input.contains("×") || input.contains("÷");

    }

    static  boolean isDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    static int whatSign(String input) {
        if (input.equals("+")) {
            return 0;
        } else if (input.equals("-")) {
            return 1;
        } else if (input.equals("×")) {
            return 2;
        } else if (input.equals("÷")) {
            return 3;
        } else {
            // no value
            return 4;
        }
    }
}
