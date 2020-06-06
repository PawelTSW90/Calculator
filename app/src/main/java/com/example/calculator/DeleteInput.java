package com.example.calculator;

import android.view.View;
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
        deleteMethod();
    }
    //Method responsible for deleting one character and placing cursor at right spot
    void deleteMethod(){
        int selection = txt.getSelectionEnd();
        if (selection == 0) {
            return;
        } else {
            storage.removeCharAt(selection-1);
            txt.setText(storage.getStorage());
            txt.setSelection(selection-1);
        }

    }
}

