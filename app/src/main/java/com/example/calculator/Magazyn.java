package com.example.calculator;


import java.util.ArrayList;

public class Magazyn {
    String magazyn = "";

    void dodajZnakDoStringa(String znak) {

        this.magazyn += znak;


    }

    void StringOdNowa(String znak) {
        this.magazyn = znak;


    }


    String zwrocStringa() {
        return magazyn;


    }

    String zwrocWynik() {
        String tmp = "";
        ArrayList<String> lista = new ArrayList<>();
        // 19+4+6
        for (int i = 0; i < magazyn.length(); i++) {
            //Jeśli znak jest cyfrą, wrzuć do tmp
            if (isInteger(Character.toString(magazyn.charAt(i))))
                tmp += magazyn.charAt(i);
            //else kiedy znak nie jest cyfrą
            else {
                lista.add(tmp);
                lista.add(Character.toString(magazyn.charAt(i)));
                tmp = "";

            }

        }

        /// obliczanie
        // [23, +, 12, =]





        return "";

    }

    public boolean isInteger(String input) { //Pass in string
        try { //Try to make the input into an integer
            Integer.parseInt(input);
            return true; //Return true if it works
        } catch (Exception e) {
            return false; //If it doesn't work return false
        }
    }


}
