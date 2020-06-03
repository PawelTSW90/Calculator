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
            String firstPart = storage.getStorage().substring(0, selection);
            String secondPart = storage.getStorage().substring(selection);
            storage.clearStorage();
            firstPart = firstPart.substring(0, firstPart.length() - 1);
            storage.addStringToTheEnd(firstPart);
            storage.addStringToTheEnd(secondPart);
            this.txt.setText(storage.returnString());
            txt.setSelection(selection - 1);
        }

    }


}

