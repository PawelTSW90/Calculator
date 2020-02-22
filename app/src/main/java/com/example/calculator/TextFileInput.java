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
        if(magazyn.magazyn == "0"){
            magazyn.StringOdNowa(((Button)v).getText().toString());
            this.txt.setText(magazyn.zwrocStringa());
        } else
        magazyn.dodajZnakDoStringa(((Button) v).getText().toString());
        this.txt.setText(magazyn.zwrocStringa());


if(v.getResources().getResourceName(v.getId()).contains("C")){
    magazyn.magazyn = "0";
    this.txt.setText(magazyn.zwrocStringa());
}



    }


}
