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
    Toast toast = null;


    ResultInput(EditText text, StorageRefactor StorageClass, Calculating calculating, Context context) {
        this.txt = text;
        this.storage = StorageClass;
        this.calculating = calculating;
        this.context = context;
    }


    public void onClick(View v) {
        //run wrongFormatChecker method to check if format is correct
        calculating.wrongFormatChecker(storage.getStorage());

        //if storage is empty, wait
        if (storage.getStorage().isEmpty()) {
            return;
        }
        //if format is correct start counting
        if (Utility.containArithmeticSymbol(storage.getStorage()) && calculating.wrongFormatChecker(storage.getStorage()) == 0) {
            storage.addCharAtPosition(storage.getStorage().length(), "=");
            txt.setText(calculating.countResult(storage));
            txt.setSelection(storage.getStorage().length());
            //if format is wrong, display toast
        } else if (calculating.wrongFormatChecker(storage.getStorage()) == 1) {
            if (toast != null) {
                toast.cancel();
            }
            toast = Toast.makeText(context, "Wrong format used", Toast.LENGTH_SHORT);
            toast.show();
            //if 15 digits limit has been reached, display toast
        } else if (calculating.wrongFormatChecker(storage.getStorage()) == 2) {
            if (toast != null) {
                toast.cancel();
            }
            toast = Toast.makeText(context, "15 digits limit reached", Toast.LENGTH_SHORT);
            toast.show();
            //if 10 digits after comma limit has been reached, display toast
        } else if (calculating.wrongFormatChecker(storage.getStorage()) == 3) {
            if (toast != null) {
                toast.cancel();
            }
            toast = Toast.makeText(context, "10 digits after comma limit reached", Toast.LENGTH_SHORT);
            toast.show();
            //if 100 characters limit has been reached, display toast
        } else if (calculating.wrongFormatChecker(storage.getStorage()) == 4) {
            if (toast != null) {
                toast.cancel();
            }
            toast = Toast.makeText(context, "100 characters limit reached", Toast.LENGTH_SHORT);
            toast.show();

        }

    }

}
