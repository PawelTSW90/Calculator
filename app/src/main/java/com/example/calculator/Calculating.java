package com.example.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.regex.Pattern;

class Calculating {
    private final int RESPONSE_OK = 0;
    private final int WRONG_FORMAT = 1;
    private final int DIGITS_SCALE_REACHED = 2;
    private final int COMMA_SCALE_REACHED = 3;
    private final int MAXIMUM_SCALE_REACHED = 4;
    private final int COMMA_SCALE = 10;
    private final int DIGITS_SCALE = 15;
    private final int MAXIMUM_SCALE = 101;
    //Before calculating, method check if format is correct
    int wrongFormatChecker(String expression) {

        //regex check if arithmetic symbols format is correct
        boolean formatCheck1 = Pattern.matches(".*[+,\\-×÷]{2,}.*", expression);
        //regex check if commas format is correct
        boolean formatCheck2 = Pattern.matches(".*(^|[+×÷\\-])([0-9]*,+[0-9]*,+[0-9]*)+.*($|[+×÷\\-]).*", expression);
        //regex check if last character is arithmetic symbol
        boolean formatCheck3 = Pattern.matches(".*[+,×÷\\-]$.*", expression);
        //regex check if 15 digit limit is reached
        boolean fifteenDigitsLimit = Pattern.matches(".*\\d{"+(DIGITS_SCALE+1)+",}.*", expression);
        //regex check if 10 digit limit is reached
        boolean tenDigitsAfterCommaLimit = Pattern.matches(".*,\\d{"+(COMMA_SCALE+1)+",}.*", expression);
        //regex check if total scale 100 or more characters is reached
        boolean maximumScaleReached = Pattern.matches(".{"+(MAXIMUM_SCALE)+",}", expression);

        if (formatCheck1 || formatCheck2 || formatCheck3) {
            return WRONG_FORMAT;
        } else if (fifteenDigitsLimit) {
            return DIGITS_SCALE_REACHED;
        } else if (tenDigitsAfterCommaLimit) {
            return COMMA_SCALE_REACHED;
        } else if(maximumScaleReached){
            return MAXIMUM_SCALE_REACHED;
        }

        else return RESPONSE_OK;

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
                    BigDecimal value1, value2, sum;
                    value1 = new BigDecimal(chars.get(x-2));
                    value2 = new BigDecimal(chars.get(x-1));
                    sum = value1.add(value2);
                    chars.set(x - 2, sum.toString());
                    chars.remove(x - 1);
                    chars.remove(x - 1);
                    x = 0;
                    //subtracting two values
                } else if (Utility.whatSign(chars.get(x)) == 1) {
                    BigDecimal value1, value2, subtracting;
                    value1 = new BigDecimal(chars.get(x-2));
                    value2 = new BigDecimal(chars.get(x-1));
                    subtracting = value1.subtract(value2);
                    chars.set(x - 2, subtracting.toString());
                    chars.remove(x - 1);
                    chars.remove(x - 1);
                    x = 0;
                    //multiplying two values
                } else if (Utility.whatSign(chars.get(x)) == 2) {
                    BigDecimal value1, value2, multiplying;
                    value1 = new BigDecimal(chars.get(x-2));
                    value2 = new BigDecimal(chars.get(x-1));
                    multiplying = value1.multiply(value2);
                    chars.set(x - 2, multiplying.stripTrailingZeros().toPlainString());
                    chars.remove(x - 1);
                    chars.remove(x - 1);
                    x = 0;
                    //dividing two values
                } else if (Utility.whatSign(chars.get(x)) == 3) {
                    BigDecimal value1, value2, dividing;
                    value1 = new BigDecimal(chars.get(x-2));
                    value2 = new BigDecimal(chars.get(x-1));
                    dividing = value1.divide(value2,COMMA_SCALE, RoundingMode.HALF_UP);
                    chars.set(x - 2, dividing.stripTrailingZeros().toPlainString());
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
            storage.setStorage(bigDecimalToString);


            if(bg2toDouble != 0.0){
                bigDecimalToString = bigDecimalToString.replace(".", ",");
                storage.setStorage(bigDecimalToString);

            }


        }
        return storage.getStorage();
    }
}