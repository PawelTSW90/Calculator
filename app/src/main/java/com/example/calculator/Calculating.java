package com.example.calculator;

import java.util.ArrayList;

public class Calculating {
    StorageClass storage;


    String FinalResult(StorageClass storage) {
        this.storage = storage;

        ArrayList znaki = storage.returnWyjscie();



        for (int x = 0; x<znaki.size(); x++) {
            if (!isInteger(znaki.get(x).toString())) {


                if (whatSign(znaki.get(x).toString()) == 0) {
                    int sum = (Integer.parseInt(znaki.get(x - 2).toString()) + (Integer.parseInt(znaki.get(x - 1).toString())));
                    znaki.set(x - 2, sum);
                    znaki.remove(x - 1);
                    znaki.remove(x - 1);
                }




                else if (whatSign(znaki.get(x).toString()) == 1) {
                    int minus = (Integer.parseInt(znaki.get(x - 2).toString()) - (Integer.parseInt(znaki.get(x - 1).toString())));
                    znaki.set(x - 2, minus);
                    znaki.remove(x - 1);
                    znaki.remove(x - 1);

                } else if (whatSign(znaki.get(x).toString()) == 2) {
                    int multiply = (Integer.parseInt(znaki.get(x - 2).toString()) * (Integer.parseInt(znaki.get(x - 1).toString())));
                    znaki.set(x - 2, multiply);
                    znaki.remove(x - 1);
                    znaki.remove(x - 1);

                }
            }

        }

        StringBuilder result = new StringBuilder();
        result.append(znaki.toString());
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
        } else if (input.equals("*")) {
            return 2;
        } else return 3;             //(\)
    }


}
