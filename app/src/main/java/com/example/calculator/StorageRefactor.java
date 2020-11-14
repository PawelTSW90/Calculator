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


    /*void changeStorage(){
StringBuilder tmpStorage = new StringBuilder().append(storage);
        String storage = getStorage();
        Pattern myPattern = Pattern.compile("-\\d+");
        Matcher match = myPattern.matcher(storage);
        List<ArrayList<Integer>> storageList = new ArrayList<>();

        while (match.find()){
            Log.d("check",""+ match.start()+"-"+ match.end());
            ArrayList<Integer> tmpList = new ArrayList<>();
            tmpList.add(match.start());
            tmpList.add(match.end());
            storageList.add(tmpList);
            Log.d("check", "" + storageList);
        }
        StringBuilder storageTmp = new StringBuilder(getStorage());
        storageTmp.insert(5, ")");
        storageTmp.insert(3, "(0");
        storageTmp.insert(3, ")");
        storageTmp.insert(1, "(0");
        Log.d("check", "" + storageTmp);
        setStorage(storageTmp.toString());




    }




     */
    ArrayList<String> refactorStorage() {



        Stack<String> stack = new Stack<>();
        ArrayList<String> exit = new ArrayList<>();
        StringBuilder tmp = new StringBuilder();

        for (int i = 0; i < storage.length(); i++) {
            //If input is a digit, move it to tmp
            if
            (Utility.isParseInt(Character.toString(storage.charAt(i))) || Character.toString(storage.charAt(i)).equals(",")) {
                tmp.append(storage.charAt(i));
            }
            //if input is open bracket, move it on stack
            else if (Character.toString(storage.charAt(i)).equals("(")) {
                stack.add(Character.toString(storage.charAt(i)));
            //if input is closed bracket, move digit from tmp to exit...
            } else if (Character.toString(storage.charAt(i)).equals(")")) {
                exit.add(tmp.toString());
                tmp = new StringBuilder();
            // move everything from stack to exit, until you get open bracket...
                while (!stack.peek().equals("(")) {
                    exit.add(stack.pop());
                }
                // remove open bracket from stack
                stack.pop();
            // if input is arithmetic symbol
            } else {
                // move digit from tmp to exit
                if (!tmp.toString().equals("")) {
                    exit.add(tmp.toString());
                }
                //if stack is empty, move current symbol to stack
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
                            // add last arithmetic operator from stack to exit until: stack is empty/you will find high priority symbol on stack/open bracket
                            while (!stack.isEmpty() && !isLowPriority(stack.peek())&& !stack.peek().equals("(")) {
                                exit.add(last);
                                stack.pop();

                            }
                            //when last arithmetic operator on stack has low priority, add our arithmetic operator to stack
                            stack.push(Character.toString(storage.charAt(i)));


                        }
                        // if our arithmetic operator has low priority.
                    } else {
                        // move operators from stack to exit until : stack is empty/you find low priority symbol on stack/open bracket
                        while (!stack.isEmpty() && !isLowPriority(stack.peek()) && !stack.peek().equals("(")) {
                            exit.add(stack.pop());
                        }
                        //then add current symbol to stack
                        stack.push(String.valueOf(storage.charAt(i)));


                    }
                }
                //reset tmp after digit has already been moved to exit
                tmp = new StringBuilder();
            }
        }
        // at the end, move everything from stack to exit until stack is empty, except "=" sign
        while (!stack.isEmpty() && !stack.peek().equals("=")) {
            exit.add(stack.pop());

        }

        //

        return exit;
    }




    private boolean isLowPriority(String input) {
        return input.equals("+") || input.equals("-");

    }
}
