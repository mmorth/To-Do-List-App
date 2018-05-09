package com.mattheworth.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class TodoDetailsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * Represents the to do title
     */
    private EditText todoDetailsTitle;

    /**
     * Represents the to do description widget
     */
    private EditText todoDetailsDescription;

    /**
     * Represents the to do drop down priority menu widget
     */
    private Spinner todoDetailsPriority;

    /**
     * Represents the to do label widget
     */
    private EditText todoDetailsLabel;

    /**
     * Represents the to do subtask input widget
     */
    private EditText todoSubtaskInput;

    /**
     * Represents the to do details create button widget
     */
    private Button todoDetailsCreateButton;

    /**
     * Represents the to do details subtask list
     */
    private ListView todoDetailsSubtaskList;

    /**
     * Represents the to do details priority label
     */
    private TextView todoDetailsPriorityLabel;

    /**
     * Represents the to do details label TextView
     */
    private TextView todoDetailsLabelLabel;

    /**
     * Represents the to do details save button
     */
    private Button todoDetailsSaveButton;

    /**
     * Represents the to do ListView adapater
     */
    private ListAdapter todoListAdapter;

    /**
     * Represents the context for the MainActivity
     */
    private Context context;

    /**
     * Represents the database handler for working with SQLite
     */
    private DBHandler dbHandler = new DBHandler(this, null, null, 3);

    /**
     * Stores the value of the to do primary key the subtask belongs to
     */
    private int todoID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Prevent the keyboard from popping up when first running the app
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        // Pass information from the previous activity
        Intent mainIntent = getIntent();
        final int todoPK = mainIntent.getIntExtra("todoPK", 0);
        todoID = todoPK;
        String todoTitle = mainIntent.getStringExtra("todoTitle");
        String todoDescription = mainIntent.getStringExtra("todoDescription");
        final String todoPriority = mainIntent.getStringExtra("todoPriority");
        String todoDate = mainIntent.getStringExtra("todoDate");
        ArrayList<String> subtasks = mainIntent.getStringArrayListExtra("todoSubtasks");

        // Initialize the widget references
        todoDetailsTitle = (EditText) findViewById(R.id.todoDetailsTitle);
        todoDetailsDescription = (EditText) findViewById(R.id.todoDetailsDescription);
        todoDetailsDescription.setText(todoDescription);
        todoDetailsPriority = (Spinner) findViewById(R.id.todoDetailsPriority);

        if (todoPriority.equals("1 - Urgent")) {
            todoDetailsPriority.setSelection(0);
        } else if (todoPriority.equals("2 - Important")) {
            todoDetailsPriority.setSelection(1);
        } else {
            todoDetailsPriority.setSelection(2);
        }

        todoDetailsLabel = (EditText) findViewById(R.id.todoDetailsLabel);
        todoDetailsLabel.setText(todoDate);
        todoSubtaskInput = (EditText) findViewById(R.id.todoDetailsSubtaskInput);
        todoDetailsCreateButton = (Button) findViewById(R.id.todoDetailsCreateButton);
        todoDetailsSubtaskList = (ListView) findViewById(R.id.todoDetailsSubtaskList);
        todoDetailsPriorityLabel = (TextView) findViewById(R.id.todoDetailsPriorityLabel);
        todoDetailsLabelLabel = (TextView) findViewById(R.id.todoDetailsLabelLabel);
        todoDetailsSaveButton = (Button) findViewById(R.id.todoDetailsBackButton);

        todoDetailsTitle.setText(todoTitle);

        context = this;

        // Create a new to do list card when the user taps the create button.
        todoDetailsCreateButton.setOnClickListener (
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        if (todoSubtaskInput.getText() != null && todoSubtaskInput.getText().toString().length() != 0) {
                            dbHandler.addSubtask(todoSubtaskInput.getText().toString(), todoPK);
                            todoSubtaskInput.setText("");

                            todoListAdapter = new TodoAdapter(context, dbHandler.getTodoSubtasks(todoPK).toArray(new String[0]));
                            todoDetailsSubtaskList.setAdapter(todoListAdapter);
                        }
                    }
                }
        );

        // Save the content of the to do when the save button is clicked
        todoDetailsSaveButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        // Save to do details contents
                        Todo saveTodo = new Todo();
                        saveTodo.setTodoTitle(todoDetailsTitle.getText().toString());
                        saveTodo.setTodoPriority(todoDetailsPriority.getSelectedItem().toString());
                        saveTodo.setTodoDescription(todoDetailsDescription.getText().toString());
                        saveTodo.setTodoDate(todoDetailsLabel.getText().toString());

                        dbHandler.updateTodo(todoPK, saveTodo);
                    }
                }
        );

        MyEditTextDatePicker myEditTextDatePicker = new MyEditTextDatePicker(context);

        todoListAdapter = new TodoAdapter(context, dbHandler.getTodoSubtasks(todoPK).toArray(new String[0]));
        todoDetailsSubtaskList.setAdapter(todoListAdapter);

    }

    /**
     * Deletes a to do item from the list
     * @param view
     *      The view to delete the to do from
     */
    public void deleteTodo(View view) {
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.todoTitle);
        String task = String.valueOf(taskTextView.getText());
        dbHandler.deleteSubtask(task);
        todoListAdapter = new TodoAdapter(context, dbHandler.getTodoSubtasks(todoID).toArray(new String[0]));
        todoDetailsSubtaskList.setAdapter(todoListAdapter);
    }

    /**
     * Completes a to do item from the list and puts it in an archive list
     * @param view
     *      The view to delete the to do from
     */
    public void completeTodo(View view) {
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.todoTitle);
        String task = String.valueOf(taskTextView.getText());
        dbHandler.deleteSubtask(task);
        todoListAdapter = new TodoAdapter(context, dbHandler.getTodoSubtasks(todoID).toArray(new String[0]));
        todoDetailsSubtaskList.setAdapter(todoListAdapter);

        // Put the completed to do in an archive list

    }

    /**
     * Goes back to the previous page
     * @param view
     *      The current view
     */
    public void goBack(View view) {
        finish();
    }

    // -------------------------- Drawer Menu Methods --------------------------

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.todo_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
