package com.example.calculator;

import android.util.Log;
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
        int selection = txt.getSelectionEnd();


        if (storage.storage.isEmpty()) {                   //if storage is empty, display "0," and set selection after comma
            storage.storage = "0,";
            this.txt.setText(storage.returnString());
            txt.setSelection(2);

        } else if (Character.toString(storage.storage.charAt(storage.storage.length() - 1)).equals(",")) { //if previous character is comma aswell, dont do nothing
            return;

        } else if(Character.toString(storage.storage.charAt(selection-1)).equals("+")){ //SET OTHER Arithmetics!
            storage.addCharToString("0,");
            this.txt.setText(storage.returnString());
            this.txt.setSelection(selection+2);
        }


        else {
            checkComma(v);
        }


    }

    void checkComma(View v) {
        int selection = txt.getSelectionEnd();
        int notIntBackward;
        int notIntForward;
        boolean isBackwardComma = false;
        boolean isForwardComma = false;


        for (int i = selection-1; i >= 0; i--) {
            Log.i("tmp", "." + i);

            if (!isInteger(String.valueOf(storage.storage.charAt(i)))) {
                notIntBackward = i;
                if (String.valueOf(storage.storage.charAt(i)).equals(",")) {
                    isBackwardComma = true;
                    commaNotAllowed();
                } else {
                    break;
                }

            }
        }

        for (int x = selection - 1; x <= storage.storage.length() - 1; x++) {

            if (!isInteger(String.valueOf(storage.storage.charAt(x)))) {
                notIntForward = x;
                if (String.valueOf(storage.storage.charAt(x)).equals(",")) {
                    isForwardComma = true;
                    commaNotAllowed();
                } else {
                    break;
                }

            }
        }

        if (isBackwardComma || isForwardComma) {
            commaNotAllowed();
        } else {
            commaAllowed(v);
        }


    }


    void commaAllowed(View v) {

        int kursor = txt.getSelectionEnd();                         //initialize selection position
        storage.addCharToString(((Button) v).getText().toString());
        this.txt.setText(storage.returnString());
        int length = storage.storage.length() - 1;
        if (kursor > length) {                                   // if selection position is bigger than storage length, set it after last value
            txt.setSelection(length + 1);
        } else {
            txt.setSelection(kursor + 1);                       //set selection position after last added value
        }
    }


    private void commaNotAllowed() {
        return;
    }

    public boolean isInteger(String input) {
        if (input.contains("+") || input.contains("-") || input.contains("×") || input.contains("÷") || input.contains(",")) {
            return false;
        }
        return true;


    }
}
