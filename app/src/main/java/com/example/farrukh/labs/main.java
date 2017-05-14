package com.example.farrukh.labs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class main extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button lab1, lab2, difhel, lab4,  rc4, a5_1;

        lab1 = (Button) findViewById(R.id.lab1);
        lab2 = (Button) findViewById(R.id.lab2);
        lab4 = (Button) findViewById(R.id.lab4);
        rc4 = (Button) findViewById(R.id.lab3);
        a5_1 = (Button) findViewById(R.id.lab5);
        difhel = (Button) findViewById(R.id.lab6);

        lab1.setOnClickListener(this);
        lab2.setOnClickListener(this);
        lab4.setOnClickListener(this);
        rc4.setOnClickListener(this);
        a5_1.setOnClickListener(this);
        difhel.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.lab1:
                intent = new Intent(getApplicationContext(), lab1.class);
                break;
            case R.id.lab2:
                intent = new Intent(getApplicationContext(), lab2.class);
                break;
            case R.id.lab3:
                intent = new Intent(getApplicationContext(), RC4.class);
                break;
            case R.id.lab4:
                intent = new Intent(getApplicationContext(), lab4.class);
                break;
            case R.id.lab5:
                intent = new Intent(getApplicationContext(), A5_1.class);
                break;
            case R.id.lab6:
                intent = new Intent(getApplicationContext(), DiffieHellman.class);
                break;
            default:
                intent = new Intent(getApplicationContext(), lab1.class);
                break;
        }
        startActivity(intent);

    }
}
