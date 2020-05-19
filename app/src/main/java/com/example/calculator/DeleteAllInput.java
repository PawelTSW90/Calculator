package com.example.calculator;

import android.view.View;
import android.widget.EditText;

public class DeleteAllInput implements View.OnClickListener {
    private EditText txt;
    private StorageRefactorClass storage;

    DeleteAllInput(EditText txt, StorageRefactorClass storage){
        this.txt = txt;
        this.storage = storage;
    }

    @Override
    public void onClick(View v) {
        storage.storage = "";
        this.txt.setText(storage.returnString());

    }
}
