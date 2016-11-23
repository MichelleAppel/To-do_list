package com.example.michelle.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Michelle on 22-11-2016.
 * Database helper
 */

class DBhelper extends SQLiteOpenHelper {
    // Set fields of database schema
    private static final String DATABASE_NAME = "DATABASE.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE = "todo_table";

    private String todoString = "todo_string";

    // Constructor
    DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // onCreate
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Query
        String CREATE_TABLE = "CREATE TABLE " + TABLE + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT , "
                + todoString + " TEXT )";
        db.execSQL(CREATE_TABLE);
    }

    // onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    // CRUD methods

    // Create
    void create(ToDo_item item) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(todoString, item.todo_string);

        db.insert(TABLE, null, values);
        db.close();
    }

    // Read
    ArrayList<ToDo_item> read() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<ToDo_item> todo_list = new ArrayList<>();

        String query = "SELECT _id , " + todoString + " FROM " + TABLE;
        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String todo_string = cursor.getString(cursor.getColumnIndex(todoString));

            ToDo_item item = new ToDo_item(id, todo_string);
            todo_list.add(item);
        }

        cursor.close();
        db.close();
        return todo_list;
    }

    // update
    public void update(ToDo_item item) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", item.id);
        db.update(TABLE, values, "_id = ? ", new String[] {String.valueOf(item.todo_string)});
        db.close();
    }

    // delete
    void delete(ToDo_item item) {
        int id = item.id;
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE, " _id = ? ", new String[] {String.valueOf(id)});
        db.close();
    }
}