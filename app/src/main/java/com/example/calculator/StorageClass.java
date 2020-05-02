package com.example.calculator;


import android.util.Log;

import java.util.ArrayList;
import java.util.Stack;

public class StorageClass {
    String storage = "";


    void addCharToString(String znak) {
        if (znak.contains("⌫")) {
            return;
        }
        if (storage.length() == 1 && storage.startsWith("0")) {
            this.storage = znak;
        } else

            this.storage += znak;

    }


    String returnString() {
        return storage;

    }

    ArrayList<String> returnWyjscie() {
        ArrayList<String> listaWartosci = new ArrayList<>();
        Stack<String> stos = new Stack<>();
        ArrayList<String> wyjscie = new ArrayList<>();
        String tmp = "";

        for (int i = 0; i < storage.length(); i++) {

            if (isInteger(Character.toString(storage.charAt(i)))|| Character.toString(storage.charAt(i)).equals(",")) //Jeśli znak jest cyfrą, wrzuć do tmp
                tmp += storage.charAt(i);
                //else kiedy znak nie jest cyfrą
            else {
                listaWartosci.add(tmp); //dodanie pełnej liczby do listyWartosci

                wyjscie.add(tmp); //dodanie pełnej liczby na wyjście
                Log.i("tmp", "Wyjscie:" + wyjscie);

                listaWartosci.add(Character.toString(storage.charAt(i))); //dodanie operatora do listyWartosci
                Log.i("tmp", "ListaWartosci:" + listaWartosci);

                if (stos.isEmpty()) { //jeśli na stosie nie ma jeszcze żadnych operatorów
                    stos.add(Character.toString(storage.charAt(i))); //dodajemy nasz operator na stos jako pierwszy
                    Log.i("tmp", "Stos:" + stos);

                } else {                         //kiedy na stosie są już operatory
                    String last = stos.peek();//przypisanie do "last" ostatniego operatora na stosie
                    if (Character.toString(storage.charAt(i)).equals("=") || Character.toString(storage.charAt(i)).equals(",")) {
                        continue;
                    }

                    if (!isLowPriority(Character.toString(storage.charAt(i)))) {//jeśli nasz operator ma wysoki priorytet

                        if (isLowPriority(last)) { // a ostatni operator na stosie ma niższy priorytet

                            stos.add(Character.toString(storage.charAt(i))); //to dodajemy nasz operator na stos
                            Log.i("tmp", "Stos:" + stos);

                        } else { //jeśli ostatni operator na stosie ma również wysoki priorytet

                            while (!isLowPriority(last)) {//dopóki na stosie jest operator z wysokim priorytetem
                                wyjscie.add(last);        //odkładamy go na wyjście
                                Log.i("tmp", "Wyjscie:" + wyjscie);

                            }
                            stos.push(Character.toString(storage.charAt(i))); //gdy operator na stosie ma niższy priorytet, odkłądamy
                            Log.i("tmp", "Stos:" + stos);
                            //nasz operator na wierzch stosu
                        }

                    } else { //jeśli nasz operator ma niski priorytet
                        while (!stos.isEmpty()) {
                            wyjscie.add(stos.pop());
                            Log.i("tmp", "Wyjscie:" + wyjscie);
                        }
                        stos.add(Character.toString(storage.charAt(i)));

                    }
                }
                tmp = "";
            }
        }
        while (!stos.isEmpty()) {
            wyjscie.add(stos.pop());
            Log.i("tmp", "Wyjscie:" + wyjscie);

        }

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
