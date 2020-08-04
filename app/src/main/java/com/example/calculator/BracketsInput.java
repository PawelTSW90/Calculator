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
        Log.i("proba", ""+storage.getStorage().charAt(selection));
        //is storage is empty, open bracket
        if (storage.getStorage().isEmpty()) {
            storage.addCharAtPosition(0, "(");
        //if there is arithmetic symbol at cursor position, and there are no brackets to close, don't do nothing
        } else if (bracketType().equals("openBracket") && Utility.containArithmeticSymbol(String.valueOf(storage.getStorage().charAt(selection)))){
            return;
        } else {

            if (bracketType().equals("closeBracket")) {
                storage.addCharAtPosition(selection, ")");
            } else {
                storage.addCharAtPosition(selection, "(");
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








