package com.example.calculator;

import android.view.View;
import android.widget.EditText;

public class ZeroInput implements View.OnClickListener {
    private EditText txt;
    private StorageRefactorClass storage;
    TextFileInput file;

    ZeroInput(EditText txt, StorageRefactorClass storage, TextFileInput file) {
        this.txt = txt;
        this.storage = storage;
        this.file = file;
    }
    @Override
    public void onClick(View v) {
        isZeroAllowed(v);


    }

    void isZeroAllowed(View v) {
        int selection = txt.getSelectionEnd();
        file.onClick(v);


    }

    public boolean isInteger(String input) {
        return !input.contains("+") && !input.contains("-") && !input.contains("×") && !input.contains("÷");


    }

}

