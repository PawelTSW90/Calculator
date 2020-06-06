package com.example.calculator;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ArithmeticInput implements View.OnClickListener {
    private EditText txt;
    private StorageRefactor storage;
    private Calculating calculating;

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
        } else {
            entryAllowed(v);
        }
    }
    void entryAllowed(View v) {
        String tmp = ((Button)v).getText().toString();
        int selection = txt.getSelectionEnd();
        storage.addCharAtPosition(selection,tmp);
        this.txt.setText(storage.returnString());
        txt.setSelection(selection+1);
    }


    private boolean isInteger(String input) {
        return !input.contains("+") && !input.contains("-") && !input.contains("×") && !input.contains("÷");

    }
}

