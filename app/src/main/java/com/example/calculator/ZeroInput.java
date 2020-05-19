package com.example.calculator;

import android.view.View;
import android.widget.EditText;

public class ZeroInput implements View.OnClickListener {
    private EditText txt;
    private StorageRefactorClass storage;
    MainInputClass input;

    ZeroInput(EditText txt, StorageRefactorClass storage, MainInputClass file) {
        this.txt = txt;
        this.storage = storage;
        this.input = file;
    }
    @Override
    public void onClick(View v) {
        isZeroAllowed(v);


    }

    void isZeroAllowed(View v) {
        input.addChar(v);
        input.setCursor(v);


    }



}

