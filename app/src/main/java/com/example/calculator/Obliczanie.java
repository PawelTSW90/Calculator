package com.example.calculator;

import java.util.ArrayList;

public class Obliczanie {
    Magazyn magazyn;


    String OstatecznyWynik(Magazyn magazyn) {
        this.magazyn = magazyn;

        ArrayList znaki = magazyn.zwrocWyjscie();
        ArrayList wynik1;
        String wynik = znaki.toString().replaceAll("[\\[\\]]", "");




        return wynik;

    }


}
