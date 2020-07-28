package com.example.calculator;


import android.widget.EditText;

import java.util.ArrayList;
import java.util.Stack;

public class StorageRefactor {
    private String storage = "";
    private EditText txt;


    StorageRefactor(EditText txt){
        this.txt = txt;

    }

    void addCharAtPosition(int position, String whichChar) {
        int selection = txt.getSelectionEnd();
        StringBuilder tmp2 = new StringBuilder(storage);
        tmp2 = tmp2.insert(position, whichChar);
        storage = tmp2.toString();
        txt.setText(getStorage());
        if(selection == 0){
            txt.setSelection(1);
        } else
        txt.setSelection(selection+1);

    }

    void removeCharAtPosition(int position){
        int selection = txt.getSelectionEnd();
        StringBuilder tmp = new StringBuilder(storage);
        tmp = tmp.deleteCharAt(position);
        storage = tmp.toString();
        txt.setText(getStorage());
        txt.setSelection(selection-1);
    }

    String getStorage(){
        return storage;
    }

    void setStorage(String string){
        storage = string;
    }

    void clearStorage(){
        this.storage = "";
        txt.setText(getStorage());
        txt.setSelection(0);
    }

    ArrayList<String> refactorStorage() {
        ArrayList<String> valueList = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        ArrayList<String> exit = new ArrayList<>();
        String tmp = "";

        for (int i = 0; i < storage.length(); i++) {
            //If input is a digit, move it to tmp
            if (Utility.isParseInt(Character.toString(storage.charAt(i))) || Character.toString(storage.charAt(i)).equals(","))
                tmp += storage.charAt(i);
                //if input isn't a digit
            else {
                //add full number to valueList
                valueList.add(tmp);
                //add full number to exit
                exit.add(tmp);
                //add arithmetic operator to valueList
                valueList.add(Character.toString(storage.charAt(i)));
                //If stack is empty, add arithmetic operator as a first
                if (stack.isEmpty()) {
                    stack.add(Character.toString(storage.charAt(i)));
                    //if stack isn't empty
                } else {
                    //declare last arithmetic operator on stack as last
                    String last = stack.peek();
                    if (Character.toString(storage.charAt(i)).equals("=") || Character.toString(storage.charAt(i)).equals(",")) {
                        continue;
                    }
                    //if our arithmetic operator has high priority,
                    if (!isLowPriority(Character.toString(storage.charAt(i)))) {
                    // and last operator has lower priority,
                        if (isLowPriority(last)) {
                            //add our operator on stack
                            stack.add(Character.toString(storage.charAt(i)));
                            //if stack last arithmetic operator has high priority as well,
                        } else {
                            // adding last arithmetic operator to exit, as long as last arithmetic operator on stack has high priority
                            while (isLowPriority(stack.peek())) {
                                exit.add(last);
                                stack.pop();

                            }
                            //when last arithmetic operator on stack has low priority, we adding our arithmetic operator to stack
                            stack.push(Character.toString(storage.charAt(i)));


                        }

                    } else {
                        while (!stack.isEmpty()) {
                            exit.add(stack.pop());
                        }
                        stack.add(Character.toString(storage.charAt(i)));
                    }
                }
                tmp = "";
            }
        }
        while (!stack.isEmpty()) {
            exit.add(stack.pop());

        }
        valueList.remove(valueList.size() - 1);

        return exit;
    }


    public boolean isLowPriority(String input) {
        return input.equals("+") || input.equals("-");

    }
}
