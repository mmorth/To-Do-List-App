package com.mattheworth.todolist;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The MainActivity of the to do list application.
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * Represents the to do list number
     */
    private int todoTagNum = 1;

    /**
     * Represents the createTodoButton
     */
    private Button createTodoButton;

    /**
     * Represents the to do input title
     */
    private EditText todoInputTitle;

    /**
     * Represents the main to do scroll list
     */
    private ListView todoListView;

    /**
     * Represents the to do list order for the to do primary keys
     */
    private static ArrayList<Integer> todoPrimaryKeys = new ArrayList<>();

    /**
     * Represents the database handler for working with SQLite
     */
    private DBHandler dbHandler = new DBHandler(this, null, null, 3);

    /**
     * Keeps track of the primary key of the to dos
     */
    private int todoPrimaryKey = 0;

    /**
     * Represents the to do ListView adapater
     */
    ListAdapter todoListAdapter;

    /**
     * Represents the context for the MainActivity
     */
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Display the correct initial layout of the app
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v("START: ", "onCreate()");

        // Initialize the Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize the DrawerLayout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Initialize the NavigationView
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Prevent the keyboard from popping up when first running the app
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        // Create the references to the android layout components
        createTodoButton = (Button) findViewById(R.id.createTodoButton);
        todoInputTitle = (EditText) findViewById(R.id.todoInput);

        // Create a reference to the ListView
        todoListView = (ListView) findViewById(R.id.todoList);

        // Set the context reference to this activity
        context = this;

        // Create a new to do list card when the user taps the create button.
        createTodoButton.setOnClickListener (
            new Button.OnClickListener() {
                public void onClick(View v) {
                    if (todoInputTitle.getText() != null && todoInputTitle.getText().toString().length() != 0) {
                        // Create and store the to do in the database
                        String todoTitle = todoInputTitle.getText().toString();

                        // Store the primary key of the to do item in a certain position
                        todoPrimaryKeys.add(todoPrimaryKey);

                        Todo createTodo = new Todo(todoTitle);

                        dbHandler.addTodo(createTodo);

                        todoInputTitle.setText("");

                        // Display the new to do on screen
                        todoListAdapter = new TodoAdapter(context, dbHandler.getTodoTitles().toArray(new String[0]));
                        todoListView.setAdapter(todoListAdapter);
                    }
                }
            }
        );

        // Determine the action to complete if an individual to do list item is clicked
        todoListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Render the to do details activity page
                        Intent todoDetailsIntent = new Intent(getApplicationContext(), TodoDetailsActivity.class);
                        int todoPK = dbHandler.getTodopks().get(position);
                        Todo todoClicked = dbHandler.getTodoInfo(todoPK);

                        ArrayList<String> todoSubtasks = dbHandler.getTodoSubtasks(todoPK);

                        String todoTitle = String.valueOf(parent.getItemAtPosition(position));
                        todoDetailsIntent.putExtra("todoPK", todoPK);
                        todoDetailsIntent.putExtra("todoTitle", todoTitle);
                        todoDetailsIntent.putExtra("todoDescription", todoClicked.getTodoDescription());
                        todoDetailsIntent.putExtra("todoPriority", todoClicked.getTodoPriority());
                        todoDetailsIntent.putExtra("todoDate", todoClicked.getTodoDate());
                        todoDetailsIntent.putExtra("todoSubtasks", todoSubtasks);

                        startActivity(todoDetailsIntent);
                    }
                }
        );

        // Display all previously stored to dos
        todoListAdapter = new TodoAdapter(context, dbHandler.getTodoTitles().toArray(new String[0]));
        todoListView.setAdapter(todoListAdapter);
    }

    // Auto increment an int variable to associate an id with the to do and then make a method in the db handler to lookup and return a Todo object based on that information

    // ----------------------- Custom Methods ---------------------------------------------

    /**
     * Deletes a to do item from the list
     * @param view
     *      The view to delete the to do from
     */
    public void deleteTodo(View view) {
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.todoTitle);
        TextView taskKey = (TextView) parent.findViewById(R.id.todoKey);
        String task = String.valueOf(taskTextView.getText());
        int todoKey = Integer.parseInt((String) taskKey.getText());
        dbHandler.deleteTodo(todoKey);
        todoListAdapter = new TodoAdapter(context, dbHandler.getTodoTitles().toArray(new String[0]));
        todoListView.setAdapter(todoListAdapter);
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
        dbHandler.deleteTodo(task);
        todoListAdapter = new TodoAdapter(context, dbHandler.getTodoTitles().toArray(new String[0]));
        todoListView.setAdapter(todoListAdapter);

        // TODO: Put the completed to do in an archive list

    }

//    @Override
//    public void onResume() {
//        super.onResume();
//
//        todoItemList = dbHandler.databaseToArrayList();
//
//        Log.d("RESUMED: ", "onResume");
//
//        String stringListSize = "#" + todoItemList.size();
//        Log.v("Size after onResume: ", stringListSize);
//    }

    // ----------------------- Drawer Menu Methods ------------------------------------

    @Override
    public void onBackPressed() {
        // Close the Drawer menu when the back button is tapped
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
        getMenuInflater().inflate(R.menu.main, menu);
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
        // Do a certain action based on which menu item is selected
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
