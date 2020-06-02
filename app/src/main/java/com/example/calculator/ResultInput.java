package com.example.calculator;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ResultInput implements View.OnClickListener {
    private EditText txt;
    private StorageRefactorClass storage;
    private CalculatingClass calculating;
    private Context context;

    ResultInput(EditText text, StorageRefactorClass StorageClass, CalculatingClass calculating, Context context) {
        this.txt = text;
        this.storage = StorageClass;
        this.calculating = calculating;
        this.context = context;
    }


    public void onClick(View v) {
        calculating.wrongFormatChecker(storage);
            //Change cantCount value back to original (false) for future calculation
         if(calculating.cantCount){
            calculating.cantCount = false;
            //if no value after operator, display wrong format toast
        } else if(!isInteger(String.valueOf(storage.storage.charAt(storage.storage.length()-1)))){
             Toast.makeText(this.context, "Wrong format used", Toast.LENGTH_SHORT).show();
         }
         //if there are arithmetic symbols, check if you can count result
         else if (!isInteger(storage.storage)) {

            storage.addCharToString("=");
            calculating.countResult(storage);
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
