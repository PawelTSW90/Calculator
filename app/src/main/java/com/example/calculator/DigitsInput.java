package com.example.calculator;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class DigitsInput implements View.OnClickListener {

    private final EditText txt;
    private final StorageRefactor storage;
    private final Context context;
    private final DeleteInput delete;
    Toast toast = null;

    private final int RESPONSE_15_DIGITS_LIMIT_REACHED = 1;
    private final int RESPONSE_LIMIT_AFTER_COMMA_REACHED = 2;
    private final int RESPONSE_TOTAL_CHARACTERS_LIMIT_REACHED = 3;
    private final int RESPONSE_WRONG_FORMAT_USED = 4;


    DigitsInput(EditText txt, StorageRefactor storage, Context context, DeleteInput delete) {
        this.txt = txt;
        this.storage = storage;
        this.context = context;
        this.delete = delete;


    }

    @Override
    public void onClick(View v) {


        //checking if format is correct. If not display toast

        //15 digits limit reached toast
        if (wrongFormatChecker() == RESPONSE_15_DIGITS_LIMIT_REACHED) {
            if (toast != null) {
                toast.cancel();
            }
            toast = Toast.makeText(context, "15 digits limit reached", Toast.LENGTH_SHORT);
            toast.show();

            //10 digits after comma limit reached toast
        } else if (wrongFormatChecker() == RESPONSE_LIMIT_AFTER_COMMA_REACHED) {
            if (toast != null) {
                toast.cancel();
            }
            toast = Toast.makeText(context, "10 digits after comma limit reached", Toast.LENGTH_SHORT);
            toast.show();

            //100 characters limit reached toast
        } else if (wrongFormatChecker() == RESPONSE_TOTAL_CHARACTERS_LIMIT_REACHED) {
            if (toast != null) {
                toast.cancel();
            }
            toast = Toast.makeText(context, "100 characters limit reached", Toast.LENGTH_SHORT);
            toast.show();
        } else if (wrongFormatChecker() == RESPONSE_WRONG_FORMAT_USED) {
        }

        //if format is correct:

        //check if 0 entry is allowed
        else if (((Button) v).getText().toString().equals("0")) {
            checkZero(v);
        }
        // check if other digits entry is allowed
        else {
            digitEntry(v);
        }
    }


    //Method blocking entering more than 15 digits between arithmetic operators or 10 digits after comma
    public int wrongFormatChecker() {
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
        boolean tmpTwoCommas = Pattern.matches(".*,,.*", storage.getStorage());

        if (tmpCharacterLimit) {
            return RESPONSE_15_DIGITS_LIMIT_REACHED;
        } else if (tmpCharacterLimitAfterComma) {
            return RESPONSE_LIMIT_AFTER_COMMA_REACHED;
        } else if (tmp100CharactersLimit) {
            return RESPONSE_TOTAL_CHARACTERS_LIMIT_REACHED;
        } else if (tmpTwoCommas) {
            return RESPONSE_WRONG_FORMAT_USED;
        } else

            return ENTRY_ALLOWED;

    }

    //method returns first no-digit entry after cursor, or -1 if operator is not found
    int getNearestRightSubstringEnd(String str, int cursor, ArrayList<String> substringList) {
        int nearestRightOperator = str.length();
        boolean operatorFound = false;

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

    // method is checking if no-0 digit input is allowed
    private void digitEntry(View v) {

        int selection = txt.getSelectionEnd();
        String currentValue = ((Button) v).getText().toString();

        //if cursor is at the beginning, add number
        if ((selection == 0)) {
            storage.addAtPosition(0, currentValue);
        }
        //if previous character is closed bracket, add "x" before new number
        else if (String.valueOf(storage.getStorage().charAt(selection - 1)).equals(")")) {
            storage.addAtPosition(selection, currentValue);
            storage.addAtPosition(selection, "×");

        }

        //if there is just zero in storage, and cursor is placed after it, replace 0 with new digit
        else if ((storage.getStorage().length() == 1) && String.valueOf(storage.getStorage().charAt(0)).equals("0")) {
            delete.deleteChar(selection);
            storage.addAtPosition(0, currentValue);
            txt.setSelection(1);

        }

        //if previous currentValue is "0" and next currentValue is comma, replace zero with new digit if it's different than 0
        else if (String.valueOf(storage.getStorage().charAt(selection - 1)).equals("0") && selection < storage.getStorage().length() && String.valueOf(storage.getStorage().charAt(selection)).equals(",")) {
            delete.deleteChar(selection - 1);
            storage.addAtPosition(selection - 1, currentValue);
            txt.setSelection(selection);
        }

        //if previous character is not zero, insert new digit
        else if (!String.valueOf(storage.getStorage().charAt(selection - 1)).equals("0")) {
            storage.addAtPosition(selection, currentValue);

        } else if (selection < 2) {
            storage.addAtPosition(selection, currentValue);

        }
        //If there is arithmetic symbol two characters back, and 0 digit one character back, replace 0 with new digit
        else if (Utility.containArithmeticSymbol(String.valueOf(storage.getStorage().charAt(selection - 2))) && String.valueOf(storage.getStorage().charAt(selection - 1)).equals("0")) {
            delete.deleteChar(selection);
            storage.addAtPosition(selection - 1, currentValue);
        } else {
            storage.addAtPosition(selection, currentValue);

        }
    }

    // 0 digit input
    public void checkZero(View v) {
        String value = ((Button) v).getText().toString();
        int selection = txt.getSelectionEnd();
        //if cursor is at position 0:

        if (selection == 0) {
            //if storage is empty - input allowed
            if (storage.getStorage().isEmpty()) {
                storage.addAtPosition(selection, value);

            }
            //if next character is comma, input allowed
            else if (String.valueOf(storage.getStorage().charAt(0)).equals(",")) {
                storage.addAtPosition(selection, value);

            }

            //otherwise, input not allowed


        }
        //cursor position as last, storage smaller than 2:

        else if (storage.getStorage().length() < 2 && selection == storage.getStorage().length()) {

            //input allowed

            storage.addAtPosition(selection, value);

        }

        //cursor positioned as last:

        else if (selection == storage.getStorage().length()) {

            //if previous character is 0, and there is arithmetic symbol two characters back, input not allowed
            if (Utility.containArithmeticSymbol(String.valueOf(storage.getStorage().charAt(selection - 2))) && String.valueOf(storage.getStorage().charAt(selection - 1)).equals("0")) {
            } else {
                //input allowed
                storage.addAtPosition(selection, value);
            }

        } else {
            //cursor positioned in the middle:

            //if current character is comma and previous character is 0, input not allowed
            if (String.valueOf(storage.getStorage().charAt(selection)).equals(",") && String.valueOf(storage.getStorage().charAt(selection - 1)).equals("0")) {

                // if current and previous characters are commas, input not allowed
            } else if (String.valueOf(storage.getStorage().charAt(selection)).equals(",") && String.valueOf(storage.getStorage().charAt(selection - 1)).equals(",")) {
            } else {

                // input allowed
                storage.addAtPosition(selection, value);

            }

        }


    }


}


