package com.example.calculator;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DigitsInput implements View.OnClickListener {
    EditText txt;
    StorageRefactorClass storage;
    MainInputClass input;


    DigitsInput(EditText txt, StorageRefactorClass storage, MainInputClass input){

        this.txt = txt;
        this.storage = storage;
        this.input = input;

    }


    @Override
    public void onClick(View v) {

        int selection = txt.getSelectionEnd();

        if(storage.storage.length() ==0){
            input.addChar(v);
            input.setCursor(v);
        } else if(!String.valueOf(storage.storage.charAt(selection-1)).equals("0")){
            input.addChar(v);
            input.setCursor(v);
        }
        //If there is arithmetic symbol two characters back, and 0 digit one character back, replace 0 with new digit
        else if(!isInteger(String.valueOf(storage.storage.charAt(selection-2))) && String.valueOf(storage.storage.charAt(selection-1)).equals("0")){
            storage.removeCharAtPosition(selection-1);
            storage.addCharToString(((Button)v).getText().toString());
            txt.setText(storage.returnString());
            txt.setSelection(storage.storage.length());
        } else{
            input.addChar(v);
            input.setCursor(v);
        }
    }

    public boolean isInteger(String input) {
        return !input.contains("+") && !input.contains("-") && !input.contains("×") && !input.contains("÷");
    }
}
