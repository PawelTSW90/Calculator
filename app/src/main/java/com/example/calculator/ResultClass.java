package com.example.calculator;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultClass implements View.OnClickListener {
    TextView txt;
    Storage storage;
    Calculating calculating;

    ResultClass(TextView text, Storage storage, Calculating calculating){
        this.txt = text;
        this.storage = storage;
        this.calculating = calculating;

    }





    public void onClick(View v) {
        storage.addCharToString("=");
        ArrayList<String> cos = storage.returnWyjscie();
        this.txt.setText(calculating.FinalResult(storage));


    }
}
