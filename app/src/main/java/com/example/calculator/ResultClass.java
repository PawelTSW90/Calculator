package com.example.calculator;

import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class ResultClass implements View.OnClickListener {
    EditText txt;
    StorageClass StorageClass;
    Calculating calculating;

    ResultClass(EditText text, StorageClass StorageClass, Calculating calculating){
        this.txt = text;
        this.StorageClass = StorageClass;
        this.calculating = calculating;

    }





    public void onClick(View v) {
        if(!isInteger(StorageClass.storage)) {                       //jeśli w storage występują jakiekolwiek operatory, to dodajemy na koniec
            StorageClass.addCharToString("=");                  //znak = i zaczynami liczenie.
            ArrayList<String> cos = StorageClass.returnWyjscie();
            this.txt.setText(calculating.FinalResult(StorageClass));
            txt.setSelection(txt.length());
        }                                                      //w przeciwnym wypadku program czeka bo bez operatorów nie ma czego liczyć


    }

    public boolean isInteger(String input) {                          // metoda sprawdzająca czy dana wartość jest integerem
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
