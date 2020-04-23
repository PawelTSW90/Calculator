package com.example.calculator;

import java.util.ArrayList;

public class Calculating {
    StorageClass storage;


    String FinalResult(StorageClass storage) {
        this.storage = storage;

        ArrayList chars = storage.returnWyjscie();


        for (int x = 0; x < chars.size(); x++) {
            if (!isInteger(chars.get(x).toString())) {


                if (whatSign(chars.get(x).toString()) == 0) {                 //adding
                    int sum = (Integer.parseInt(chars.get(x - 2).toString()) + (Integer.parseInt(chars.get(x - 1).toString())));
                    chars.set(x - 2, sum);
                    chars.remove(x - 1);
                    chars.remove(x - 1);
                    x = 0;
                } else if (whatSign(chars.get(x).toString()) == 1) {            //subtracting
                    int minus = (Integer.parseInt(chars.get(x - 2).toString()) - (Integer.parseInt(chars.get(x - 1).toString())));
                    chars.set(x - 2, minus);
                    chars.remove(x - 1);
                    chars.remove(x - 1);
                    x = 0;

                } else if (whatSign(chars.get(x).toString()) == 2) {          //multiply
                    int multiply = (Integer.parseInt(chars.get(x - 2).toString()) * (Integer.parseInt(chars.get(x - 1).toString())));
                    chars.set(x - 2, multiply);
                    chars.remove(x - 1);
                    chars.remove(x - 1);
                    x = 0;

                }
            }

        }

        StringBuilder result = new StringBuilder();
        result.append(chars.toString());
        result.deleteCharAt(0).deleteCharAt(result.length() - 1);
        storage.storage = result.toString();

        return storage.storage;
    }

    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int whatSign(String input) {
        if (input.equals("+")) {
            return 0;
        } else if (input.equals("-")) {
            return 1;
        } else if (input.equals("×")) {
            return 2;
        } else return 3;             //(\)
    }


}
