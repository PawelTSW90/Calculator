package com.example.calculator;

import android.content.Context;
import android.util.Log;
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

    final int RESPONSE_OK = 0;
    final int RESPONSE_OVERAL_LIMIT_REACHED = 1;
    final int RESPONSE_LIMIT_AFTER_COMMA_REACHED = 2;


    DigitsInput(EditText txt, StorageRefactor storage, Context context) {
        this.txt = txt;
        this.storage = storage;
        this.context = context;

    }

    @Override
    public void onClick(View v) {
        if (characterLimitAfterComma(v) == RESPONSE_OVERAL_LIMIT_REACHED) {
            Toast.makeText(this.context, "15 digits limit reached", Toast.LENGTH_SHORT).show();
        } else if (characterLimitAfterComma(v) == RESPONSE_LIMIT_AFTER_COMMA_REACHED) {
            Toast.makeText(this.context, "10 digits after comma limit reached", Toast.LENGTH_SHORT).show();

        } else {
            digitEntry(v);
        }
    }


    //Method blocking entering more than 10 digits after comma
    public int characterLimitAfterComma(View v) {
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

        int nearestRightOperator = getNearestRightSubstringStart(storage.getStorage(), selection, listOfOperators);
        int nearestLeftOperator = getNearestLeftSubstringStart(storage.getStorage(), selection, listOfOperators);

        if ((storage.getStorage().isEmpty() || storage.getStorage().length() < 2)) {
            return RESPONSE_OK;
        }
        if (nearestLeftOperator != -1)
            substringStart = nearestLeftOperator;

        if (nearestRightOperator != -1)
            substringEnd = nearestRightOperator;


        substring = storage.getStorage().substring(substringStart, substringEnd);


        boolean tmpCharacterLimit = Pattern.matches(".*\\d{14,}.*", substring);
        boolean tmpCharacterLimitAfterComma = Pattern.matches(".*,\\d{9,}.*", substring);

        if (tmpCharacterLimit) {
            return RESPONSE_OVERAL_LIMIT_REACHED;
        } else if (tmpCharacterLimitAfterComma) {
            return RESPONSE_LIMIT_AFTER_COMMA_REACHED;
        }
        return RESPONSE_OK;


        /*else if (storage.getStorage().length() >= 2) {


            if ((nearestLeftOperator == -1) && (nearestRightOperator != -1)) {
                substring = storage.getStorage().substring(0, nearestRightOperator);

                boolean tmpCharacterLimit = Pattern.matches(".*\\d{14,}.*", substring);
                boolean tmpCharacterLimitAfterComma = Pattern.matches(".*\\d{9,}.*", substring);

                if (tmpCharacterLimit) {
                    characterLimit = 1;
                } else if (tmpCharacterLimitAfterComma) {
                    characterLimitAfterComma = 1;
                }


            } else if ((nearestLeftOperator != -1) && nearestRightOperator == -1) {
                substring = storage.getStorage().substring(nearestLeftOperator, storage.getStorage().length()-1);

                boolean tmpCharacterLimit = Pattern.matches(".*\\d{14,}.*", substring);
                boolean tmpCharacterLimitAfterComma = Pattern.matches(".*\\d{9,}.*", substring);

                if (tmpCharacterLimit) {
                    characterLimit = 1;
                } else if (tmpCharacterLimitAfterComma) {
                    characterLimitAfterComma = 1;
                }
            } else if ((nearestLeftOperator != -1) && nearestRightOperator != -1) {
                substring = storage.getStorage().substring(nearestLeftOperator, nearestRightOperator);

                boolean tmpCharacterLimit = Pattern.matches(".*\\d{14,}.*", substring);
                boolean tmpCharacterLimitAfterComma = Pattern.matches(".*\\d{19,}.*", substring);

                if (tmpCharacterLimit) {
                    characterLimit = 1;
                } else if (tmpCharacterLimitAfterComma) {
                    characterLimitAfterComma = 1;
                }
            } else {
                substring = storage.getStorage().substring(0, storage.getStorage().length() - 1);

                boolean tmpCharacterLimit = Pattern.matches(".*\\d{14,}.*", substring);

                if (tmpCharacterLimit) {
                    characterLimit = 1;
                }


            }



        }
         */



    }

    int getNearestRightSubstringStart(String str, int cursor, ArrayList<String> substringList) {
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


    public void digitEntry(View v) {
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
        //if previous character is not zero, insert new digit
        else if (!String.valueOf(storage.getStorage().charAt(selection - 1)).equals("0")) {
            storage.addCharAtPosition(selection, value);

        } else if (selection < 2) {
            storage.addCharAtPosition(selection, value);

        }
        //If there is arithmetic symbol two characters back, and 0 digit one character back, replace 0 with new digit
        else if (!Utility.isParseInt(String.valueOf(storage.getStorage().charAt(selection - 2))) && String.valueOf(storage.getStorage().charAt(selection - 1)).equals("0")) {
            storage.removeCharAtPosition(selection - 1);
            Log.i("storage", "storage: " + storage.getStorage());
            storage.addCharAtPosition(selection - 1, value);
            txt.setText(storage.getStorage());
            txt.setSelection(storage.getStorage().length());
        } else {
            storage.addCharAtPosition(selection, value);

        }
    }

}
