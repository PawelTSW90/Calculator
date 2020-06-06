package com.example.calculator;

import android.view.View;
import android.widget.EditText;

public class ZeroInput implements View.OnClickListener {
    private EditText txt;
    private StorageRefactor storage;
    MainInputClass input;

    ZeroInput(EditText txt, StorageRefactor storage, MainInputClass file) {
        this.txt = txt;
        this.storage = storage;
        this.input = file;
    }
    @Override
    public void onClick(View v) {
        isZeroAllowed(v);


    }

    void isZeroAllowed(View v) {
        int selection = txt.getSelectionEnd();

        if(selection ==0 && storage.getStorage().length()>0){
            return;
        }

        if(selection<3){
            input.addChar(v);
            input.setCursor(v);
        }

        else if(!Utility.isInteger(String.valueOf(storage.getStorage().charAt(selection-2))) && String.valueOf(storage.getStorage().charAt(selection-1)).equals("0")){
            return;
        } else {
            input.addChar(v);
            input.setCursor(v);
        }

    }






}

