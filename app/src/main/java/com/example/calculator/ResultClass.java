package com.example.calculator;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultClass implements View.OnClickListener {
    TextView txt;
    Magazyn magazyn;
    Obliczanie obliczanie;

    ResultClass(TextView text, Magazyn magazyn, Obliczanie obliczanie){
        this.txt = text;
        this.magazyn = magazyn;
        this.obliczanie = obliczanie;

    }





    public void onClick(View v) {
        magazyn.dodajZnakDoStringa("=");
        ArrayList<String> cos = magazyn.zwrocWyjscie();
        this.txt.setText(obliczanie.OstatecznyWynik(magazyn));


    }
}
