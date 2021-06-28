package com.example.new_new_new;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DBAssetsHelper extends SQLiteAssetHelper {
    public static final String name = "contacts.db";
    public static final int version = 1;
    public static final String TABLE_CONTACTS = "contacts";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "Name";
    public static final String KEY_MAIL = "Mail";
    public static final String KEY_NATIONALITY = "Nationality";
    public static final String KEY_AGE = "Age";
    public static final String KEY_RESULTS = "Results";


    public DBAssetsHelper(Context context) {
        super(context, name, null, version);
    }
}
