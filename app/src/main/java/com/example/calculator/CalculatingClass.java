package com.example.calculator;

import android.util.Log;

import java.util.ArrayList;

class CalculatingClass {
   StorageRefactorClass storage;
    boolean cantCount = false;

    //Before calculating, method check if format is correct. If not, program waiting
    void WrongFormatChecker(StorageRefactorClass storage){
        this.storage=storage;
    //Loop that no allows to two commas between two arithmetic operators
        for(int tmp = 0; tmp<=storage.storage.length()-1; tmp++ ){
            if(cantCount){
                break;
            }
            if(String.valueOf(storage.storage.charAt(tmp)).equals(",")){

                for(int tmp2 = tmp+1; tmp2<=storage.storage.length()-1; tmp2++){
                    Log.i("tmp", " " + storage.storage.charAt(tmp2));
                    if(!isDouble(String.valueOf(storage.storage.charAt(tmp2))) && String.valueOf(storage.storage.charAt(tmp2)).equals(",")){
                        cantCount = true;
                        break;
                    }  else if (!isDouble(String.valueOf(storage.storage.charAt(tmp2)))) {
                        break;

                    }

                }
            }
        }


    }


    String FinalResult(StorageRefactorClass storage) {
        this.storage = storage;



        ArrayList<String> chars = storage.returnExit();
        //replace "," for "." for calculating
        for (int x = 0; x < chars.size(); x++) {
            chars.set(x, chars.get(x).replace(",", "."));

        }


        for (int x = 0; x < chars.size(); x++) {
            Log.i("tmp", "storage: " + storage.storage);
            Log.i("tmp", "chars" + chars);
            //if character is nor number or dot, start calculating
            if (!isDouble(chars.get(x)) && !chars.get(x).contains(".") && !chars.get(x).isEmpty()) {

                if (chars.get(x - 2).isEmpty() || chars.get(x - 1).isEmpty()) {
                    cantCount = true;

                    //adding
                } else if (chars.get(x).equals("+")) {
                    double tmp1 = Double.parseDouble(chars.get(x - 2));
                    double tmp2 = Double.parseDouble(chars.get(x - 1));
                    Double sum = tmp1 + tmp2;
                    chars.set(x - 2, sum.toString());
                    chars.remove(x - 1);
                    chars.remove(x - 1);
                    x = 0;
                    //subtracting
                } else if (whatSign(chars.get(x)) == 1) {
                    Double minus = (Double.parseDouble(chars.get(x - 2)) - (Double.parseDouble(chars.get(x - 1))));
                    chars.set(x - 2, minus.toString());
                    chars.remove(x - 1);
                    chars.remove(x - 1);
                    x = 0;
                    //multiplying
                } else if (whatSign(chars.get(x)) == 2) {
                    Double multiply = (Double.parseDouble(chars.get(x - 2)) * (Double.parseDouble(chars.get(x - 1))));
                    chars.set(x - 2, multiply.toString());
                    chars.remove(x - 1);
                    chars.remove(x - 1);
                    x = 0;
                    //dividing
                } else if (whatSign(chars.get(x)) == 3) {
                    Double divide = (Double.parseDouble(chars.get(x - 2)) / (Double.parseDouble(chars.get(x - 1))));
                    chars.set(x - 2, divide.toString());
                    chars.remove(x - 1);
                    chars.remove(x - 1);
                    x = 0;
                }
            }

        }

        if(cantCount){
            storage.removeCharAtPosition(storage.storage.charAt(storage.storage.length()-1));
            return storage.storage;

        } else {


            StringBuilder result = new StringBuilder();
            double value = Double.parseDouble(chars.get(chars.size() - 1));
            //set number of integers after comma to 10
            double roundedValue = Math.round(value * 10000000000.0) / 10000000000.0;
            //Displaying result as integer
            if(roundedValue%1==0){
                int intvalue = (int) roundedValue;
                result.append(intvalue);
                String tmp = result.toString();
                tmp = tmp.replace(".", ",");
                storage.storage = tmp;
                return storage.storage;
                //Displaying result as double
            } else {
                result.append(roundedValue);
                String tmp = result.toString();
                tmp = tmp.replace(".", ",");
                storage.storage = tmp;
                return storage.storage;
            }
        }
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
        } else if (input.equals("÷")) {
            return 3;
        } else {
            // no value
            return 4;
        }
    }


}




