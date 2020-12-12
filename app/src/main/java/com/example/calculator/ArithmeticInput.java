package com.example.calculator;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ArithmeticInput implements View.OnClickListener {
    private final EditText txt;
    private final StorageRefactor storage;


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
        String currentValue = (((Button) v).getText().toString());

        //if storage is empty, arithmetic not allowed (except minus)
        if (storage.getStorage().isEmpty()) {
            if (currentValue.equals("-")) {
                entryAllowed(v);
            }
        }

        //if selection = 0, arithmetic not allowed (except minus when next symbol is digit)
        else if (selection == 0) {
            if ((Utility.isDouble(String.valueOf(storage.getStorage().charAt(selection)))) && currentValue.equals("-")) {
                entryAllowed(v);
            }


            //if selection is last,
        } else if (selection == storage.getStorage().length()) {
            // and previous symbol is arithmetic or comma don't add symbol
            if (Utility.containArithmeticSymbol(String.valueOf(storage.getStorage().charAt(selection - 1))) || String.valueOf(storage.getStorage().charAt(selection - 1)).equals(",")) {
                //if previous symbol is opened bracket, only minus is allowed
            } else if (String.valueOf(storage.getStorage().charAt(selection - 1)).equals("(")) {
                if (((Button) v).getText().toString().equals("-")) {
                    entryAllowed(v);
                }
                //otherwise arithmetic symbols are allowed
            } else {
                entryAllowed(v);
            }
            //if previous or current symbols are arithmetic, input not allowed
        } else if (Utility.containArithmeticSymbol(String.valueOf(storage.getStorage().charAt(selection - 1)))) {

        }
        //if current symbol is arithmetic, replace it with new one
        else if (Utility.containArithmeticSymbol(String.valueOf(storage.getStorage().charAt(selection)))) {
            storage.setStorage(storage.getStorage().replace(String.valueOf(storage.getStorage().charAt(selection)), currentValue));
            txt.setText(storage.getStorage());
            txt.setSelection(selection);
        }


        //if previous, or current symbol is comma, input not allowed
        else if (String.valueOf(storage.getStorage().charAt(selection - 1)).equals(",") || String.valueOf(storage.getStorage().charAt(selection)).equals(",")) {
        }
        //if previous symbol is opened bracket, and current symbol is minus, entry allowed
        else if (String.valueOf(storage.getStorage().charAt(selection - 1)).equals("(")) {
            if (((Button) v).getText().toString().equals("-")) {
                entryAllowed(v);
            }
        }
        //otherwise arithmetic symbols are allowed
        else {
            entryAllowed(v);

        }

    }

    void entryAllowed(View v) {
        String tmp = ((Button) v).getText().toString();
        int selection = txt.getSelectionEnd();
        storage.addAtPosition(selection, tmp);

    }


}

