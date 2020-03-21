package com.example.calculator;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private StorageClass storage = new StorageClass();
    private Calculating calculating = new Calculating();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText txt = findViewById(R.id.text_Edit);
        txt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        final Button button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(new TextFileInput(txt, storage));
        final Button button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(new TextFileInput(txt, storage));
        final Button button3 = findViewById(R.id.button_3);
        button3.setOnClickListener(new TextFileInput(txt, storage));
        final Button button4 = findViewById(R.id.button_4);
        button4.setOnClickListener(new TextFileInput(txt, storage));
        final Button button5 = findViewById(R.id.button_5);
        button5.setOnClickListener(new TextFileInput(txt, storage));
        final Button button6 = findViewById(R.id.button_6);
        button6.setOnClickListener(new TextFileInput(txt, storage));
        final Button button7 = findViewById(R.id.button_7);
        button7.setOnClickListener(new TextFileInput(txt, storage));
        final Button button8 = findViewById(R.id.button_8);
        button8.setOnClickListener(new TextFileInput(txt, storage));
        final Button button9 = findViewById(R.id.button_9);
        button9.setOnClickListener(new TextFileInput(txt, storage));
        final Button brackets = findViewById(R.id.button_brackets);
        brackets.setOnClickListener(new TextFileInput(txt, storage));
        final Button button_add = findViewById(R.id.button_add);
        button_add.setOnClickListener(new TextFileInput(txt, storage));
        final Button button_multiply = findViewById(R.id.button_multiply);
        button_multiply.setOnClickListener(new TextFileInput(txt, storage));
        final Button button_C = findViewById(R.id.button_C);
        button_C.setOnClickListener(new TextFileInput(txt, storage));
        final Button button_result = findViewById(R.id.button_result);
        button_result.setOnClickListener(new ResultClass(txt, storage, calculating));
        final Button button_minus = findViewById(R.id.button_minus);
        button_minus.setOnClickListener(new TextFileInput(txt, storage));

        //test comment from github


    }


}







