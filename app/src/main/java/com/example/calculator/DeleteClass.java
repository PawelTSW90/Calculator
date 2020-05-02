package com.example.calculator;

import android.view.View;
import android.widget.EditText;

public class DeleteClass implements View.OnClickListener {

    EditText txt;
    StorageClass storage;


    DeleteClass(EditText txt, StorageClass storage) {
        this.txt = txt;
        this.storage = storage;
    }

    @Override
    public void onClick(View v) {
        int cursor = txt.getSelectionEnd();
        int length = storage.storage.length() - 1;
        if (cursor == 0) {
            return;
        } else {
            String firstPart = storage.storage.substring(0, cursor);
            String secondPart = storage.storage.substring(cursor);
            storage.storage = "";
            firstPart = firstPart.substring(0, firstPart.length() - 1);
            storage.storage += firstPart;
            storage.storage += secondPart;
            this.txt.setText(storage.returnString());
            txt.setSelection(cursor - 1);
        }
    }
}

