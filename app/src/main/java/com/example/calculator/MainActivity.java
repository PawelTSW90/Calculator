package com.example.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText txt = findViewById(R.id.text_Edit);
        Calculating calculating = new Calculating();
        StorageRefactor storage = new StorageRefactor(txt);

        //disable system keyboard
        txt.setShowSoftInputOnFocus(false);

        final Button button0 = findViewById(R.id.button_0);
        button0.setOnClickListener(new DigitsInput(txt, storage, getApplicationContext()));

        final Button button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(new DigitsInput(txt, storage, getApplicationContext()));

        final Button button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(new DigitsInput(txt, storage,getApplicationContext()));

        final Button button3 = findViewById(R.id.button_3);
        button3.setOnClickListener(new DigitsInput(txt, storage,getApplicationContext()));

        final Button button4 = findViewById(R.id.button_4);
        button4.setOnClickListener(new DigitsInput(txt, storage,getApplicationContext()));

        final Button button5 = findViewById(R.id.button_5);
        button5.setOnClickListener(new DigitsInput(txt, storage,getApplicationContext()));

        final Button button6 = findViewById(R.id.button_6);
        button6.setOnClickListener(new DigitsInput(txt, storage,getApplicationContext()));

        final Button button7 = findViewById(R.id.button_7);
        button7.setOnClickListener(new DigitsInput(txt, storage,getApplicationContext()));

        final Button button8 = findViewById(R.id.button_8);
        button8.setOnClickListener(new DigitsInput(txt, storage,getApplicationContext()));

        final Button button9 = findViewById(R.id.button_9);
        button9.setOnClickListener(new DigitsInput(txt, storage,getApplicationContext()));

        final Button brackets = findViewById(R.id.button_brackets);
        brackets.setOnClickListener(new BracketsInput());

        final Button button_add = findViewById(R.id.button_add);
        button_add.setOnClickListener(new ArithmeticInput(txt, storage));

        final Button button_multiply = findViewById(R.id.button_multiply);
        button_multiply.setOnClickListener(new ArithmeticInput(txt, storage));

        final Button button_divide = findViewById(R.id.button_divide);
        button_divide.setOnClickListener(new ArithmeticInput(txt, storage));

        final Button button_C = findViewById(R.id.button_C);
        button_C.setOnClickListener(new DeleteInput(txt, storage));

        final Button button_result = findViewById(R.id.button_result);
        button_result.setOnClickListener(new ResultInput(txt, storage, calculating, this.getApplicationContext()));

        final Button button_minus = findViewById(R.id.button_minus);
        button_minus.setOnClickListener(new ArithmeticInput(txt, storage));

        final Button button_delete = findViewById(R.id.button_delete);
        button_delete.setOnClickListener(new DeleteInput(txt, storage));

        final Button comma_button = findViewById(R.id.button_comma);
        comma_button.setOnClickListener(new CommaInput(txt, storage));

    }




}







