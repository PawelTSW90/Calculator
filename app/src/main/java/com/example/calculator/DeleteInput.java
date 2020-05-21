package com.example.calculator;

import android.view.View;
import android.widget.EditText;

public class DeleteInput implements View.OnClickListener {

    private EditText txt;
    private StorageRefactorClass storage;


    DeleteInput(EditText txt, StorageRefactorClass storage) {
        this.txt = txt;
        this.storage = storage;
    }

    @Override

    public void onClick(View v) {
        /*boolean onlyZeroBackward;
        boolean onlyZeroForward;
        int selection = txt.getSelectionEnd();
        if(isInteger(String.valueOf(storage.storage.charAt(selection-1)))){
            storage.removeCharAtPosition(selection-1);
            int currentSelection = selection-1;
            txt.setSelection(currentSelection);
            Log.i("selection position: ", "" + selection);
            Log.i("storage", "" + storage.storage);
            for(int x = currentSelection; x>=0; x--){
                if(isInteger(String.valueOf(storage.storage.charAt(x))) && !String.valueOf(storage.storage.charAt(x)).equals("0")){
                    onlyZeroBackward = false;
                    break;

                } else if(isInteger(String.valueOf(storage.storage.charAt(x))) && String.valueOf(storage.storage.charAt(x)).equals("0")){
                    onlyZeroBackward = true;
                    break;

                }

            }
        } else





         */
        deleteMethod();

    }
    //Method responsible for deleting one character and setting selection at right spot
    void deleteMethod(){
        int selection = txt.getSelectionEnd();
        if (selection == 0) {
            return;
        } else {
            String firstPart = storage.storage.substring(0, selection);
            String secondPart = storage.storage.substring(selection);
            storage.storage = "";
            firstPart = firstPart.substring(0, firstPart.length() - 1);
            storage.storage += firstPart;
            storage.storage += secondPart;
            this.txt.setText(storage.returnString());
            txt.setSelection(selection - 1);
        }

    }

    private boolean isInteger(String input) {
        return !input.contains("+") && !input.contains("-") && !input.contains("×") && !input.contains("÷");


    }
}

