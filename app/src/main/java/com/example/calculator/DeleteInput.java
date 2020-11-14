package com.example.calculator;

import android.view.View;
import android.widget.Button;
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
        String input = ((Button) v).getText().toString();
        if (input.equals("⌫")) {
            deleteMethod();
        } else {
            deleteAllMethod();
        }
    }

    //Method  deleting one character
    void deleteMethod() {
        try {
            int selection = txt.getSelectionEnd();

            if (selection == 0) {
                return;
            }
            else {
                storage.removeCharAtPosition(selection - 1);

            }

        } catch (IndexOutOfBoundsException e){
            int selection = txt.getSelectionEnd();
            storage.removeCharAtPosition(selection - 1);
        }
    }

    //Method clearing all storage
    void deleteAllMethod() {
        storage.clearStorage();


    }
}

