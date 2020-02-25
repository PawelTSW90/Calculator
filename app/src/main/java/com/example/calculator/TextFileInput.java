package com.example.calculator;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TextFileInput implements View.OnClickListener {
    TextView txt;
    Storage storage;

    TextFileInput(TextView text, Storage storage) {
        this.txt = text;
        this.storage = storage;


    }


    @Override
    public void onClick(View v) {
        if(storage.magazyn == "0"){
            storage.stringZero(((Button)v).getText().toString());
            this.txt.setText(storage.returnString());
        } else
        storage.addCharToString(((Button) v).getText().toString());
        this.txt.setText(storage.returnString());


if(v.getResources().getResourceName(v.getId()).contains("C")){
    storage.magazyn = "0";
    this.txt.setText(storage.returnString());
}



    }


}
