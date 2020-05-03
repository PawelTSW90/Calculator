package com.example.calculator;

import java.util.ArrayList;

public class Calculating {
    StorageClass storage;


    String FinalResult(StorageClass storage) {
        this.storage = storage;

        ArrayList<String> chars = storage.returnWyjscie();
        for (int x = 0; x < chars.size(); x++) {
            chars.set(x, chars.get(x).replace(",", "."));

        }


        for (int x = 0; x < chars.size(); x++) {
            if (!isDouble(chars.get(x)) && !chars.get(x).contains(".")) {


                if (whatSign(chars.get(x)) == 0) {           //adding
                    double tmp1 = Double.parseDouble(chars.get(x - 2));
                    double tmp2 = Double.parseDouble(chars.get(x - 1));
                    Double sum = tmp1 + tmp2;
                    chars.set(x - 2, sum.toString());
                    chars.remove(x - 1);
                    chars.remove(x - 1);
                    x = 0;
                } else if (whatSign(chars.get(x)) == 1) {            //subtracting
                    Double minus = (Double.parseDouble(chars.get(x - 2)) - (Double.parseDouble(chars.get(x - 1))));
                    chars.set(x - 2, minus.toString());
                    chars.remove(x - 1);
                    chars.remove(x - 1);
                    x = 0;

                } else if (whatSign(chars.get(x)) == 2) {          //multiply
                    Double multiply = (Double.parseDouble(chars.get(x - 2)) * (Double.parseDouble(chars.get(x - 1))));
                    chars.set(x - 2, multiply.toString());
                    chars.remove(x - 1);
                    chars.remove(x - 1);
                    x = 0;

                } else {
                    Double divide = (Double.parseDouble(chars.get(x - 2)) / (Double.parseDouble(chars.get(x - 1))));
                    chars.set(x - 2, divide.toString());
                    chars.remove(x - 1);
                    chars.remove(x - 1);
                    x = 0;
                }
            }

        }

        StringBuilder result = new StringBuilder();
        double value = Double.parseDouble(chars.get(chars.size()-1));
        double roundedValue = Math.round(value * 100.0) / 100.0;
        result.append(roundedValue);
        storage.storage = storage.storage.replace(".", ",");
        storage.storage = result.toString();

        return storage.storage;
    }

    public boolean isDouble(String input) {
        try {
            Double.parseDouble(input);
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
