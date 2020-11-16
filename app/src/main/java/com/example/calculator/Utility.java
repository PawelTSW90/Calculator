package com.example.calculator;

class Utility {

            //method is checking if value is integer
    static boolean isParseInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
            //method is checking if value is arithmetic symbol
    static boolean containArithmeticSymbol(String input) {
        return input.contains("+") || input.contains("-") || input.contains("×") || input.contains("÷");

    }
            //method is checking if value is double
     static boolean isDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
        //method is checking what arithmetic symbol is beeing used
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

    //method is checking if symbol is low priority arithmetic
    static boolean isLowPriority(String input) {
        return input.equals("+") || input.equals("-");

    }


}
