package com.example.calculator;


import java.util.ArrayList;
import java.util.Stack;

public class StorageClass {
    String storage = "";


    void addCharToString(String input) {
        if (input.contains("⌫")) {
            return;
        }
        if (storage.length() == 1 && storage.startsWith("0") && !input.equals(",") && isInteger(input)) {
            this.storage = input;
        } else
            this.storage += input;

    }

    String returnString() {
        return storage;
    }

    void removeLastChar(){
        storage=storage.substring(0, storage.length()-1);

    }

    ArrayList<String> returnWyjscie() {
        ArrayList<String> valueList = new ArrayList<>();
        Stack<String> stos = new Stack<>();
        ArrayList<String> wyjscie = new ArrayList<>();
        String tmp = "";

        for (int i = 0; i < storage.length(); i++) {

            if (isInteger(Character.toString(storage.charAt(i)))|| Character.toString(storage.charAt(i)).equals(",")) //Jeśli input jest cyfrą, wrzuć do tmp
                tmp += storage.charAt(i);
                //else kiedy input nie jest cyfrą
            else {
                valueList.add(tmp); //dodanie pełnej liczby do listyWartosci

                wyjscie.add(tmp); //dodanie pełnej liczby na wyjście

                valueList.add(Character.toString(storage.charAt(i))); //dodanie operatora do listyWartosci

                if (stos.isEmpty()) { //jeśli na stosie nie ma jeszcze żadnych operatorów
                    stos.add(Character.toString(storage.charAt(i))); //dodajemy nasz operator na stos jako pierwszy

                } else {                         //kiedy na stosie są już operatory
                    String last = stos.peek();//przypisanie do "last" ostatniego operatora na stosie
                    if (Character.toString(storage.charAt(i)).equals("=") || Character.toString(storage.charAt(i)).equals(",")) {
                        continue;
                    }

                    if (!isLowPriority(Character.toString(storage.charAt(i)))) {//jeśli nasz operator ma wysoki priorytet

                        if (isLowPriority(last)) { // a ostatni operator na stosie ma niższy priorytet

                            stos.add(Character.toString(storage.charAt(i))); //to dodajemy nasz operator na stos

                        } else { //jeśli ostatni operator na stosie ma również wysoki priorytet

                            while (!isLowPriority(last)) {//dopóki na stosie jest operator z wysokim priorytetem
                                wyjscie.add(last);        //odkładamy go na wyjście

                            }
                            stos.push(Character.toString(storage.charAt(i))); //gdy operator na stosie ma niższy priorytet, odkłądamy

                            //nasz operator na wierzch stosu
                        }

                    } else { //jeśli nasz operator ma niski priorytet
                        while (!stos.isEmpty()) {
                            wyjscie.add(stos.pop());
                        }
                        stos.add(Character.toString(storage.charAt(i)));
                    }
                }
                tmp = "";
            }
        }
        while (!stos.isEmpty()) {
            wyjscie.add(stos.pop());

        }
        valueList.remove(valueList.size()-1);

        return wyjscie;
    }

    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLowPriority(String input) {
        if (input.equals("+") || input.equals("-")) {
            return true;
        } else
            return false;
    }
}
