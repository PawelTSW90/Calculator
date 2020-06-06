package com.example.calculator;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DigitsInput implements View.OnClickListener {
    EditText txt;
    StorageRefactor storage;
    MainInputClass input;


    DigitsInput(EditText txt, StorageRefactor storage, MainInputClass input){

        this.txt = txt;
        this.storage = storage;
        this.input = input;

    }


    @Override
    public void onClick(View v) {

        int selection = txt.getSelectionEnd();

        if(storage.getStorage().length() ==0){
            input.addChar(v);
            input.setCursor(v);
        } else if(!String.valueOf(storage.getStorage().charAt(selection-1)).equals("0")){
            input.addChar(v);
            input.setCursor(v);
        } else if(selection<2){
            input.addChar(v);
            input.setCursor(v);
        }
        //If there is arithmetic symbol two characters back, and 0 digit one character back, replace 0 with new digit
        else if(!Utility.isInteger(String.valueOf(storage.getStorage().charAt(selection-2))) && String.valueOf(storage.getStorage().charAt(selection-1)).equals("0")){
            storage.removeCharAtPosition(selection-1);
            storage.addCharToString(((Button)v).getText().toString());
            txt.setText(storage.returnString());
            txt.setSelection(storage.getStorage().length());
        } else{
            input.addChar(v);
            input.setCursor(v);
        }
    }


}
