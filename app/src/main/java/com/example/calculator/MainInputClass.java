package com.example.calculator;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainInputClass {
    private EditText txt;
    private StorageRefactor storage;

    MainInputClass(EditText text, StorageRefactor storage) {
        this.txt = text;
        this.storage = storage;

    }


    void addChar(View v){
        storage.addCharToString(((Button) v).getText().toString());
    }

    void setCursor(View v){
        //initialize selection position
        int selection = txt.getSelectionEnd();
        this.txt.setText(storage.returnString());
        int length = storage.getStorage().length() - 1;
        // if selection position is bigger than storage length, set it after last value
        if (selection > length) {
            txt.setSelection(length + 1);
            //set selection position after last added value
        } else {
            txt.setSelection(selection + 1);
        }
        //if selection is not positioned as last and storage has more than 1 values:
        if (selection != storage.getStorage().length() - 1 && storage.getStorage().length() > 1) {
            //split storage into two halves. First one: all values before new value
            String firstPart = storage.getStorage().substring(0, selection);
            //second one: all values after new value
            String secondPart = storage.getStorage().substring(selection, storage.getStorage().length() - 1);
            storage.clearStorage();
            storage.addStringToTheEnd(firstPart);
            //merge new storage from first half, NEW VALUE and second half
            storage.addCharToString(((Button) v).getText().toString());
            storage.addStringToTheEnd(secondPart);
            this.txt.setText(storage.returnString());
            txt.setSelection(selection + 1);

        }

    }


}
