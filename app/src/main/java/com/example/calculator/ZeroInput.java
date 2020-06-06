package com.example.calculator;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ZeroInput implements View.OnClickListener {
    private EditText txt;
    private StorageRefactor storage;

    ZeroInput(EditText txt, StorageRefactor storage) {
        this.txt = txt;
        this.storage = storage;
    }
    @Override
    public void onClick(View v) {
        isZeroAllowed(v);

    }

    void isZeroAllowed(View v) {
        String value = ((Button)v).getText().toString();
        int selection = txt.getSelectionEnd();

        if(selection ==0 && storage.getStorage().length()>0){
            return;
        }

        else if((storage.getStorage().length()==1 && String.valueOf(storage.getStorage().charAt(selection-1)).equals("0"))){
            return;
        }

        else if(selection<3){
            storage.addCharAtPosition(selection,value);

        }
        //if previous character is 0, and there is arithmetic symbol two characters back, dont do nothing
        else if(!Utility.isParseInt(String.valueOf(storage.getStorage().charAt(selection-2))) && String.valueOf(storage.getStorage().charAt(selection-1)).equals("0")){
            return;
        }


        else {
            storage.addCharAtPosition(selection,value);

        }

    }

}

