package com.example.calculator;

import android.util.Log;
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
        //is storage is empty, open bracket
        if (storage.getStorage().isEmpty()) {
            storage.addCharAtPosition(0, "(");

        }
         else {
            Log.i("check", "" + storage.getStorage().charAt(selection-1));
            if (bracketType().equals("closeBracket")) {
                storage.addCharAtPosition(selection, ")");
            } else {
                if(Utility.containArithmeticSymbol(String.valueOf(storage.getStorage().charAt(selection-1)))){
                    storage.addCharAtPosition(selection, "(");
                } else {
                    storage.addCharAtPosition(selection, "×(");
                    txt.setSelection(selection + 2);
                }
            }

        }
    }

    String bracketType() {
        boolean closeBracket = false;
        int substringStart = 0;
        int substringEnd = txt.getSelectionEnd();
        String substring = storage.getStorage().substring(substringStart, substringEnd);
        for (int x = substring.length() - 1; x >= 0; x--) {
            if (String.valueOf(substring.charAt(x)).equals("(")) {
                closeBracket = true;
                break;
            } else if (String.valueOf(substring.charAt(x)).equals(")")) {
                break;
            }
        }

        if (closeBracket) {
            return "closeBracket";
        } else {
            return "openBracket";
        }


    }
}








