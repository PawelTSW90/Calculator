package com.example.calculator;

import android.view.View;
import android.widget.TextView;

public class ResultClass implements View.OnClickListener {
    TextView txt;
    Magazyn magazyn;

    ResultClass(TextView text, Magazyn magazyn){
        this.txt = text;
        this.magazyn = magazyn;

    }




    public void onClick(View v) {
        magazyn.dodajZnakDoStringa("=");
        String cos = magazyn.zwrocWynik();
        this.txt.setText(cos);
        magazyn.magazyn = (cos);
    }
}
