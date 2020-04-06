package com.example.calculator;

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
        if (kursor > length) {
            txt.setSelection(length+1);
        } else {
            txt.setSelection(kursor + 1);                       //set cursor position after last added value
        }
        if (kursor != storage.storage.length() - 1 && storage.storage.length()>1) {               //if cursor is not positioned as last:
            String firstPart = storage.storage.substring(0, kursor);
            String secondPart = storage.storage.substring(kursor, storage.storage.length() - 1); //split storage into two halves,
            storage.storage = "";                                   //first one: all values before new value
            storage.storage += firstPart;                             //second one: all values after new value
            storage.addCharToString(((Button) v).getText().toString());  //merge new storage from first half, NEW VALUE and second half
            storage.storage += secondPart;
            this.txt.setText(storage.returnString());
            txt.setSelection(kursor + 1);

        }


        if (v.getResources().getResourceName(v.getId()).contains("C")) {     //if C has been touched, remove all values
            storage.storage = "";
            this.txt.setText(storage.returnString());
        }

        /*if (v.getResources().getResourceName(v.getId()).contains("0") && storage.storage.length() == 2 &&
        storage.storage.endsWith("0") && storage.storage.startsWith("0")){
            storage.storage = "0";
            this.txt.setText(storage.returnString());
            kursor = txt.getSelectionEnd();
            txt.setSelection(kursor + 1);

        }
         */


        if (v.getResources().getResourceName(v.getId()).contains("delete")) { //if delete has been touched:

            if (storage.storage.length() > 1) {
                String tmp = storage.storage.substring(0, storage.storage.length() - 2);    //delete last value
                storage.storage = "";
                storage.storage += tmp;
                this.txt.setText(storage.returnString());
                txt.setSelection(storage.storage.length());
            } else {
                storage.storage = "";
                this.txt.setText(storage.returnString());

            }

        }


    }

    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
