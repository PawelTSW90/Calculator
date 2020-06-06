package com.example.calculator;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DigitsInput implements View.OnClickListener {
    EditText txt;
    StorageRefactor storage;

    DigitsInput(EditText txt, StorageRefactor storage){
        this.txt = txt;
        this.storage = storage;

    }
    @Override
    public void onClick(View v) {
        int selection = txt.getSelectionEnd();
        String value = ((Button)v).getText().toString();
        //if storage is empty, insert new digit
        if(storage.getStorage().length() ==0){
            storage.addCharAtPosition(0, value);

        }
        //if cursor is at the beginning, and storage contains some characters, insert digit as a first one
        else if((selection ==0) && storage.getStorage().length()>=1){
            storage.addCharAtPosition(selection, value);
        }
        //if previous character is not zero, insert new digit
        else if(!String.valueOf(storage.getStorage().charAt(selection-1)).equals("0")){
            storage.addCharAtPosition(selection,value);

        } else if(selection<2){
            storage.addCharAtPosition(selection,value);

        }
        //If there is arithmetic symbol two characters back, and 0 digit one character back, replace 0 with new digit
        else if(!Utility.isParseInt(String.valueOf(storage.getStorage().charAt(selection-2))) && String.valueOf(storage.getStorage().charAt(selection-1)).equals("0")){
            storage.removeCharAtPosition(selection-1);
            Log.i("storage", "storage: " + storage.getStorage());
            storage.addCharAtPosition(selection-1, value);
            txt.setText(storage.getStorage());
            txt.setSelection(storage.getStorage().length());
        } else{
            storage.addCharAtPosition(selection,value);

        }
    }
}
