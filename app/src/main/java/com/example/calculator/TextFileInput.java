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
        magazyn.dodajZnakDoStringa(((Button) v).getText().toString());
        this.txt.setText(magazyn.zwrocStringa());
        //Log.i("Pawel","id = " + v.getResources().getResourceName(v.getId()));


        if(v.getResources().getResourceName(v.getId()).contains("button_result")){
            int cos = magazyn.zwrocWynik();
            this.txt.setText(Integer.toString(cos));
        }



    }


}
