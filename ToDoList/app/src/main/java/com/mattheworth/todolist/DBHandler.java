package com.mattheworth.todolist;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;

import java.util.ArrayList;

/**
 * This Class is used to create the SQLite database for the to do application
 */
public class DBHandler extends SQLiteOpenHelper {

    /**
     * Stores the version of the database
     * Update when there are updates to the data
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * The file to use for the database
     */
    private static final String DATABASE_NAME = "todo.db";

    /**
     * The name of the table
     */
    public static final String TABLE_NAME = "todo";

    /**
     * Stores the primary key id of the item
     */
    public static final String COLUMN_ID = "_id";

    /**
     * Stores the todoTitle in the database
     */
    public static final String COLUMN_TODO_TITLE = "todotitle";

    /**
     * The constructor for the SQLite database
     * @param context
     *      Stores the background information
     * @param name
     *      The name of the database
     * @param factory
     *      Background information
     * @param version
     *      The version of the database
     */
    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the new table
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_TODO_TITLE + " TEXT " +
                ");";

        // Execute the create table query
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create the new table
        onCreate(db);
    }

    /**
     * Adds a to do row to the database
     * @param todoTitle
     *      The new to do to add to the database
     */
    public void addTodo(String todoTitle) {
        // Prep to add a row to the database
        ContentValues values = new ContentValues();
        values.put(COLUMN_TODO_TITLE, todoTitle);

        // Object that represents the database we will add a row to
        SQLiteDatabase db = getWritableDatabase();

        // Add product to databases
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    /**
     * Removes a to do from the database
     * @param todoTitle
     *      The to do to remove from the database
     */
    public void deleteTodo(String todoTitle) {
        // Object that represents the database we will add a row to
        SQLiteDatabase db = getWritableDatabase();

        // Delete the to do from the database
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_TODO_TITLE + "=\"" + todoTitle + "\";");
//        db.delete(TABLE_NAME, COLUMN_TODO_TITLE + "=?", new String[] {todoTitle});
        db.close();
    }

    /**
     * Print out the database as a String
     * @return
     *      A String that represents the database as a String
     */
    public ArrayList<String> databaseToArrayList() {
        // Store the database as a String
        ArrayList<String> todos = new ArrayList<>();

        // Object that represents the database we will add a row to
        SQLiteDatabase db = getWritableDatabase();

        // Select all information from the database
        String query = "SELECT * FROM " + TABLE_NAME;

        // Create database cursor
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        // Parse through the database
//        while (!c.isAfterLast()) {
//            if (c.getString(c.getColumnIndex(COLUMN_TODO_TITLE)) != null) {
//                todos.add(c.getString(c.getColumnIndex(COLUMN_TODO_TITLE)));
//            }
//        }

        while(c.moveToNext()) {
            todos.add(c.getString(c.getColumnIndex(COLUMN_TODO_TITLE)));
        }

        String sizeString = "#" + todos.size();
        Log.v("dbToArrayList method: ", sizeString);

        // Close and return the database
        db.close();
        return todos;
    }

}