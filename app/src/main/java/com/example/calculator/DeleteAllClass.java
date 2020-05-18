package com.example.calculator;

import android.view.View;
import android.widget.EditText;

public class DeleteAllClass implements View.OnClickListener {
    private EditText txt;
    private StorageClass storage;

    DeleteAllClass(EditText txt, StorageClass storage){
        this.txt = txt;
        this.storage = storage;
    }

    @Override
    public void onClick(View v) {
        storage.storage = "";
        this.txt.setText(storage.returnString());

    }
}
