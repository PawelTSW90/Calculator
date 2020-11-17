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
        //method is checking which arithmetic symbol is used
    static int whatSign(String input) {
        switch (input) {
            case "+":
                return 0;
            case "-":
                return 1;
            case "×":
                return 2;
            case "÷":
                return 3;
            default:
                // no value
                return 4;
        }
    }

    //method is checking if symbol is low priority arithmetic
    static boolean isLowPriority(String input) {
        return input.equals("+") || input.equals("-");

    }


}
