package com.example.finalbmi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.finalbmi.ObjectBmi.*;

public class TableControllerBmi extends DatabaseHandler {

    public TableControllerBmi(Context context) {

        super(context);
    }

    public boolean create() {

        ContentValues values = new ContentValues();

        values.put("date", date);
        values.put("weight", weight);
        values.put("height", height);
        values.put("result", result);

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("bmis", null, values) > 0;
        db.close();

        return createSuccessful;
    }
    public int count() {

        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM bmis";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;

    }
    public List<ObjectBmi> read() {
        List<ObjectBmi> recordsList = new ArrayList<ObjectBmi>();
        String sql = "SELECT * FROM bmis ORDER BY id DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            int aydi = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
            String deyt = cursor.getString(cursor.getColumnIndex("date"));
            String weyt = cursor.getString(cursor.getColumnIndex("weight"));
            String hayt = cursor.getString(cursor.getColumnIndex("height"));
            String resulta = cursor.getString(cursor.getColumnIndex("result"));
            ObjectBmi objectBmi = new ObjectBmi();
            id = aydi;
            date = deyt;
            weight = weyt;
            height = hayt;
            result = resulta;
            cursor.moveToNext();
            recordsList.add(objectBmi);
        }
        cursor.close();
        db.close();
        return recordsList;
    }

}
