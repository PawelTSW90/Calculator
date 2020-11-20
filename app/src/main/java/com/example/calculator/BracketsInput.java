package com.example.calculator;

import android.view.View;
import android.widget.EditText;

public class BracketsInput implements View.OnClickListener {

    private final StorageRefactor storage;
    private final EditText txt;

    BracketsInput(EditText txt, StorageRefactor storage) {
        this.storage = storage;
        this.txt = txt;
    }

    @Override
    public void onClick(View v) {
        int selection = txt.getSelectionEnd();
        //is storage is empty, open bracket
        if (storage.getStorage().isEmpty()) {
            storage.addAtPosition(0, "(");

        } else {
            try {


                //close bracket input
                if (bracketType().equals("closeBracket")) {
                    storage.addAtPosition(selection, ")");
                    //open bracket input
                }
                //open bracket with "x", if there is a number before it
                else if
                (bracketType().equals("openBracket") && Utility.isDouble(String.valueOf(storage.getStorage().charAt(selection - 1)))) {
                    storage.addAtPosition(selection, "×(");
                    txt.setSelection(selection + 2);
                } else {
                    storage.addAtPosition(selection, "(");
                }
                // open bracket
            } catch (IndexOutOfBoundsException e) {
                storage.addAtPosition(selection, "(");
            }

        }

    }


    //method checking what type of bracket has to be input
    String bracketType() {
        int closedBracket = 0;
        int openedBracket = 0;
        int substringStart = 0;
        int substringEnd = storage.getStorage().length();
        String substring = storage.getStorage().substring(substringStart, substringEnd);
        for (int x = substring.length() - 1; x >= 0; x--) {
            if (String.valueOf(substring.charAt(x)).equals("(")) {
                openedBracket++;
            } else if (String.valueOf(substring.charAt(x)).equals(")")) {
                closedBracket++;
            }
        }

        if (closedBracket < openedBracket) {
            return "closeBracket";
        } else {
            return "openBracket";
        }


    }
}








