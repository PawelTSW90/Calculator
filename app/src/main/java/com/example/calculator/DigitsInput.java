package com.example.calculator;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class DigitsInput implements View.OnClickListener {
    private EditText txt;
    private StorageRefactor storage;
    private Context context;

    private final int RESPONSE_15_DIGITS_LIMIT_REACHED = 1;
    private final int RESPONSE_LIMIT_AFTER_COMMA_REACHED = 2;
    private final int RESPONSE_TOTAL_CHARACTERS_LIMIT_REACHED = 3;


    DigitsInput(EditText txt, StorageRefactor storage, Context context) {
        this.txt = txt;
        this.storage = storage;
        this.context = context;

    }

    @Override
    public void onClick(View v) {
        //checking if format is correct. If not display toast
        if (characterLimitAfterComma(v) == RESPONSE_15_DIGITS_LIMIT_REACHED) {
            Toast.makeText(this.context, "15 digits limit reached", Toast.LENGTH_SHORT).show();
        } else if (characterLimitAfterComma(v) == RESPONSE_LIMIT_AFTER_COMMA_REACHED) {
            Toast.makeText(this.context, "10 digits after comma limit reached", Toast.LENGTH_SHORT).show();

        } else if(characterLimitAfterComma(v) == RESPONSE_TOTAL_CHARACTERS_LIMIT_REACHED){
            Toast.makeText(this.context, "100 characters limit reached", Toast.LENGTH_SHORT).show();
        }

        //0 input
        else if(((Button)v).getText().toString().equals("0")){
            checkZero(v);
        }
        // other digits input
        else {
            digitEntry(v);
        }
    }


    //Method blocking entering more than 15 digits between arithmetic operators or 10 digits after comma
    public int characterLimitAfterComma(View v) {
        int ENTRY_ALLOWED = 0;
        int selection = txt.getSelectionEnd();
        int substringStart = 0;
        int substringEnd = storage.getStorage().length() - 1;
        String substring;
        ArrayList<String> listOfOperators = new ArrayList<String>() {{
            add("+");
            add("-");
            add("(");
            add(")");
            add(",");
            add("×");
            add("÷");
        }};

        int nearestRightOperator = getNearestRightSubstringEnd(storage.getStorage(), selection, listOfOperators);
        int nearestLeftOperator = getNearestLeftSubstringStart(storage.getStorage(), selection, listOfOperators);


        if ((storage.getStorage().isEmpty() || storage.getStorage().length() < 2)) {
            return ENTRY_ALLOWED;
        }
        if (nearestLeftOperator != -1)
            substringStart = nearestLeftOperator;

        if (nearestRightOperator != -1)
            substringEnd = nearestRightOperator;


        substring = storage.getStorage().substring(substringStart, substringEnd);


        boolean tmpCharacterLimit = Pattern.matches(".*\\d{14,}.*", substring);
        boolean tmpCharacterLimitAfterComma = Pattern.matches(".*,\\d{9,}.*", substring);
        boolean tmp100CharactersLimit = Pattern.matches(".{100,}", storage.getStorage());

        if (tmpCharacterLimit) {
            return RESPONSE_15_DIGITS_LIMIT_REACHED;
        } else if (tmpCharacterLimitAfterComma) {
            return RESPONSE_LIMIT_AFTER_COMMA_REACHED;
        } else if(tmp100CharactersLimit){
            return  RESPONSE_TOTAL_CHARACTERS_LIMIT_REACHED;
        } else
        return ENTRY_ALLOWED;

    }
            //method return first no-digit entry after cursor, or -1 if operator is not found
    int getNearestRightSubstringEnd(String str, int cursor, ArrayList<String> substringList) {
        int nearestRightOperator = str.length();
        boolean operatorFound = false;
            //
        for (String element : substringList) {
            int tmp2 = str.indexOf(element, cursor);
            if ((tmp2 < nearestRightOperator) && (tmp2 != -1)) {
                nearestRightOperator = tmp2;
                operatorFound = true;
            }
        }
        if (!operatorFound)
            nearestRightOperator = -1;

        return nearestRightOperator;
    }
            // method returns first no-digit entry before cursor, or -1 if operator is not found
    int getNearestLeftSubstringStart(String str, int cursor, ArrayList<String> substringList) {
        int nearestLeftOperator = -1;
        for (String element : substringList) {
            int tmp2 = str.lastIndexOf(element, cursor);
            if (tmp2 > nearestLeftOperator) {
                nearestLeftOperator = tmp2;

            }

        }

        return nearestLeftOperator;
    }

            //digit input except 0
    private  void digitEntry(View v) {

        int selection = txt.getSelectionEnd();
        String value = ((Button) v).getText().toString();

        //if storage is empty, insert new digit
        if (storage.getStorage().length() == 0) {
            storage.addCharAtPosition(0, value);
        }

        //if cursor is at the beginning, and storage contains some characters, insert digit as a first one
        else if ((selection == 0) && storage.getStorage().length() >= 1) {
            storage.addCharAtPosition(selection, value);
        }
        //if there is just zero in storage, and cursor is placed after it, replace 0 with new digit
        else if((storage.getStorage().length()==1)&&String.valueOf(storage.getStorage().charAt(0)).equals("0")){
            storage.removeCharAtPosition(0);
            storage.addCharAtPosition(0, value);
            txt.setSelection(1);

        }

        //if previous value is "0" and next value is comma, replace zero with new digit if it's different than 0
        else if(String.valueOf(storage.getStorage().charAt(selection-1)).equals("0")&& selection<storage.getStorage().length() &&String.valueOf(storage.getStorage().charAt(selection)).equals(",")){
            storage.removeCharAtPosition(selection - 1);
            storage.addCharAtPosition(selection - 1, value);

            txt.setSelection(selection);

        }

        //if previous character is not zero, insert new digit
        else if (!String.valueOf(storage.getStorage().charAt(selection - 1)).equals("0")) {
            storage.addCharAtPosition(selection, value);

        } else if (selection < 2) {
            storage.addCharAtPosition(selection, value);

        }
        //If there is arithmetic symbol two characters back, and 0 digit one character back, replace 0 with new digit
        else if (Utility.containArithmeticSymbol(String.valueOf(storage.getStorage().charAt(selection - 2))) && String.valueOf(storage.getStorage().charAt(selection - 1)).equals("0")) {
            storage.removeCharAtPosition(selection - 1);
            storage.addCharAtPosition(selection - 1, value);

            txt.setSelection(storage.getStorage().length());
        }


        else {
            storage.addCharAtPosition(selection, value);

        }
    }
    // 0 digit input
    public void checkZero(View v){
        String value = ((Button)v).getText().toString();
        int selection = txt.getSelectionEnd();
        //cursor position 0, storage is not empty, wait
        if(selection ==0 && storage.getStorage().length()>0){
            return;
        }
        //if there is only 0, wait
        else if((storage.getStorage().length()==1 && String.valueOf(storage.getStorage().charAt(selection-1)).equals("0"))){
            return;
        }

        else if(selection<3){
            storage.addCharAtPosition(selection,value);

        }
        //if previous character is 0, and there is arithmetic symbol two characters back, don't do nothing
        else if(Utility.containArithmeticSymbol(String.valueOf(storage.getStorage().charAt(selection-2))) && String.valueOf(storage.getStorage().charAt(selection-1)).equals("0")){
            return;
        }


        else {
            storage.addCharAtPosition(selection,value);

        }
    }

}
