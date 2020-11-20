package com.example.calculator;

import android.view.View;
import android.widget.EditText;

public class CommaInput implements View.OnClickListener {
    private final EditText txt;
    private final StorageRefactor storage;


    CommaInput(EditText txt, StorageRefactor storage) {
        this.txt = txt;
        this.storage = storage;

    }

    public void onClick(View v) {
        int selection = txt.getSelectionEnd();

        //if storage is empty, display "0," and set selection after comma
        if (storage.getStorage().isEmpty()) {
            storage.setStorage("0,");
            this.txt.setText(storage.getStorage());
            txt.setSelection(2);

        }  else {
            if (isCommaAllowed())
                commaAllowed();
        }

    }

    //Method is checking if comma is allowed
    boolean isCommaAllowed() {
        int selection = txt.getSelectionEnd();
        boolean isBackwardComma = false;
        boolean isForwardComma = false;
        //Backward loop looking for comma between selection and first arithmetic symbol
        for (int i = Math.max(selection - 1, 0); i >= 0; i--) {

            if (!Utility.isDouble(String.valueOf(storage.getStorage().charAt(i)))) {

                if (String.valueOf(storage.getStorage().charAt(i)).equals(",")) {
                    isBackwardComma = true;

                }
                break;

            } else if (String.valueOf(storage.getStorage().charAt(i)).equals(",")) {
                isBackwardComma = true;
                break;
            }
        }
        //Forward Loop looking for comma between selection and first arithmetic symbol
        for (int x = Math.max(selection - 1, 0); x <= storage.getStorage().length() - 1; x++) {
            if (String.valueOf(storage.getStorage().charAt(x)).equals(",")) {
                isForwardComma = true;
                break;
            }
        }
        return !isBackwardComma && !isForwardComma;
    }

    void commaAllowed() {
        //initialize selection position
        int selection = txt.getSelectionEnd();

        //if selection is 0, add ",0"
        if (selection == 0) {
            storage.addAtPosition(0, "0,");
        }

        //if previous character is arithmetic symbol, add 0,
        else if (Utility.containArithmeticSymbol(String.valueOf(storage.getStorage().charAt(selection - 1)))) {
            storage.addAtPosition(selection, "0,");
            this.txt.setSelection(selection + 2);

        } else {
            storage.addAtPosition(selection, ",");
            txt.setSelection(selection + 1);

        }
    }

}



