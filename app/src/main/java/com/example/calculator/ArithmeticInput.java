package com.example.calculator;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ArithmeticInput implements View.OnClickListener {
    private EditText txt;
    private StorageRefactor storage;


    ArithmeticInput(EditText txt, StorageRefactor storage) {
        this.txt = txt;
        this.storage = storage;
    }

    @Override
    public void onClick(View v) {
        checkIfEntryAllowed(v);
    }

    void checkIfEntryAllowed(View v) {
        int selection = txt.getSelectionEnd();

        //if storage is empty, arithmetic not allowed (except minus)
        if (storage.getStorage().isEmpty()) {
            if (!((Button) v).getText().equals("-")) {
                return;
            } else {
                entryAllowed(v);
            }
        }

        //if selection = 0, arithmetic not allowed (except minus when next symbol is digit)
        if (selection == 0) {
            if (!(Utility.isDouble(String.valueOf(storage.getStorage().charAt(selection))))) {
                return;

            } else {
                if (((Button) v).getText().equals("-")) {
                    entryAllowed(v);
                } else {
                    return;
                }
            }



            //if selection is last,
        } else if (selection == storage.getStorage().length()) {
            // and previous symbol is arithmetic, don't add symbol
            if (Utility.containArithmeticSymbol(String.valueOf(storage.getStorage().charAt(selection - 1)))) {
                return;
                //if previous symbol is opened bracket, only minus is allowed
            } else if (String.valueOf(storage.getStorage().charAt(selection - 1)).equals("(")) {
                if (((Button) v).getText().toString().equals("-")) {
                    entryAllowed(v);
                } else {
                    return;
                }
                //otherwise arithmetic symbols are allowed
            } else {
                entryAllowed(v);
            }
            //if previous or current symbols are arithmetic, input not allowed
        } else if (Utility.containArithmeticSymbol(String.valueOf(storage.getStorage().charAt(selection - 1))) || (Utility.containArithmeticSymbol(String.valueOf(storage.getStorage().charAt(selection))))) {
            return;
            //if previous symbol is opened bracket, and current symbol is minus, entry allowed
        } else if (String.valueOf(storage.getStorage().charAt(selection - 1)).equals("(")) {
            if (((Button) v).getText().toString().equals("-")) {
                entryAllowed(v);
            }
        }
        else {
            entryAllowed(v);

        }

    }

    void entryAllowed(View v) {
        String tmp = ((Button) v).getText().toString();
        int selection = txt.getSelectionEnd();
        storage.addCharAtPosition(selection, tmp);

    }


}

