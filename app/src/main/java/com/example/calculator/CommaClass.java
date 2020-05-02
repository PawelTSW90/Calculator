package com.example.calculator;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CommaClass implements View.OnClickListener {
    EditText txt;
    StorageClass storage;

    CommaClass(EditText txt, StorageClass storage) {
        this.txt = txt;
        this.storage = storage;
    }


    public void onClick(View v) {
        int selection = txt.getSelectionEnd() - 1;


        if (storage.storage.isEmpty()) {                   //if storage is empty, display "0," and set selection after comma
            storage.storage = "0,";
            this.txt.setText(storage.returnString());
            txt.setSelection(2);

        } else if (Character.toString(storage.storage.charAt(storage.storage.length() - 1)).equals(",")) { //if previous character is comma aswell, dont do nothing
            return;
        } else


            for (int i = selection; i >= 0; i--) {
                if (!isInteger(String.valueOf(storage.storage.charAt(i)))) {
                    int notInt = storage.storage.charAt(i);
                    i = storage.storage.charAt(selection);
                    for (int x = storage.storage.charAt(selection - 1); x >= notInt; x--) {
                        if (String.valueOf(storage.storage.charAt(x)).equals(",")) {
                            return;
                        }
                    }
                } else {
                    int kursor = txt.getSelectionEnd();                         //initialize selection position
                    storage.addCharToString(((Button) v).getText().toString());
                    this.txt.setText(storage.returnString());
                    int length = storage.storage.length() - 1;
                    if (kursor > length) {                                   // if selection position is bigger than storage length, set it after last value
                        txt.setSelection(length + 1);
                    } else {
                        txt.setSelection(kursor + 1);                       //set selection position after last added value
                    }
                    break;
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
