package com.example.calculator;

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
        int kursor = txt.getSelectionEnd();                          //initialize cursor position
        storage.addCharToString(((Button) v).getText().toString());
        this.txt.setText(storage.returnString());
        txt.setSelection(kursor+1);                                  //place cursor after last added value


        if(kursor!=storage.storage.length()-1){                      //If we have more than one value
             String firstPart = storage.storage.substring(0, kursor);
             String secondPart = storage.storage.substring(kursor, storage.storage.length()-1); //split storage into two halves,
             storage.storage = "";                                   //first one: all values before new value
             storage.storage+=firstPart;                             //second one: all values after new value
             storage.addCharToString(((Button) v).getText().toString());  //merge new storage from first half, NEW VALUE and second half
             storage.storage+=secondPart;
             this.txt.setText(storage.returnString());
             txt.setSelection(kursor+1);

         }







if(v.getResources().getResourceName(v.getId()).contains("C")){     //if C has been clicked, remove all values
    storage.storage = "";
    this.txt.setText(storage.returnString());
}

if(v.getResources().getResourceName(v.getId()).contains("()")){
    String bracketOpen = "(";
    String bracketClose = ")";
    int countOpenBracket = 0;
    int countCloseBracket = 0;
    for(int i =0; i<storage.storage.length();i++){


    }
}



    }


}
