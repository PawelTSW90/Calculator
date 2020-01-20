package com.example.calculator;

public class Magazyn {
    String magazyn = "";

    void dodajZnakDoStringa(String znak){
        this.magazyn += znak;


    }


    String zwrocStringa(){
        return magazyn;


    }

    int zwrocWynik(){
        try{
            int cos = Character.getNumericValue(magazyn.charAt(0));
            int cos2 = Character.getNumericValue(magazyn.charAt(1));
            return cos+ cos2;

        } catch (NullPointerException e){
            e.printStackTrace();
        }
        return 0;



    }






}
