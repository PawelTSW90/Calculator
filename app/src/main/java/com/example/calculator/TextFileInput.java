package com.example.calculator;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TextFileInput implements View.OnClickListener {
    EditText txt;
    StorageClass storage;

    TextFileInput(EditText text, StorageClass storage) {
        this.txt = text;
        this.storage = storage;


    }


    @Override
    public void onClick(View v) {
        int kursor = txt.getSelectionEnd();                         //initialize cursor position
        storage.addCharToString(((Button) v).getText().toString());
        this.txt.setText(storage.returnString());
        int length = storage.storage.length() - 1;
        if (kursor > length) {                                   // if cursor position is bigger than storage length, set it after last value
            txt.setSelection(length + 1);
        } else {
            txt.setSelection(kursor + 1);                       //set cursor position after last added value
        }


        if (kursor != storage.storage.length() - 1 && storage.storage.length() > 1 && !(v.getResources().getResourceName(v.getId()).contains("del"))) {               //if cursor is not positioned as last and storage has more than 1 values:
            String firstPart = storage.storage.substring(0, kursor);
            String secondPart = storage.storage.substring(kursor, storage.storage.length() - 1); //split storage into two halves,
            storage.storage = "";                                   //first one: all values before new value
            storage.storage += firstPart;                             //second one: all values after new value
            storage.addCharToString(((Button) v).getText().toString());  //merge new storage from first half, NEW VALUE and second half
            storage.storage += secondPart;
            this.txt.setText(storage.returnString());
            txt.setSelection(kursor + 1);

        }
        Log.i("storage", storage.storage);
        if (v.getResources().getResourceName(v.getId()).contains("del")) {
            if (kursor == length && length != 0) {
                String updatedStorage = storage.storage.substring(0, length - 1);
                storage.storage = updatedStorage;
                this.txt.setText(storage.returnString());
                txt.setSelection(length - 1);
            } else if (storage.storage.length() == 0) {
                return;
            } else {
                String firstPart = storage.storage.substring(0, kursor);
                String secondPart = storage.storage.substring(kursor);
                storage.storage = "";
                firstPart = firstPart.substring(0, firstPart.length() - 1);
                storage.storage += firstPart;
                storage.storage += secondPart;
                this.txt.setText(storage.returnString());
                txt.setSelection(kursor - 1);
            }

        }


        if (v.getResources().getResourceName(v.getId()).contains("C")) {     //if C has been touched, remove all values
            storage.storage = "";
            this.txt.setText(storage.returnString());
        }


    }


}
