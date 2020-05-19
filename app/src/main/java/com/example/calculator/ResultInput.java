package com.example.calculator;

import android.view.View;
import android.widget.EditText;

public class ResultInput implements View.OnClickListener {
    private EditText txt;
    private StorageRefactorClass storage;
    private CalculatingClass calculating;

    ResultInput(EditText text, StorageRefactorClass StorageClass, CalculatingClass calculating) {
        this.txt = text;
        this.storage = StorageClass;
        this.calculating = calculating;

    }


    public void onClick(View v) {
        calculating.WrongFormatChecker(storage);


            //If format is not correct, dont do nothing
         if(calculating.cantCount){
            calculating.cantCount = false;
             //if there are arithmetic symbols, check if you can count result
        } else if (!isInteger(storage.storage)) {

            storage.addCharToString("=");
            calculating.FinalResult(storage);
            //if not, don't do nothing
            if(calculating.cantCount){
                calculating.cantCount = false;
            // else, display result
            } else {



                this.txt.setText(storage.storage);
                txt.setSelection(txt.length());
            }
        }


    }

    private boolean isInteger(String input) {
        return !input.contains("+") && !input.contains("-") && !input.contains("×") && !input.contains("÷");


    }

}
