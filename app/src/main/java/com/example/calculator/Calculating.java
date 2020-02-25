package com.example.calculator;

import java.util.ArrayList;

public class Calculating {
    Storage storage;


    String FinalResult(Storage storage) {
        this.storage = storage;

        ArrayList znaki = storage.returnWyjscie();
        ArrayList przetwarzaneZnaki = new ArrayList();


        for (int x = 0; x < znaki.size(); x++) {
            if (isNotInteger(znaki.get(x).toString())) {

                if (whatSign(znaki.get(x).toString()) == 0) {
                    int sum = (Integer.parseInt(znaki.get(x - 2).toString()) + (Integer.parseInt(znaki.get(x - 1).toString())));
                    znaki.set(x - 2, sum);
                    znaki.remove(x-1);
                    znaki.remove(x-1);

                   // Log.i("Pawel", "Znaki" + znaki.toString());


                }

            }

        }

        //tmp.deleteCharAt(0).deleteCharAt(tmp.length() - 1);
        String wynik = przetwarzaneZnaki.toString();
        return wynik;

    }

    public boolean isNotInteger(String input) {

        if (input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/")) {
            return true;
        } else return false;
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
