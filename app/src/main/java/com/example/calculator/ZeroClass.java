package com.example.calculator;

import android.view.View;
import android.widget.EditText;

public class ZeroClass implements View.OnClickListener {
    private EditText txt;
    private StorageClass storage;
    TextFileInput file;

    ZeroClass(EditText txt, StorageClass storage, TextFileInput file) {
        this.txt = txt;
        this.storage = storage;
        this.file = file;
    }
    @Override
    public void onClick(View v) {
        if (!storage.storage.isEmpty()) {
            file.onClick(v);
        }
    }

    void isZeroAllowed(View v) {

    }

    public boolean isInteger(String input) {
        return !input.contains("+") && !input.contains("-") && !input.contains("×") && !input.contains("÷");


    }

}

