package com.example.calculator;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TextFileInput implements View.OnClickListener {
    EditText txt;
    StorageClass storage;

    TextFileInput(EditText text, StorageClass storage) {
        this.txt = text;
        this.storage = storage;


    }


    @Override
    public void onClick(View v) {
        int kursor = txt.getSelectionEnd();                         //initialize cursor position
        storage.addCharToString(((Button) v).getText().toString());
        this.txt.setText(storage.returnString());
        txt.setSelection(kursor + 1);                              //set cursor position after last added value

        if (kursor != storage.storage.length() - 1) {               //if cursor is not positioned as last:
            String firstPart = storage.storage.substring(0, kursor);
            String secondPart = storage.storage.substring(kursor, storage.storage.length() - 1); //split storage into two halves,
            storage.storage = "";                                   //first one: all values before new value
            storage.storage += firstPart;                             //second one: all values after new value
            storage.addCharToString(((Button) v).getText().toString());  //merge new storage from first half, NEW VALUE and second half
            storage.storage += secondPart;
            this.txt.setText(storage.returnString());
            txt.setSelection(kursor + 1);

        }


        if (v.getResources().getResourceName(v.getId()).contains("C")) {     //if C has been touched, remove all values
            storage.storage = "";
            this.txt.setText(storage.returnString());
        }

        if(v.getResources().getResourceName(v.getId()).contains("0")){
            Log.i("First", String.valueOf(storage.storage.charAt(kursor-1)));
            Log.i("Second", String.valueOf(storage.storage.charAt(kursor-2)));
            if(storage.storage.charAt(kursor-1)==0){
                if(!isInteger(Character.toString(storage.storage.charAt(kursor-2)))){
                    this.txt.setText("Siemano");
                    /*storage.addCharToString("");
                    this.txt.setText(storage.returnString());
                    txt.setSelection(kursor+1);
*/


                }
            }
        }

        if (v.getResources().getResourceName(v.getId()).contains("delete")) { //if delete has been touched:

            if (storage.storage.length() > 1) {
                String tmp = storage.storage.substring(0, storage.storage.length() - 2);    //delete last value
                storage.storage = "";
                storage.storage += tmp;
                this.txt.setText(storage.returnString());
                txt.setSelection(storage.storage.length());
            } else {
                storage.storage = "";
                this.txt.setText(storage.returnString());

            }

        }


    }

    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
