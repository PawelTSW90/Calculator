package com.example.calculator;


import android.util.Log;

import java.util.ArrayList;
import java.util.Stack;

public class Storage {
    String magazyn = "";

    void addCharToString(String znak) {

        this.magazyn += znak;


    }

    void stringZero(String znak) {
        this.magazyn = znak;


    }


    String returnString() {
        return magazyn;


    }





    ArrayList<String> returnWyjscie() {
        String tmp = "";
        ArrayList<String> listaWartosci = new ArrayList<>();
        Stack<String> stos = new Stack<>();
        ArrayList<String> wyjscie = new ArrayList<>();


        for (int i = 0; i < magazyn.length(); i++) {

            if (isInteger(Character.toString(magazyn.charAt(i)))) //Jeśli znak jest cyfrą, wrzuć do tmp
                tmp += magazyn.charAt(i);
                //else kiedy znak nie jest cyfrą
            else {
                listaWartosci.add(tmp); //dodanie pełnej liczby do listyWartosci

                wyjscie.add(tmp); //dodanie pełnej liczby na wyjście

                listaWartosci.add(Character.toString(magazyn.charAt(i))); //dodanie operatora do listyWartosci

                if (stos.isEmpty()) { //jeśli na stosie nie ma jeszcze żadnych operatorów
                    stos.add(Character.toString(magazyn.charAt(i))); //dodajemy nasz operator na stos jako pierwszy

                } else {                         //kiedy na stosie są już operatory
                    String last = stos.peek();//przypisanie do "last" ostatniego operatora na stosie
                    if(Character.toString(magazyn.charAt(i)).equals("=")){
                        continue;
                    }

                    if (!isLowPriority(Character.toString(magazyn.charAt(i)))) {//jeśli nasz operator ma wysoki priorytet

                        if (isLowPriority(last)) { // a ostatni operator na stosie ma niższy priorytet

                            stos.add(Character.toString(magazyn.charAt(i))); //to dodajemy nasz operator na stos

                        } else { //jeśli ostatni operator na stosie ma również wysoki priorytet

                            while (!isLowPriority(last)) {//dopóki na stosie jest operator z wysokim priorytetem
                                wyjscie.add(last);        //odkładamy go na wyjście

                            }
                            stos.push(Character.toString(magazyn.charAt(i))); //gdy operator na stosie ma niższy priorytet, odkłądamy
                            //nasz operator na wierzch stosu
                        }

                    } else { //jeśli nasz operator ma niski priorytet
                        while (!stos.isEmpty()) {
                            wyjscie.add(stos.pop());
                        }
                        stos.add(Character.toString(magazyn.charAt(i)));

                    }


                }


                tmp = "";


            }


        }



        while (!stos.isEmpty()){
            wyjscie.add(stos.pop());

        }






        Log.i("paweł", "wyjscie=" + wyjscie.toString());
        Log.i("paweł", "stos= " + stos.toString());

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
