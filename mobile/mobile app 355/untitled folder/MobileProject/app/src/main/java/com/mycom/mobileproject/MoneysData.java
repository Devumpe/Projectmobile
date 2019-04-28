package com.mycom.mobileproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.mycom.mobileproject.Constants.BALANCE;
import static com.mycom.mobileproject.Constants.SPENT;
import static com.mycom.mobileproject.Constants.TABLE_NAME;

public class MoneysData  extends SQLiteOpenHelper{

    public MoneysData(Context ctx) {

        super(ctx,"moneys.db" , null ,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +" (" + _ID
                +" INTEGER PRIMARY KEY AUTOINCREMENT, " + BALANCE + " INTEGER, " +  SPENT +" INTEGER);" );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS moneys");
            onCreate(db);
    }


}
