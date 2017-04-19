package com.greenarkresorts.greenark.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.greenarkresorts.greenark.models.db.BookingDetailContract;

public class CottagesDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "cottages";

    public CottagesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BookingDetailContract.CREATE_TABLE_BOOKING_DETAIL_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
