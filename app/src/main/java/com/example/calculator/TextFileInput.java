package com.example.calculator;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TextFileInput implements View.OnClickListener {
    TextView txt;
    Magazyn magazyn;

    TextFileInput(TextView text, Magazyn magazyn) {
        this.txt = text;
        this.magazyn = magazyn;


    }


    @Override
    public void onClick(View v) {
        if(magazyn.magazyn == "0"){                                  // jeśli magazyn to 0, pierwszy naciśnięty znak zastępuje to 0
            magazyn.StringOdNowa(((Button)v).getText().toString());
            this.txt.setText(magazyn.zwrocStringa());
        } else
        magazyn.dodajZnakDoStringa(((Button) v).getText().toString()); // jeśli magazyn nie jest 0, dodajemy kolejny znak do już istniejącego
        this.txt.setText(magazyn.zwrocStringa());


if(v.getResources().getResourceName(v.getId()).contains("C")){       //jeśli naciskamy C, magazyn zamienia się w 0 i zostaje wyświetlony
    magazyn.magazyn = "0";
    this.txt.setText(magazyn.zwrocStringa());
}



    }


}
