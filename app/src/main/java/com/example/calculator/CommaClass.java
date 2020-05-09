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
        int selection = txt.getSelectionEnd();


        if (storage.storage.isEmpty()) {                   //if storage is empty, display "0," and set selection after comma
            storage.storage = "0,";
            this.txt.setText(storage.returnString());
            txt.setSelection(2);

        } else if (Character.toString(storage.storage.charAt(storage.storage.length() - 1)).equals(",")) { //if previous character is comma aswell, dont do nothing
            return;

        } else if (selection == 0) {
            int notInt;
            for (int x = selection; x <= storage.storage.length() - 1; x++) {
                if (!isInteger(String.valueOf(storage.storage.charAt(x)))) {
                    notInt = x;

                    for (int y = selection; y <= storage.storage.charAt(notInt); y++) {
                        if (String.valueOf(storage.storage.charAt(y)).equals(",")) {
                            commaNotAllowed();
                        } else {
                            commaAllowed(v);
                        }

                    }

                }
            }
        } else if (!isInteger(Character.toString(storage.storage.charAt(selection - 1)))) {
            storage.addCharToString("0,");
            this.txt.setText(storage.returnString());
            this.txt.setSelection(selection + 2);
        } else {
            checkComma(v);
        }


    }

    void checkComma(View v) {
        int selectionZero = 0;
        int selection = txt.getSelectionEnd();
        if (selection == 0) {
            selectionZero = 1;
        }
        int notIntBackward;
        int notIntForward;
        boolean isBackwardComma = false;
        boolean isForwardComma = false;

        for (int i = Math.max(selection - 1, selectionZero); i >= 0; i--) {

            if (!isInteger(String.valueOf(storage.storage.charAt(i)))) {
                notIntBackward = i;
                if (String.valueOf(storage.storage.charAt(i)).equals(",")) {
                    isBackwardComma = true;
                    commaNotAllowed();
                } else {
                    break;
                }

            } else if (String.valueOf(storage.storage.charAt(i)).equals(",")) {
                isBackwardComma = true;
                break;
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
        if (input.contains("+") || input.contains("-") || input.contains("×") || input.contains("÷")) {
            return false;
        }
        return true;


    }
}
