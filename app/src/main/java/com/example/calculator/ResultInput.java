package com.example.calculator;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ResultInput implements View.OnClickListener {
    private EditText txt;
    private StorageRefactor storage;
    private Calculating calculating;
    private Context context;

    ResultInput(EditText text, StorageRefactor StorageClass, Calculating calculating, Context context) {
        this.txt = text;
        this.storage = StorageClass;
        this.calculating = calculating;
        this.context = context;
    }


    public void onClick(View v) {
        calculating.wrongFormatChecker(storage.getStorage());
        if(!Utility.isInteger(String.valueOf(storage.getStorage().charAt(storage.getStorage().length()-1)))){
            Toast.makeText(this.context, "Wrong format used", Toast.LENGTH_SHORT).show();

         }
         //if there are arithmetic symbols and format is correct start counting
         else if(!Utility.isInteger(storage.getStorage()) && !calculating.wrongFormatChecker(storage.getStorage())) {

            storage.addCharToString("=");
            txt.setText(calculating.countResult(storage));
            txt.setSelection(storage.getStorage().length());
        } else{
            Toast.makeText(this.context, "Wrong format used", Toast.LENGTH_SHORT).show();
        }

    }

}
