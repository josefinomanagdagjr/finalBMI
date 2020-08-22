package com.example.finalbmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button calculate;
    Button records;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }
    public void initComponents(){

        calculate = findViewById(R.id.calculate);
        records = findViewById(R.id.records);
        calculate.setOnClickListener(this);
        records.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.calculate:
                String packageContext;
                Intent calculate = new Intent( this, Calculate.class);
                startActivity(calculate);
                break;
            case R.id.records:
                Intent records = new Intent(this, Database.class);
                startActivity(records);
                break;
        }
    }
}