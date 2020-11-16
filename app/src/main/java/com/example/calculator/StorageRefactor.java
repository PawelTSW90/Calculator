package com.example.calculator;


import android.widget.EditText;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class StorageRefactor {
    private String storage = "";
    private EditText txt;

    StorageRefactor(EditText txt) {
        this.txt = txt;


    }

    //method is adding value, to designated position
    void addCharAtPosition(int position, String whichChar) {
        int selection = txt.getSelectionEnd();
        StringBuilder tempStorage = new StringBuilder(storage);
        tempStorage = tempStorage.insert(position, whichChar);
        storage = tempStorage.toString();
        txt.setText(getStorage());
        if (selection == 0) {
            txt.setSelection(1);
        } else
            txt.setSelection(selection + 1);

    }



    String getStorage() {
        return storage;
    }

    void setStorage(String string) {
        storage = string;
    }

    //method is converting calculator input into Polish reverse notation
    ArrayList<String> refactorStorage() {


        Stack<String> stack = new Stack<>();
        ArrayList<String> exit = new ArrayList<>();
        StringBuilder awaitingNumbers = new StringBuilder();

        for (int i = 0; i < storage.length(); i++) {
            String currentChar = Character.toString(storage.charAt(i));
            //If input is a digit, move it to awaitingNumbers
            if
            (Utility.isParseInt(currentChar) || currentChar.equals(",")) {
                awaitingNumbers.append(storage.charAt(i));
            }
            //if input is open bracket, move it on stack
            else if (currentChar.equals("(")) {
                stack.add(currentChar);
                //if input is closed bracket,
            } else if (currentChar.equals(")")) {

                //if stack peek is "NEG", negate previous number and move it to exit
                if (stack.peek().equals("NEG")) {
                    exit.add("-" + awaitingNumbers.toString());
                    stack.pop();
                    awaitingNumbers = new StringBuilder();

                } else {
                    // move number from awaitingNumbers to exit...
                    exit.add(awaitingNumbers.toString());
                    awaitingNumbers = new StringBuilder();
                }
                // move everything from stack to exit, until you get open bracket...
                while (!stack.peek().equals("(")) {
                    exit.add(stack.pop());
                }
                // remove open bracket from stack
                stack.pop();
                // if input is arithmetic symbol
            } else {

                //if input is "-" and it's first character in storage, add "NEG" to stack
                if (currentChar.equals("-") && awaitingNumbers.length() == 0) {
                    stack.add("NEG");
                } else {


                    //if current character is arithmetic, and "NEG" is on top of the stack, negate previous number

                    try {
                        if (stack.peek().equals("NEG")) {
                            exit.add("-" + awaitingNumbers.toString());
                            stack.pop();
                            awaitingNumbers = new StringBuilder();

                        }
                    } catch (EmptyStackException ignored) {

                    }
                    //move all numbers from awaitingNumbers to exit
                    if (!awaitingNumbers.toString().isEmpty()) {
                        exit.add(awaitingNumbers.toString());
                    }
                    //if stack is empty, move current symbol to stack
                    if (stack.isEmpty()) {
                        stack.add(currentChar);
                        //if stack isn't empty
                    } else {
                        //declare last arithmetic operator on stack as last
                        String last = stack.peek();
                        if (currentChar.equals("=")) {
                            continue;
                        }
                        //if our arithmetic operator has high priority,
                        if (!Utility.isLowPriority(currentChar)) {
                            // and last operator has lower priority,
                            if (Utility.isLowPriority(last)) {
                                //add our operator on stack
                                stack.add(currentChar);
                                //if last stack  operator has high priority as well,
                            } else {
                                // add last arithmetic operator from stack to exit until: stack is empty/you will find high priority symbol on stack/open bracket
                                while (!stack.isEmpty() && !Utility.isLowPriority(stack.peek()) && !stack.peek().equals("(")) {
                                    exit.add(last);
                                    stack.pop();

                                }
                                //when last arithmetic operator on stack has low priority, add our arithmetic operator to stack
                                stack.push(currentChar);


                            }
                            // if our arithmetic operator has low priority.
                        } else {


                            // move operators from stack to exit until : stack is empty/you find open bracket
                            while (!stack.isEmpty() && !stack.peek().equals("(")) {
                                exit.add(stack.pop());
                            }
                            //then add current symbol to stack
                            stack.push(currentChar);


                        }
                    }
                    //reset awaitingNumbers after digit has already been moved to exit
                    awaitingNumbers = new StringBuilder();
                }
            }
        }
        // at the end, move everything from stack to exit until stack is empty, except "=" sign
        while (!stack.isEmpty() && !stack.peek().equals("=")) {
            exit.add(stack.pop());

        }

        return exit;
    }

}
