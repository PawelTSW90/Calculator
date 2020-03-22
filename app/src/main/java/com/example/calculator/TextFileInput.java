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
        int kursor = txt.getSelectionEnd();
        storage.addCharToString(((Button) v).getText().toString());
        this.txt.setText(storage.returnString());
        txt.setSelection(kursor+1);
        Log.i("proba", storage.storage);







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
