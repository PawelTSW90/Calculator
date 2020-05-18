package com.example.calculator;

import android.view.View;
import android.widget.EditText;

public class ResultClass implements View.OnClickListener {
    private EditText txt;
    private StorageClass storage;
    private Calculating calculating;

    ResultClass(EditText text, StorageClass StorageClass, Calculating calculating) {
        this.txt = text;
        this.storage = StorageClass;
        this.calculating = calculating;

    }


    public void onClick(View v) {
        calculating.WrongFormatChecker(storage);



         if(calculating.cantCount){             //If format is not correct, dont do nothing
            calculating.cantCount = false;
        } else if (!isInteger(storage.storage)) {      //if there are arithmetic symbols, check if you can count result

            storage.addCharToString("=");
            calculating.FinalResult(storage);

            if(calculating.cantCount){                //if not, don't do nothing
                calculating.cantCount = false;

            } else {                                 // else, display result



                this.txt.setText(storage.storage);
                txt.setSelection(txt.length());
            }
        }


    }

    private boolean isInteger(String input) {
        return !input.contains("+") && !input.contains("-") && !input.contains("×") && !input.contains("÷");


    }

}
