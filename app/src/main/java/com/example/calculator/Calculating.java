package com.example.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.regex.Pattern;

class Calculating {
    StorageRefactor storage;

    Calculating(StorageRefactor storage){
        this.storage = storage;
    }
    private final int RESPONSE_OK = 0;
    private final int WRONG_FORMAT = 1;
    private final int DIGITS_SCALE_REACHED = 2;
    private final int COMMA_SCALE_REACHED = 3;
    private final int MAXIMUM_SCALE_REACHED = 4;
    private final int COMMA_SCALE = 10;
    private final int DIGITS_SCALE = 15;
    private final int MAXIMUM_SCALE = 101;

    //Before calculating, method check if format is correct. If not, toast are displayed
    int wrongFormatChecker(String expression) {

        //regex check if arithmetic symbols format is correct
        boolean formatCheck1 = Pattern.matches(".*[+,\\-×÷]{2,}.*|.*", expression);
        //regex check if commas format is correct
        boolean formatCheck2 = Pattern.matches(".*(^|[+×÷\\-])([0-9]*,+[0-9]*,+[0-9]*)+.*($|[+()×÷\\-])|\\D[,]|^[,].*|.*\\(\\).*", expression);
        //regex check if last character is arithmetic symbol
        boolean formatCheck3 = Pattern.matches(".*[+,×÷\\-]$.*", expression);
        //regex check if every comma with brackets format is correct
        boolean formatCheck4 = Pattern.matches(".*(\\D,\\D|\\d,\\D|\\D,\\d|,$|^,).*", expression);
        //regex check if 15 digit limit is reached
        boolean fifteenDigitsLimit = Pattern.matches(".*\\d{" + (DIGITS_SCALE + 1) + ",}.*", expression);
        //regex check if 10 digit limit after is reached
        boolean tenDigitsAfterCommaLimit = Pattern.matches(".*,\\d{" + (COMMA_SCALE + 1) + ",}.*", expression);
        //regex check if total scale 100 or more characters is reached
        boolean maximumScaleReached = Pattern.matches(".{" + (MAXIMUM_SCALE) + ",}", expression);
        //regex check
        if (formatCheck1 || formatCheck2 || formatCheck3||formatCheck4||!checkBrackets()) {
            return WRONG_FORMAT;
        } else if (fifteenDigitsLimit) {
            return DIGITS_SCALE_REACHED;
        } else if (tenDigitsAfterCommaLimit) {
            return COMMA_SCALE_REACHED;
        } else if (maximumScaleReached) {
            return MAXIMUM_SCALE_REACHED;
        } else return RESPONSE_OK;

    }
    //method checking if number of brackets is correct
    boolean checkBrackets(){
        int openedBrackets = 0;
        int closedBrackets = 0;

        for(int x=0; x<=storage.getStorage().length()-1; x++){
            if(String.valueOf(storage.getStorage().charAt(x)).equals("(")){
                openedBrackets++;
            }
            if(String.valueOf(storage.getStorage().charAt(x)).equals(")")){
                closedBrackets++;
            }
        }

        return openedBrackets == closedBrackets;

    }

    //Method is calculating polish reverse notation format and returns result
    String countResult(StorageRefactor storage) {

        boolean cantCount = false;
        ArrayList<String> exit = storage.refactorStorage();
        

        //replace "," for "." for calculating
        for (int x = 0; x < exit.size(); x++) {
            exit.set(x, exit.get(x).replace(",", "."));
        }
        for (int x = 0; x < exit.size(); x++) {
            //if character is nor number or dot, start calculating
            if (!Utility.isDouble(exit.get(x)) && !exit.get(x).contains(".") && !exit.get(x).isEmpty()) {

                if (exit.get(x - 2).isEmpty() || exit.get(x - 1).isEmpty()) {
                    cantCount = true;
                    break;
                    //adding two values
                } else if (exit.get(x).equals("+")) {
                    BigDecimal value1, value2, sum;
                    value1 = new BigDecimal(exit.get(x - 2));
                    value2 = new BigDecimal(exit.get(x - 1));
                    sum = value1.add(value2);
                    exit.set(x - 2, sum.toString());
                    exit.remove(x - 1);
                    exit.remove(x - 1);
                    x = 0;
                    //subtracting two values
                } else if (Utility.whatSign(exit.get(x)) == 1) {
                    BigDecimal value1, value2, subtracting;
                    value1 = new BigDecimal(exit.get(x - 2));
                    value2 = new BigDecimal(exit.get(x - 1));
                    subtracting = value1.subtract(value2);
                    exit.set(x - 2, subtracting.toString());
                    exit.remove(x - 1);
                    exit.remove(x - 1);
                    x = 0;
                    //multiplying two values
                } else if (Utility.whatSign(exit.get(x)) == 2) {
                    BigDecimal value1, value2, multiplying;
                    value1 = new BigDecimal(exit.get(x - 2));
                    value2 = new BigDecimal(exit.get(x - 1));
                    multiplying = value1.multiply(value2);
                    exit.set(x - 2, multiplying.stripTrailingZeros().toPlainString());
                    exit.remove(x - 1);
                    exit.remove(x - 1);
                    x = 0;
                    //dividing two values
                } else if (Utility.whatSign(exit.get(x)) == 3) {
                    BigDecimal value1, value2, dividing;
                    value1 = new BigDecimal(exit.get(x - 2));
                    value2 = new BigDecimal(exit.get(x - 1));
                    dividing = value1.divide(value2, COMMA_SCALE, RoundingMode.HALF_UP);
                    exit.set(x - 2, dividing.stripTrailingZeros().toPlainString());
                    exit.remove(x - 1);
                    exit.remove(x - 1);
                    x = 0;
                }
            }
        }
            //if result can't get calculated, wait
        if (cantCount) {
            storage.setStorage(storage.getStorage().replace("=", ""));
            return storage.getStorage();

        } else {
            //changing result format to BigDecimal
            BigDecimal bigDecimalValue;
            bigDecimalValue = new BigDecimal(exit.get(exit.size() - 1));
            String bigDecimalToString = bigDecimalValue.toString();
            BigDecimal tmp = new BigDecimal(bigDecimalToString);

            //workaround for trailingZeros bug with 0 compared value
            BigDecimal zero = BigDecimal.ZERO;
            if (tmp.compareTo(zero) == 0) {
                tmp = zero;
            }
            //removing trailing zeros from result, changing back "." into "," and return final result
            tmp = tmp.stripTrailingZeros();
            String stringValue = tmp.toPlainString();
            stringValue = stringValue.replace(".", ",");

            storage.setStorage(stringValue);
        }
        return storage.getStorage();
    }
}