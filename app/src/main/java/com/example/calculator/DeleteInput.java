package com.example.calculator;

import android.view.View;
import android.widget.EditText;

public class DeleteInput implements View.OnClickListener {

    private EditText txt;
    private StorageRefactor storage;


    DeleteInput(EditText txt, StorageRefactor storage) {
        this.txt = txt;
        this.storage = storage;
    }

    @Override

    public void onClick(View v) {
        deleteMethod();
    }
    //Method responsible for deleting one character and placing cursor at right spot
    void deleteMethod(){
        int selection = txt.getSelectionEnd();
        if (selection == 0) {
            return;
        } else if(storage.getStorage().length()<=1){
            storage.removeCharAtPosition(selection-1);
        }


        else if((selection == 1) && !Utility.isParseInt(String.valueOf(storage.getStorage().charAt(selection)))){
            storage.removeCharAtPosition(0);
            storage.removeCharAtPosition(0);
            txt.setText(storage.getStorage());
            txt.setSelection(1);
        }
        else {
            storage.removeCharAtPosition(selection-1);

        }

    }
}

