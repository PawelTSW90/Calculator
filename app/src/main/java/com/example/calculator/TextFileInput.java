package com.example.calculator;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TextFileInput implements View.OnClickListener {
    TextView txt;
    StorageClass storage;

    TextFileInput(EditText text, StorageClass storage) {
        this.txt = text;
        this.storage = storage;


    }


    @Override
    public void onClick(View v) {
        if(storage.storage == "0"){                               //if 0 is displaying, replace it with new value
            storage.stringZero(((Button)v).getText().toString());
            this.txt.setText(storage.returnString());
        } else
        storage.addCharToString(((Button) v).getText().toString()); //else, add new value to existing one
        this.txt.setText(storage.returnString());


if(v.getResources().getResourceName(v.getId()).contains("C")){     //if C has been clicked, display 0
    storage.storage = "0";
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
