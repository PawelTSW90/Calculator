package com.example.calculator;


import android.util.Log;

import java.util.ArrayList;
import java.util.Stack;

public class Magazyn {
    String magazyn = "";
    int priorytet;

    void dodajZnakDoStringa(String znak) {

        this.magazyn += znak;


    }

    void StringOdNowa(String znak) {
        this.magazyn = znak;


    }


    String zwrocStringa() {
        return magazyn;


    }





    String zwrocWynik() {
        String tmp = "";
        ArrayList<String> listaWartosci = new ArrayList<>();
        Stack<String> stos = new Stack<>();
        ArrayList<String> wyjscie = new ArrayList<>();

        // 19+4+6
        for (int i = 0; i < magazyn.length(); i++) {

            if (isInteger(Character.toString(magazyn.charAt(i)))) //Jeśli znak jest cyfrą, wrzuć do tmp
                tmp += magazyn.charAt(i);
                //else kiedy znak nie jest cyfrą
            else {
                listaWartosci.add(tmp); //dodanie pełnej liczby do listyWartosci

                wyjscie.add(tmp); //dodanie pełnej liczby na wyjście

                listaWartosci.add(Character.toString(magazyn.charAt(i))); //dodanie operatora do listyWartosci

                if(stos.isEmpty()){ //jeśli na stosie nie ma jeszcze żadnych operatorów
                    stos.add(Character.toString(magazyn.charAt(i))); //dodajemy nasz operator na stos jako pierwszy
                    //kiedy na stosie są już operatory
                } else{
                    String last = stos.peek();//przypisanie do "last" ostatniego operatora na stosie

                    if (!isLowPriority(Character.toString(magazyn.charAt(i)))){//jeśli nasz operator ma wysoki priorytet

                        if(isLowPriority(last)){ // a ostatni operator na stosie ma niższy priorytet

                            stos.add(Character.toString(magazyn.charAt(i))); //to dodajemy nasz operator na stos

                        } else{ //jeśli ostatni operator na stosie ma również wysoki priorytet

                            while (!stos.isEmpty()){            //odkładamy operatory o wysokim indeksie na wyjście
                                if(isLowPriority(stos.peek())){//dopóki nie natrafimy na operator o niskim indeksie
                                    stos.push(Character.toString(magazyn.charAt(i)));
                                    break;
                                }
                                wyjscie.add(stos.pop());
                            }

                        }

                    }






                }
                Log.i("paweł", wyjscie.toString());
                tmp = "";

            }


        }


        /// obliczanie
        // [23, +, 12, =]


        return "";

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
        if(input.equals("+")|| input.equals("-")){
            return true;
        } else
            return false;



    }



}
