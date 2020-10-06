package com.example.justjava3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class CoffeeDBHandler extends SQLiteOpenHelper {

    //Attributes
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "coffeebase.db";
    public static final String TABLE_COFFEE = "coffeesales";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CUSTOMERNAME = "customername";
    public static final String COLUMN_SALESAMOUNT = "salesamount";

    public CoffeeDBHandler(@Nullable Context context, @Nullable String name,
                           @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+ TABLE_COFFEE +"("+
                COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_CUSTOMERNAME +" TEXT , "+
                COLUMN_SALESAMOUNT+ " INTEGER "+
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_COFFEE);
        onCreate(db);
    }

    public void addOrder(Order order){
        ContentValues values = new ContentValues();
        values.put(COLUMN_CUSTOMERNAME, order.get_custName());
        values.put(COLUMN_SALESAMOUNT, order.get_saleAmount());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_COFFEE, null, values);
        db.close();
    }

    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_COFFEE + " WHERE 1";
        //Create A Cursor Object To Point To The DB
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        //Loop To Read And Store The Information In A String
        while (!c.isAfterLast()){
            if(c.getString(c.getColumnIndex(COLUMN_CUSTOMERNAME))!= null){
                dbString += c.getString(c.getColumnIndex(COLUMN_CUSTOMERNAME))+ " --> $"+
                            c.getString(c.getColumnIndex(COLUMN_SALESAMOUNT))+
                            "\n";
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }

}
