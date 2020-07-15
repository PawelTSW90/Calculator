package com.example.calculator;

import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class BracketsInput implements View.OnClickListener {

    private StorageRefactor storage;
    private EditText txt;

    BracketsInput(EditText txt, StorageRefactor storage){
        this.storage = storage;
        this.txt = txt;
    }

    @Override
    public void onClick(View v) {
        bracketType();

    }

    void bracketType(){

        ArrayList<String> listOfDigits = new ArrayList<String>() {{
            add("0");
            add("1");
            add("2");
            add("3");
            add("4");
            add("5");
            add("6");
            add("7");
            add("8");
            add("9");
        }};


        int selection = txt.getSelectionEnd();
        int substringStart = 0;
        int subStringEnd = storage.getStorage().length()-1;

        if(storage.getStorage().isEmpty()){
            storage.addCharAtPosition(0, "(");


        } else{
           int leftSubstring = getNearestLeftSubstringStart(selection, storage.getStorage(), listOfDigits);
            int rightSubstring = getNearestRightSubstringEnd(selection, storage.getStorage(), listOfDigits);

            if(leftSubstring != -1){
                substringStart = leftSubstring;
            }

            if(rightSubstring != -1){
                subStringEnd = rightSubstring;
            }

            String substring = storage.getStorage().substring(substringStart, subStringEnd);


        }
    }

    int getNearestLeftSubstringStart(int cursor, String string, ArrayList<String> digitsList){
        int substringStart = -1;
        for(String element:digitsList){
            int tmp = string.lastIndexOf(element, cursor);
            if(tmp>substringStart){
                substringStart = tmp;
            }

        }
        return substringStart;

    }

    int getNearestRightSubstringEnd(int cursor, String string, ArrayList<String> digitsList){
        int substringEnd = string.length();
        boolean digitFound = false;
        for(String element:digitsList){
            int tmp2 = string.indexOf(element,cursor);
            if((tmp2<substringEnd) && tmp2 != -1){
                substringEnd = tmp2;
                digitFound = true;


            }

            if(!digitFound){
                substringEnd = -1;
            }

        }
      return substringEnd;
    }
}



