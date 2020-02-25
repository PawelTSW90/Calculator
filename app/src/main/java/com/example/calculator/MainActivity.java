package com.example.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private Storage storage = new Storage();
    private Calculating calculating = new Calculating();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView txt = findViewById(R.id.text_View);
        final Button button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(new TextFileInput(txt, storage));
        final Button button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(new TextFileInput(txt, storage));
        final Button button3 = findViewById(R.id.button_3);
        button3.setOnClickListener(new TextFileInput(txt, storage));
        final Button button_add = findViewById(R.id.button_add);
        button_add.setOnClickListener(new TextFileInput(txt, storage));
        final Button button_multiply = findViewById(R.id.button_multiply);
        button_multiply.setOnClickListener(new TextFileInput(txt, storage));
        final Button button_C = findViewById(R.id.button_C);
        button_C.setOnClickListener(new TextFileInput(txt, storage));
        final Button button_result = findViewById(R.id.button_result);
        button_result.setOnClickListener(new ResultClass(txt, storage, calculating));
        //hello



    }


}







