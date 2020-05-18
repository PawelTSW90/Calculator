package com.example.calculator;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TextFileInput implements View.OnClickListener {
    private EditText txt;
    private StorageClass storage;

    TextFileInput(EditText text, StorageClass storage) {
        this.txt = text;
        this.storage = storage;

    }

    @Override
    public void onClick(View v) {
        int selection = txt.getSelectionEnd();                       //initialize selection position
        storage.addCharToString(((Button) v).getText().toString());
        this.txt.setText(storage.returnString());
        int length = storage.storage.length() - 1;
        if (selection > length) {                                   // if selection position is bigger than storage length, set it after last value
            txt.setSelection(length + 1);
        } else {
            txt.setSelection(selection + 1);                       //set selection position after last added value
        }

        if (selection != storage.storage.length() - 1 && storage.storage.length() > 1) {      //if selection is not positioned as last and storage has more than 1 values:
            String firstPart = storage.storage.substring(0, selection);
            String secondPart = storage.storage.substring(selection, storage.storage.length() - 1); //split storage into two halves,
            storage.storage = "";                                   //first one: all values before new value
            storage.storage += firstPart;                             //second one: all values after new value
            storage.addCharToString(((Button) v).getText().toString());  //merge new storage from first half, NEW VALUE and second half
            storage.storage += secondPart;
            this.txt.setText(storage.returnString());
            txt.setSelection(selection + 1);

        }
    }


}
