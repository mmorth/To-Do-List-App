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
    private static final int DATABASE_VERSION = 3;

    /**
     * The file to use for the database
     */
    private static final String DATABASE_NAME = "todo.db";

    /**
     * The name of the table
     */
    private static final String TABLE_TODO = "todo";

    /**
     * Stores the primary key id of the item
     */
    private static final String COLUMN_TODO_ID = "_id";

    /**
     * Stores the todoTitle in the database
     */
    private static final String COLUMN_TODO_TITLE = "todo_title";

    /**
     * Stores the to do description
     */
    private static final String COLUMN_TODO_DESCRIPTION = "todo_description";

    /**
     * Stores the to do priority
     */
    private static final String COLUMN_TODO_PRIORITY = "todo_priority";

    /**
     * Stores the due date of the to do
     */
    private static final String COLUMN_TODO_DATE = "todo_date";

    /**
     * The name of the table
     */
    private static final String TABLE_SUBTASK = "subtask";

    /**
     * Stores the primary key id of the item
     */
    private static final String COLUMN_SUBTASK_ID = "_id";

    /**
     * Stores the title of the subtask
     */
    private static final String COLUMN_SUBTASK_TITLE = "subtask_title";

    /**
     * Stores the primary key of the to do it references
     */
    private static final String COLUMN_SUBTASK_TODO = "todo_ref";

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
        // Create the new to do table
        String queryTodo = "CREATE TABLE " + TABLE_TODO + "(" +
                COLUMN_TODO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_TODO_TITLE + " TEXT ," +
                COLUMN_TODO_DESCRIPTION + " TEXT ," +
                COLUMN_TODO_PRIORITY + " TEXT ," +
                COLUMN_TODO_DATE + " TEXT " +
                ");";

        // Execute the create table query
        db.execSQL(queryTodo);

        // Create the new table
        String querySubtask = "CREATE TABLE " + TABLE_SUBTASK + "(" +
                COLUMN_SUBTASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_SUBTASK_TITLE + " TEXT ," +
                COLUMN_SUBTASK_TODO + " INTEGER ," +
                " FOREIGN KEY (" + COLUMN_SUBTASK_TODO + ") REFERENCES " + TABLE_TODO + "(" + COLUMN_TODO_ID + ")" +
                ");";

        // Execute the create table query
        db.execSQL(querySubtask);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBTASK);

        // Create the new table
        onCreate(db);
    }

    /**
     * Adds a to do row to the database
     * @param todo
     *      The new to do to add to the database
     */
    public void addTodo(Todo todo) {
        // Prep to add a row to the database
        ContentValues values = new ContentValues();
        values.put(COLUMN_TODO_TITLE, todo.getTodoTitle());
        values.put(COLUMN_TODO_DESCRIPTION, todo.getTodoDescription());
        values.put(COLUMN_TODO_PRIORITY, todo.getTodoPriority());
        values.put(COLUMN_TODO_DATE, todo.getTodoDate());

        // Object that represents the database we will add a row to
        SQLiteDatabase db = getWritableDatabase();

        // Add product to databases
        db.insert(TABLE_TODO, null, values);
        db.close();
    }

    /**
     * Adds a to do row to the database
     * @param todo
     *      The new to do to add to the database
     */
    public void updateTodo(int todoPK, Todo todo) {
        // Prep to add a row to the database
        ContentValues values = new ContentValues();
        values.put(COLUMN_TODO_TITLE, todo.getTodoTitle());
        values.put(COLUMN_TODO_DESCRIPTION, todo.getTodoDescription());
        values.put(COLUMN_TODO_PRIORITY, todo.getTodoPriority());
        values.put(COLUMN_TODO_DATE, todo.getTodoDate());

        // Object that represents the database we will add a row to
        SQLiteDatabase db = getWritableDatabase();

        // Add product to databases
        String filter = COLUMN_TODO_ID + " = " + todoPK;
        db.update(TABLE_TODO, values, filter, null);
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
        db.execSQL("DELETE FROM " + TABLE_TODO + " WHERE " + COLUMN_TODO_TITLE + "=\"" + todoTitle + "\";");

        db.close();
    }

    /**
     * Removes a to do from the database
     * @param todoID
     *      The to do to remove from the database
     */
    public void deleteTodo(int todoID) {
        // Object that represents the database we will add a row to
        SQLiteDatabase db = getWritableDatabase();

        // Delete the to do from the database
        db.execSQL("DELETE FROM " + TABLE_TODO + " WHERE " + COLUMN_TODO_ID + "=\"" + todoID + "\";");

        db.close();
    }

    /**
     * Adds a subtask row to the database
     * @param subtaskTitle
     *      The new subtask to add to the database
     */
    public void addSubtask(String subtaskTitle, int todoPK) {
        // Prep to add a row to the database
        ContentValues values = new ContentValues();
        values.put(COLUMN_SUBTASK_TODO, todoPK);
        values.put(COLUMN_SUBTASK_TITLE, subtaskTitle);

        // Object that represents the database we will add a row to
        SQLiteDatabase db = getWritableDatabase();

        // Add product to databases
        db.insert(TABLE_SUBTASK, null, values);
        db.close();
    }

    /**
     * Removes a subtask from the database
     * @param subtaskTitle
     *      The subtask to remove from the database
     */
    public void deleteSubtask(String subtaskTitle) {
        // Object that represents the database we will add a row to
        SQLiteDatabase db = getWritableDatabase();

        // Delete the to do from the database
        db.execSQL("DELETE FROM " + TABLE_SUBTASK + " WHERE " + COLUMN_SUBTASK_TITLE + "=\"" + subtaskTitle + "\";");

        db.close();
    }

    /**
     * Print out the database as a String
     * @return
     *      A String that represents the database as a String
     */
    public ArrayList<String> getTodoTitles() {
        // Store the database as a String
        ArrayList<String> todos = new ArrayList<>();

        // Object that represents the database we will add a row to
        SQLiteDatabase db = getWritableDatabase();

        // Select all information from the database
        String query = "SELECT * FROM " + TABLE_TODO;

        // Create database cursor
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(c.moveToNext()) {
            todos.add(c.getString(c.getColumnIndex(COLUMN_TODO_TITLE)));
        }

        // Close and return the database
        db.close();
        return todos;
    }

    /**
     * Returns the Todo object stored in the row of the database
     * @return
     *      The todo ojbect stored in the row of the database
     *
     */
    public Todo getTodoInfo(int todoPK) {

        // Object that represents the database we will add a row to
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_TODO + " WHERE " + COLUMN_TODO_ID + " = " + todoPK + ";";

        // Create database cursor
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        // Set the Todo information that we will return
        Todo getTodo = new Todo();

        getTodo.setTodoTitle(c.getString(c.getColumnIndex(COLUMN_TODO_TITLE)));
        getTodo.setTodoDescription(c.getString(c.getColumnIndex(COLUMN_TODO_DESCRIPTION)));
        getTodo.setTodoPriority(c.getString(c.getColumnIndex(COLUMN_TODO_PRIORITY)));
        getTodo.setTodoDate(c.getString(c.getColumnIndex(COLUMN_TODO_DATE)));

        return getTodo;
    }

    /**
     * Returns a String ArrayList with the subtasks associated to the to do stored in the database
     * @return
     *      A String ArrayList with the subtasks associated to the to do stored in the database
     */
    public ArrayList<String> getTodoSubtasks(int todoPK) {
        // Store the database as a String
        ArrayList<String> subtasks = new ArrayList<>();

        // Object that represents the database we will add a row to
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_SUBTASK + " WHERE " + COLUMN_SUBTASK_TODO + " = " + todoPK;

        // Create database cursor
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(c.moveToNext()) {
            subtasks.add(c.getString(c.getColumnIndex(COLUMN_SUBTASK_TITLE)));
        }

        // Close and return the database
        db.close();
        c.close();
        return subtasks;
    }

    /**
     * Returns a String ArrayList with the primary keys associated to the to do stored in the database
     * @return
     *      A String ArrayList with the primary keys associated to the to do stored in the database
     */
    public ArrayList<Integer> getTodopks() {
        // Store the database as a String
        ArrayList<Integer> pks = new ArrayList<>();

        // Object that represents the database we will add a row to
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_TODO;

        // Create database cursor
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(c.moveToNext()) {
            pks.add(c.getInt(c.getColumnIndex(COLUMN_TODO_ID)));
        }

        // Close and return the database
        db.close();
        return pks;
    }

}
