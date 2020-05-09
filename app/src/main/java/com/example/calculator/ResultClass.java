package com.example.calculator;

import android.view.View;
import android.widget.EditText;

public class ResultClass implements View.OnClickListener {
    EditText txt;
    StorageClass storage;
    Calculating calculating;

    ResultClass(EditText text, StorageClass StorageClass, Calculating calculating) {
        this.txt = text;
        this.storage = StorageClass;
        this.calculating = calculating;

    }


    public void onClick(View v) {
        int selection = txt.getSelectionEnd();

        if (!isInteger(storage.storage) || (selection == 0)) {                       //jeśli w storage występują jakiekolwiek operatory, to dodajemy na koniec
            storage.addCharToString("=");                  //znak = i zaczynami liczenie.
            this.txt.setText(calculating.FinalResult(storage));
            txt.setSelection(txt.length());
        } else if (!isInteger(String.valueOf(storage.storage.charAt(selection - 1)))) {
            return;
        }                                                     //w przeciwnym wypadku program czeka bo bez operatorów nie ma czego liczyć


    }

    private boolean isInteger(String input) {
        return !input.contains("+") && !input.contains("-") && !input.contains("×") && !input.contains("÷") && !input.contains(",");


    }

}
