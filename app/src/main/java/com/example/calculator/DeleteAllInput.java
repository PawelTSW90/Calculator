package com.example.calculator;

import android.view.View;
import android.widget.EditText;

public class DeleteAllInput implements View.OnClickListener {
    private EditText txt;
    private StorageRefactor storage;

    DeleteAllInput(EditText txt, StorageRefactor storage){
        this.txt = txt;
        this.storage = storage;
    }

    @Override
    public void onClick(View v) {
        storage.clearStorage();
        this.txt.setText(storage.getStorage());

    }
}
