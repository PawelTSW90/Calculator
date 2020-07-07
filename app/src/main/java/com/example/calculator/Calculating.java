package com.example.calculator;

import android.util.Log;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.regex.Pattern;

class Calculating {
    final int RESPONSE_OK = 0;
    final int WRONG_FORMAT = 1;
    final int RESPONSE_15_DIGITS_LIMIT_REACHED = 2;
    final int RESPONSE_LIMIT_AFTER_COMMA_REACHED = 3;

    //Before calculating, method check if format is correct
    int wrongFormatChecker(String expression) {

        //regex check if arithmetic symbols format is correct
        boolean formatCheck1 = Pattern.matches(".*[+,\\-×÷]{2,}.*", expression);
        //regex check if commas format is correct
        boolean formatCheck2 = Pattern.matches(".*(^|[+×÷\\-])([0-9]*,+[0-9]*,+[0-9]*)+.*($|[+×÷\\-]).*", expression);
        //regex check if last character is arithmetic symbol
        boolean formatCheck3 = Pattern.matches(".*[+,×÷\\-]$.*", expression);
        //regex check if 15 digit limit is reached
        boolean fifteenDigitsLimit = Pattern.matches(".*\\d{16,}.*", expression);
        //regex check if 10 digit limit is reached
        boolean tenDigitsAfterCommaLimit = Pattern.matches(".*,\\d{11,}.*", expression);

        if (formatCheck1 || formatCheck2 || formatCheck3) {
            return WRONG_FORMAT;
        } else if (fifteenDigitsLimit) {
            return RESPONSE_15_DIGITS_LIMIT_REACHED;
        } else if (tenDigitsAfterCommaLimit) {
            return RESPONSE_LIMIT_AFTER_COMMA_REACHED;
        } else return RESPONSE_OK;

    }

    //Method is calling refactorStorage method to prepare storage for calculating,
    //and start counting
    String countResult(StorageRefactor storage) {

        boolean cantCount = false;

        ArrayList<String> chars = storage.refactorStorage();
        //replace "," for "." for calculating
        for (int x = 0; x < chars.size(); x++) {
            chars.set(x, chars.get(x).replace(",", "."));
        }
        for (int x = 0; x < chars.size(); x++) {
            //if character is nor number or dot, start calculating
            if (!Utility.isDouble(chars.get(x)) && !chars.get(x).contains(".") && !chars.get(x).isEmpty()) {
                if (chars.get(x - 2).isEmpty() || chars.get(x - 1).isEmpty()) {
                    cantCount = true;
                    //adding two values
                } else if (chars.get(x).equals("+")) {
                    BigDecimal tmp11 = new BigDecimal(chars.get(x-2));
                    BigDecimal tmp22 = new BigDecimal(chars.get(x-1));
                    BigDecimal sum;
                    sum = tmp11.add(tmp22);
                    chars.set(x - 2, sum.toString());
                    chars.remove(x - 1);
                    chars.remove(x - 1);
                    x = 0;
                    //subtracting two values
                } else if (Utility.whatSign(chars.get(x)) == 1) {
                    double minus = (Double.parseDouble(chars.get(x - 2)) - (Double.parseDouble(chars.get(x - 1))));
                    chars.set(x - 2, Double.toString(minus));
                    chars.remove(x - 1);
                    chars.remove(x - 1);
                    x = 0;
                    //multiplying two values
                } else if (Utility.whatSign(chars.get(x)) == 2) {
                    Double multiply = (Double.parseDouble(chars.get(x - 2)) * (Double.parseDouble(chars.get(x - 1))));
                    chars.set(x - 2, multiply.toString());
                    chars.remove(x - 1);
                    chars.remove(x - 1);
                    x = 0;
                    //dividing two values
                } else if (Utility.whatSign(chars.get(x)) == 3) {
                    Double divide = (Double.parseDouble(chars.get(x - 2)) / (Double.parseDouble(chars.get(x - 1))));
                    chars.set(x - 2, divide.toString());
                    chars.remove(x - 1);
                    chars.remove(x - 1);
                    x = 0;
                }
            }
        }

        if (cantCount) {
            storage.removeCharAtPosition(storage.getStorage().charAt(storage.getStorage().length() - 1));
            return storage.getStorage();

        } else {
            BigDecimal bigDecimalValue, bg2;
            bigDecimalValue = new BigDecimal(chars.get(chars.size()-1));
            bg2 = bigDecimalValue.remainder(new BigDecimal(1));
            double bg2toDouble = bg2.doubleValue();
            String bigDecimalToString = bigDecimalValue.toString();

            if(bg2toDouble == 0.0){
                StringBuilder tmp = new StringBuilder(bigDecimalToString);
                Log.i("proba",""+tmp);
                tmp = tmp.deleteCharAt(tmp.length()-1);
                tmp = tmp.deleteCharAt(tmp.length()-1);
                storage.setStorage(tmp.toString());
            } else{
                bigDecimalToString = bigDecimalToString.replace(".", ",");
                storage.setStorage(bigDecimalToString);
                //15,63+8 naprawić

            }


        }
        return storage.getStorage();
    }
}