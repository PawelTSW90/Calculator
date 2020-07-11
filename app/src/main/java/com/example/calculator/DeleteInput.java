package com.example.calculator;

import android.util.Log;
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
        int selection = txt.getSelectionEnd();
        Log.i("proba", ""+storage.getStorage().charAt(selection-2));
        Log.i("proba", ""+storage.getStorage().charAt(selection));
        Log.i("proba", ""+Utility.isParseInt(String.valueOf(selection+1)));
        if (selection == 0) {
            return;
            // ex. 5+602+7  if cursor is positioned between 6 and 0, remove both 6 and 0
        } else if(Utility.containArithmeticSymbol(String.valueOf(storage.getStorage().charAt(selection-2))) && String.valueOf(storage.getStorage().charAt(selection)).equals("0") && Utility.isParseInt(String.valueOf(storage.getStorage().charAt(selection+1)))){
            storage.removeCharAtPosition(selection);
            storage.removeCharAtPosition(selection-1);

        }

        else {
            storage.removeCharAtPosition(selection - 1);

        }

    }
    //Method clearing all storage
    void deleteAllMethod() {
        storage.clearStorage();


    }
}

