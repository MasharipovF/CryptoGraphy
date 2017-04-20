package com.example.farrukh.labs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button lab1, lab2, lab3, lab4, lab5, lab6, lab7, lab8, lab9;

        lab1 = (Button) findViewById(R.id.lab1);
        lab2 = (Button) findViewById(R.id.lab2);
        lab4 = (Button) findViewById(R.id.lab4);

        lab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), lab1.class);
                startActivity(intent);
            }
        });
        lab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), lab2.class);
                startActivity(intent);
            }
        });
        lab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), lab4.class);
                startActivity(intent);
            }
        });
    }
}
