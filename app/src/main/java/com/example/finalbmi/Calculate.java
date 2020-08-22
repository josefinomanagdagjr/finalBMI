package com.example.finalbmi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Calculate extends Activity implements View.OnClickListener{
    EditText weight;
    EditText height;
    TextView result;
    TextView dateTimeDisplay;
    Button calculate;
    Button clear;
    Button save;
    Button back;
    Calendar calendar;
    SimpleDateFormat dateFormat;
    String date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);


        weight = findViewById(R.id.etweight);
        height = findViewById(R.id.etheight);
        result = findViewById(R.id.result);
        calendar = Calendar.getInstance();
        dateTimeDisplay = findViewById(R.id.date);
        calculate = findViewById(R.id.calculate);
        clear = findViewById(R.id.clear);
        back = findViewById(R.id.back);
        save = findViewById(R.id.save);
        clear.setOnClickListener(this);
        back.setOnClickListener(this);
        save.setOnClickListener(this);

        findViewById(R.id.calculate).setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                String weyt = weight.getText().toString();
                String hayt = height.getText().toString();


                if(TextUtils.isEmpty(weyt)){
                    weight.setError("Please enter your weight");
                    weight.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(hayt)){
                    height.setError("Please enter your height");
                    height.requestFocus();
                    return;
                }

                float weight = Float.parseFloat(weyt);
                float height = Float.parseFloat(hayt)/100;
                float bmiValue = calculateBMI(weight, height);
                String textresult = interpretBMI(bmiValue);
                result.setText("BMI: " + bmiValue + " - " + textresult);

                dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                date = dateFormat.format(calendar.getTime());
                dateTimeDisplay.setText("Date: " + date);

            }
        });countRecords();


    }
    private float calculateBMI (float weight, float height) {
        return (weight / (height * height));
    }
    private String interpretBMI(float bmiValue) {
        if (bmiValue < 16) {
            return "Severely underweight";
        } else if (bmiValue < 18.5) {
            return "Underweight";
        } else if (bmiValue < 25) {
            return "Normal";
        } else if (bmiValue < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clear:
                weight.setText(" ");
                height.setText(" ");
                result.setText(" ");
                dateTimeDisplay.setText(" ");
                break;

            case R.id.back:
                Intent backpage = new Intent(this, MainActivity.class);
                startActivity(backpage);
                break;

            case R.id.save:

                Context context = view.getRootView().getContext();
                String deyt = dateTimeDisplay.getText().toString();
                String weyt = weight.getText().toString();
                String hayt = height.getText().toString();
                String resulta = result.getText().toString();

                ObjectBmi objectBmi = new ObjectBmi();
                ObjectBmi.date = deyt;
                ObjectBmi.weight = weyt;
                ObjectBmi.height = hayt;
                ObjectBmi.result = resulta;

                boolean createSuccessful = new TableControllerBmi(context).create();

                if (createSuccessful) {
                    Toast.makeText(context, "BMI information was saved.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Unable to save BMI information.", Toast.LENGTH_SHORT).show();
                }

                dateTimeDisplay.setText(" ");
                weight.setText(" ");
                height.setText(" ");
                result.setText(" ");

                countRecords();
                break;
        }
    }

        public void countRecords() {
            int recordCount = new TableControllerBmi(this).count();

            TextView textViewRecordCount = findViewById(R.id.textViewRecordCount);
            textViewRecordCount.setText(recordCount + " records found.");
        }
}