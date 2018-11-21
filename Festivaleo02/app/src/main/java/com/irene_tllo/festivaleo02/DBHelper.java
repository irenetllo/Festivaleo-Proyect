package com.irene_tllo.festivaleo02;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by irene_ on 14/11/2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="FestivaleoO";
    private static final int DB_SCHEMA_VERSION=1;


    public DBHelper(Context context) {
        super(context, DB_NAME,null , DB_SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DBManager.CREATE_TABLE_USUARIO);
        DBManager.insertarUsuariosBase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
