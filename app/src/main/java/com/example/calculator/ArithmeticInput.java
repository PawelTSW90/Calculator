package com.example.calculator;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ArithmeticInput implements View.OnClickListener {
    private EditText txt;
    private StorageRefactorClass storage;


    ArithmeticInput(EditText txt, StorageRefactorClass storage) {
        this.txt = txt;
        this.storage = storage;

    }

    @Override
    public void onClick(View v) {
        int selection = txt.getSelectionEnd();
        //if storage is empty, wait
        if (storage.storage.isEmpty())
            return;


         else{
            entryAllowed(v);
        }

    }

    void entryAllowed(View v) {
        int selection = txt.getSelectionEnd();
        storage.addCharToString(((Button) v).getText().toString());
        this.txt.setText(storage.returnString());
        int length = storage.storage.length() - 1;
            // if selection position is bigger than storage length, set it after last value
        if (selection > length) {
            txt.setSelection(length + 1);
        } else {
            //set selection position after last added value
            txt.setSelection(selection + 1);
        }
        //if selection is not positioned as last and storage has more than 1 values, split storage into two halves,
        if (selection != storage.storage.length() - 1 && storage.storage.length() > 1) {
            //first one: all values before new value
            String firstPart = storage.storage.substring(0, selection);
            //second one: all values after new value
            String secondPart = storage.storage.substring(selection, storage.storage.length() - 1);
            storage.storage = "";
            //merge new storage from first half, NEW VALUE and second half
            storage.storage += firstPart;
            storage.addCharToString(((Button) v).getText().toString());
            storage.storage += secondPart;
            this.txt.setText(storage.returnString());
            txt.setSelection(selection + 1);

        }
    }


    private boolean isInteger(String input) {
        return !input.contains("+") && !input.contains("-") && !input.contains("×") && !input.contains("÷");


    }
}

