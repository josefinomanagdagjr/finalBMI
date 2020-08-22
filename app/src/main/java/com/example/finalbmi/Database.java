package com.example.finalbmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;
import java.util.List;

public class Database extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        readRecords();
    }
    public void readRecords() {
        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();
        List<ObjectBmi> bmis = new TableControllerBmi(this).read();
        if (bmis.size() > 0) {
            for (ObjectBmi obj : bmis) {
                int id = obj.id;
                String date = obj.date;
                String weight = obj.weight;
                String height = obj.height;
                String result = obj.result;
                String textViewContents = date + " - " + weight + "kg" + " - " + height + "cm" +" - " + result;
                TextView textView= new TextView(this);
                textView.setPadding(0, 10, 0, 10);
                textView.setText(textViewContents);
                textView.setTag(Integer.toString(id));
                linearLayoutRecords.addView(textView);
            }
        }
        else {
            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("No records yet.");
            linearLayoutRecords.addView(locationItem);
        }

    }
}