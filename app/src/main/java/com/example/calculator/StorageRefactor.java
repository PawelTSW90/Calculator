package com.example.calculator;


import android.widget.EditText;

import java.util.ArrayList;
import java.util.Stack;

public class StorageRefactor {
    private String storage = "";
    private EditText txt;


    StorageRefactor(EditText txt) {
        this.txt = txt;

    }

    void addCharAtPosition(int position, String whichChar) {
        int selection = txt.getSelectionEnd();
        StringBuilder tmp2 = new StringBuilder(storage);
        tmp2 = tmp2.insert(position, whichChar);
        storage = tmp2.toString();
        txt.setText(getStorage());
        if (selection == 0) {
            txt.setSelection(1);
        } else
            txt.setSelection(selection + 1);

    }

    void removeCharAtPosition(int position) {
        int selection = txt.getSelectionEnd();
        StringBuilder tmp = new StringBuilder(storage);
        tmp = tmp.deleteCharAt(position);
        storage = tmp.toString();
        txt.setText(getStorage());
        txt.setSelection(selection - 1);
    }

    String getStorage() {
        return storage;
    }

    void setStorage(String string) {
        storage = string;
    }

    void clearStorage() {
        this.storage = "";
        txt.setText(getStorage());
        txt.setSelection(0);
    }

    ArrayList<String> refactorStorage() {
        Stack<String> stack = new Stack<>();
        ArrayList<String> exit = new ArrayList<>();
        String tmp = "";

        for (int i = 0; i < storage.length(); i++) {
            //If input is a digit, move it to tmp
            if (Utility.isParseInt(Character.toString(storage.charAt(i))) || Character.toString(storage.charAt(i)).equals(","))
                tmp += storage.charAt(i);
                //if input isn't a digit
            else if (Character.toString(storage.charAt(i)).equals("(")) {
                stack.add(Character.toString(storage.charAt(i)));

            } else if (Character.toString(storage.charAt(i)).equals(")")) {
                exit.add(tmp);
                tmp = "";

                while (!stack.peek().equals("(")) {
                    exit.add(stack.pop());

                }
                stack.pop();

            } else {
                if (!tmp.equals("")) {
                    exit.add(tmp);
                }

                if (stack.isEmpty()) {
                    stack.add(Character.toString(storage.charAt(i)));
                    //if stack isn't empty
                } else {
                    //declare last arithmetic operator on stack as last
                    String last = stack.peek();
                    if (Character.toString(storage.charAt(i)).equals("=")) {
                        continue;
                    }
                    //if our arithmetic operator has high priority,
                    if (!isLowPriority(Character.toString(storage.charAt(i)))) {
                        // and last operator has lower priority,
                        if (isLowPriority(last)) {
                            //add our operator on stack
                            stack.add(Character.toString(storage.charAt(i)));
                            //if last stack  operator has high priority as well,
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
                        if(stack.isEmpty() || !isLowPriority(stack.peek())){
                            stack.push(String.valueOf(storage.charAt(i)));

                        } else{
                            exit.add(stack.pop());
                        }

                    }
                }
                tmp = "";
            }
        }
        while (!stack.isEmpty()) {
            exit.add(stack.pop());

        }


        return exit;
    }

    //5*(8-7+9)/5 Check wrong result!!!!!!!


    public boolean isLowPriority(String input) {
        return input.equals("+") || input.equals("-");

    }
}
