package com.example.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private Magazyn magazyn = new Magazyn();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView txt = findViewById(R.id.text_View);
        final Button button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(new TextFileInput(txt, magazyn));
        final Button button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(new TextFileInput(txt, magazyn));
        final Button button3 = findViewById(R.id.button_3);
        button3.setOnClickListener(new TextFileInput(txt, magazyn));
        final Button button_add = findViewById(R.id.button_add);
        button_add.setOnClickListener(new TextFileInput(txt, magazyn));
        final Button button_C = findViewById(R.id.button_C);
        button_C.setOnClickListener(new TextFileInput(txt, magazyn));
        final Button button_result = findViewById(R.id.button_result);
        button_result.setOnClickListener(new ResultClass(txt, magazyn));


    }


}







