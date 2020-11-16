package com.example.calculator;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DeleteInput implements View.OnClickListener {

    private EditText txt;
    private StorageRefactor storage;


    DeleteInput(EditText txt, StorageRefactor storage) {
        this.txt = txt;
        this.storage = storage;
    }

    @Override

    public void onClick(View v) {
        int selection = txt.getSelectionEnd();
        String input = ((Button) v).getText().toString();
        if (input.equals("⌫")) {
            deleteChar(selection);
        } else {
            deleteAll();
        }
    }

    //Method  deleting one character
    void deleteChar(int position) {
        try {
            StringBuilder tempStorage = new StringBuilder(storage.getStorage());
            tempStorage = tempStorage.deleteCharAt(position-1);
            storage.setStorage(tempStorage.toString());
            txt.setText(storage.getStorage());
            txt.setSelection(position-1);

        } catch (IndexOutOfBoundsException e) {

        }

    }

    //Method clearing all storage
    void deleteAll() {
        storage.setStorage("");
        txt.setSelection(0);
        txt.setText(storage.getStorage());

    }
}

