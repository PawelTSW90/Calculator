package com.example.calculator;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultClass implements View.OnClickListener {
    TextView txt;
    StorageClass StorageClass;
    Calculating calculating;

    ResultClass(TextView text, StorageClass StorageClass, Calculating calculating){
        this.txt = text;
        this.StorageClass = StorageClass;
        this.calculating = calculating;

    }





    public void onClick(View v) {
        if(!isInteger(StorageClass.storage)) {
            StorageClass.addCharToString("=");
            ArrayList<String> cos = StorageClass.returnWyjscie();
            this.txt.setText(calculating.FinalResult(StorageClass));
        } else;



    }

    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
