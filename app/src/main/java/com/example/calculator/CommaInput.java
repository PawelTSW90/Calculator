package com.example.calculator;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CommaInput implements View.OnClickListener {
    private EditText txt;
    private StorageRefactorClass storage;


    CommaInput(EditText txt, StorageRefactorClass storage) {
        this.txt = txt;
        this.storage = storage;

    }


    public void onClick(View v) {
        int selection = txt.getSelectionEnd();

        //if storage is empty, display "0," and set selection after comma
        if (storage.storage.isEmpty()) {
            storage.storage = "0,";
            this.txt.setText(storage.returnString());
            txt.setSelection(2);

        }

        else if (selection == 0) {
            checkComma(v);
        }
        //if previous character is arithmetic symbol, add "0,"
        else if (!isInteger(Character.toString(storage.storage.charAt(selection - 1)))) {
            storage.addCharToString("0,");
            this.txt.setText(storage.returnString());
            this.txt.setSelection(selection + 2);
        } else {
            checkComma(v);
        }


    }
    //Method is checking if comma is allowed
    void checkComma(View v) {
        //avoiding out of bound exception
        int selectionZero = 0;
        int selection = txt.getSelectionEnd();
        if (selection == 0) {
            selectionZero = 1;
        }

        boolean isBackwardComma = false;
        boolean isForwardComma = false;
        //Backward loop looking for comma between selection and first no-integer character
        for (int i = Math.max(selection - 1, selectionZero); i >= 0; i--) {

            if (!isInteger(String.valueOf(storage.storage.charAt(i)))) {

                if (String.valueOf(storage.storage.charAt(i)).equals(",")) {
                    isBackwardComma = true;
                    break;

                } else {
                    break;
                }

            } else if (String.valueOf(storage.storage.charAt(i)).equals(",")) {
                isBackwardComma = true;
                break;
            }
        }
        //Forward Loop looking for comma between selection and first no-integer character
        for (int x = Math.max(selection - 1, selectionZero); x <= storage.storage.length() - 1; x++) {
            if(String.valueOf(storage.storage.charAt(x)).equals(",")){
                isForwardComma = true;
                break;
            }
        }

        if (isBackwardComma || isForwardComma) {
            return;
        } else {
            commaAllowed(v);
        }


    }


    void commaAllowed(View v) {
        //initialize selection position
        int kursor = txt.getSelectionEnd();
        storage.addCharToString(((Button) v).getText().toString());
        this.txt.setText(storage.returnString());
        int length = storage.storage.length() - 1;
        // if selection position is bigger than storage length, set it after last value
        if (kursor > length) {
            txt.setSelection(length + 1);
            //set selection position after last added value
        } else {
            txt.setSelection(kursor + 1);
        }
    }




    public boolean isInteger(String input) {
        if (input.contains("+") || input.contains("-") || input.contains("×") || input.contains("÷")) {
            return false;
        }
        return true;


    }
}



