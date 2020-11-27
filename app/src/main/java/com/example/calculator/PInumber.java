package com.example.calculator;

import android.view.View;
import android.widget.EditText;

public class PInumber implements View.OnClickListener {

    StorageRefactor storage;
    EditText txt;

    public PInumber(EditText txt, StorageRefactor storage) {
        this.storage = storage;
        this.txt = txt;
    }

    @Override
    public void onClick(View v) {
        isPiAllowed();

    }

    public void isPiAllowed() {
        int selection = txt.getSelectionEnd();
        if (storage.getStorage().isEmpty()) {
            storage.addAtPosition(selection, "π");
        } else if (selection == 0) {
            if (!String.valueOf(storage.getStorage().charAt(selection)).equals("π")) {
                storage.addAtPosition(selection, "π×");
            }

        } else if (selection == storage.getStorage().length()) {
            if (Utility.containArithmeticSymbol(String.valueOf(storage.getStorage().charAt(selection - 1))) || String.valueOf(storage.getStorage().charAt(selection - 1)).equals("(")) {
                storage.addAtPosition(selection, "π");
            } else if (Utility.isDouble(String.valueOf(storage.getStorage().charAt(selection - 1))) || String.valueOf(storage.getStorage().charAt(selection - 1)).equals(")")) {
                storage.addAtPosition(selection, "×π");
                txt.setSelection(selection + 2);
            }
        }


    }
}
