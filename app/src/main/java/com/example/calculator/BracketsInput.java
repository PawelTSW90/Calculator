package com.example.calculator;

import android.view.View;
import android.widget.EditText;

public class BracketsInput implements View.OnClickListener {

    private StorageRefactor storage;
    private EditText txt;

    BracketsInput(EditText txt, StorageRefactor storage) {
        this.storage = storage;
        this.txt = txt;
    }

    @Override
    public void onClick(View v) {
        int selection = txt.getSelectionEnd();

        if (storage.getStorage().isEmpty()) {
            storage.addCharAtPosition(0, "(");
        } else {

            if (bracketType().equals("closeBracket")) {
                storage.addCharAtPosition(selection, ")");
            } else {
                storage.addCharAtPosition(selection, "(");
            }

        }
    }

    String bracketType() {
        int bracketOpened = 0;
        int bracketClosed = 0;
        int substringStart = 0;
        int substringEnd = txt.getSelectionEnd();
        String substring = storage.getStorage().substring(substringStart, substringEnd);
        for(int x = substring.length()-1; x>=0; x--){
            if(String.valueOf(substring.charAt(x)).equals("(")){
                bracketOpened = 1;
                break;
            } else if(String.valueOf(substring.charAt(x)).equals(")")){
                bracketClosed = 1;
                break;
            }
        }

        if(bracketOpened ==1){
            return "closeBracket";
        } else if(bracketClosed ==1){
            return "openBracket";
        } return null;





    }
}








