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
        int selection = txt.getSelectionEnd();
        //if storage is empty, don't add symbol
        if (storage.getStorage().isEmpty()) {
            return;
            //if storage is not empty, but selection is 0, dont add symbol
        } else if(selection==0){
            return;

//if selection is last,
        } else if(selection == storage.getStorage().length()){
            // and previous symbol is arithmetic, don't add symbol
            if(!isInteger(String.valueOf(storage.getStorage().charAt(selection-1)))){
                return;
                //if not add it
            } else{
                entryAllowed(v);
            }
            //if previous or current character arithmetic symbol, don't add symbol
        } else if(!isInteger(String.valueOf(storage.getStorage().charAt(selection-1))) || (!isInteger(String.valueOf(storage.getStorage().charAt(selection))))){
            return;

        }


         else{
            entryAllowed(v);
        }

    }

    void entryAllowed(View v) {
        int selection = txt.getSelectionEnd();
        storage.addCharToString(((Button) v).getText().toString());
        this.txt.setText(storage.returnString());
        int length = storage.getStorage().length() - 1;
            // if selection position is bigger than storage length, set it after last value
        if (selection > length) {
            txt.setSelection(length + 1);
        } else {
            //set selection position after last added value
            txt.setSelection(selection + 1);
        }
        //if selection is not positioned as last and storage has more than 1 values, split storage into two halves,
        if (selection != storage.getStorage().length() - 1 && storage.getStorage().length() > 1) {
            //first one: all values before new value
            String firstPart = storage.getStorage().substring(0, selection);
            //second one: all values after new value
            String secondPart = storage.getStorage().substring(selection, storage.getStorage().length() - 1);
            storage.clearStorage();
            //merge new storage from first half, NEW VALUE and second half
            storage.addStringToTheEnd(firstPart);
            storage.addCharToString(((Button) v).getText().toString());
            storage.addStringToTheEnd(secondPart);
            this.txt.setText(storage.returnString());
            txt.setSelection(selection + 1);

        }
    }


    private boolean isInteger(String input) {
        return !input.contains("+") && !input.contains("-") && !input.contains("×") && !input.contains("÷");


    }
}

