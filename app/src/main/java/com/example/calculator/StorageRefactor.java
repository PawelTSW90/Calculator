package com.example.calculator;


import java.util.ArrayList;
import java.util.Stack;

public class StorageRefactor {
    private String storage = "";

    void addCharToString(String input) {
        //if digit is an input, and previous character is 0, replace it with new input
        if (storage.length() == 1 && storage.startsWith("0") && !input.equals(",") && Utility.isInteger(input)) {
            this.storage = input;
        } else
            this.storage += input;

    }

    String returnString() {
        return storage;
    }


    void removeCharAtPosition(int position) {
        StringBuilder tmp = new StringBuilder(storage);
        tmp = tmp.deleteCharAt(position);
        storage = tmp.toString();

    }

    String getStorage(){
        return storage;
    }

    void setStorage(String string){
        storage = string;
    }

    void addCharAtPosition(int position, String whichChar) {
        StringBuilder tmp2 = new StringBuilder(storage);
        tmp2 = tmp2.insert(position, whichChar);
        storage = tmp2.toString();

    }

    void addStringToTheEnd(String string){
        this.storage+=string;

    }

    void clearStorage(){
        this.storage = "";
    }

    void removeCharAt(int position){
        StringBuilder tmp = new StringBuilder(storage);
        tmp = tmp.deleteCharAt(position);
        storage = tmp.toString();
    }

    ArrayList<String> refactorStorage() {
        ArrayList<String> valueList = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        ArrayList<String> exit = new ArrayList<>();
        String tmp = "";

        for (int i = 0; i < storage.length(); i++) {
            //If input is a digit, move it to tmp
            if (Utility.isInteger(Character.toString(storage.charAt(i))) || Character.toString(storage.charAt(i)).equals(","))
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
                    //if our arithmetic operator has high priority, and last arithmetic operator on stack has lower priority
                    if (!isLowPriority(Character.toString(storage.charAt(i)))) {

                        if (isLowPriority(last)) {
                            //add arithmetic operator to stack
                            stack.add(Character.toString(storage.charAt(i)));
                            //if stack last arithmetic operator has high priority aswell,
                        } else {
                            // adding last arithmetic operator to exit, as long as last arithmetic operator on stack has high priority
                            while (!isLowPriority(last)) {
                                exit.add(last);

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
        if (input.equals("+") || input.equals("-")) {
            return true;
        } else
            return false;
    }
}
