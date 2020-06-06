package com.example.calculator;

public class Utility {


    static boolean isInteger(String input){
        return !input.contains("+") && !input.contains("-") && !input.contains("×") && !input.contains("÷");

    }
}
