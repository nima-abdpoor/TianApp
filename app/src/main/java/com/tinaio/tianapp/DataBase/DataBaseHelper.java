package com.tinaio.tianapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DataBaseHelper extends SQLiteOpenHelper {
    Context context;
    List<String> IDs;
    List<String> names;
    List<List<String>> services;
    private static final String[] ALL_COLUMNS = {"ID", "NAME"};
    public static final String DB_NAME = "MY_SERVICES";
    public static final String TABLE_NAME = "MY_SERVICES_TABLE";
    public static final int DB_VERSION = 1;
    private final String CMD_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
            "'ID' TEXT NOT NULL UNIQUE, " +
            "'NAME' TEXT " +
            " ) ";

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CMD_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Long InsertService(String ID, String name) {
        long result = (long) 0.0;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", ID);
        contentValues.put("NAME", name);
        try {
            result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        } catch (SQLException ex) {
            ex.getCause();
        }
        sqLiteDatabase.close();
        return result;
    }

    public List<List<String>> GetServices() {
        IDs = new ArrayList<>();
        names = new ArrayList<>();
        services = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(true, TABLE_NAME, ALL_COLUMNS,
                null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            IDs.add(cursor.getString(cursor.getColumnIndex("ID")));
            names.add(cursor.getString(cursor.getColumnIndex("NAME")));
        }
        for (int i = 0; i < cursor.getCount(); ++i) {
            services.add(IDs);
            services.add(names);
        }

        return services;
    }

    public int GetServicesCount() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(true, TABLE_NAME, ALL_COLUMNS,
                null, null, null, null, null, null);
        return cursor.getCount();
    }
}
