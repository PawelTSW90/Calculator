package com.example.calculator;

import android.view.View;
import android.widget.EditText;

public class CommaInput implements View.OnClickListener {
    private final EditText txt;
    private final StorageRefactor storage;


    CommaInput(EditText txt, StorageRefactor storage) {
        this.txt = txt;
        this.storage = storage;

    }

    public void onClick(View v) {

        //if storage is empty, display "0," and set selection after comma
        if (storage.getStorage().isEmpty()) {
            storage.setStorage("0,");
            this.txt.setText(storage.getStorage());
            txt.setSelection(2);

        }  else {
            if (isCommaAllowed())
                commaAllowed();
        }

    }

    //Method is checking if comma is allowed
    boolean isCommaAllowed() {
        int selection = txt.getSelectionEnd();
        boolean isCommaBackward = false;
        boolean isCommaForward = false;
        boolean piSymbol = false;
        //Backward loop looking for comma between selection and first arithmetic symbol
        for (int i = Math.max(selection - 1, 0); i >= 0; i--) {

            if (!Utility.isDouble(String.valueOf(storage.getStorage().charAt(i)))) {

                if (String.valueOf(storage.getStorage().charAt(i)).equals(",")) {
                    isCommaBackward = true;

                }
                break;

            } else if (String.valueOf(storage.getStorage().charAt(i)).equals(",")) {
                isCommaBackward = true;
                break;
            }
        }
        //Forward Loop looking for comma between selection and first arithmetic symbol
        for (int x = Math.max(selection - 1, 0); x <= storage.getStorage().length() - 1; x++) {
            if (String.valueOf(storage.getStorage().charAt(x)).equals(",")) {
                isCommaForward = true;
                break;
            }
        }
        //if previous character is π, entry not allowed
        if(!storage.getStorage().isEmpty() && selection >0){
            if(String.valueOf(storage.getStorage().charAt(selection-1)).equals("π")){
                piSymbol = true;
            }
        // //if next character is π, entry not allowed
        } if(storage.getStorage().length()>0 && selection !=storage.getStorage().length()){
            if(String.valueOf(storage.getStorage().charAt(selection)).equals("π")){
                piSymbol = true;
            }
        }

        return !isCommaBackward && !isCommaForward && !piSymbol;
    }

    void commaAllowed() {
        //initialize selection position
        int selection = txt.getSelectionEnd();

        //if selection is 0, add ",0"
        if (selection == 0) {
            storage.addAtPosition(0, "0,");
        }

        //if previous character is arithmetic or PI symbol, add 0,
        else if (Utility.containArithmeticSymbol(String.valueOf(storage.getStorage().charAt(selection - 1)))) {
            storage.addAtPosition(selection, "0,");
            this.txt.setSelection(selection + 2);

        } else {
            storage.addAtPosition(selection, ",");
            txt.setSelection(selection + 1);

        }
    }


}



