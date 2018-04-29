package com.mattheworth.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // TEST: message for console output
    private static final String TAG = "Activity_Message";

    /**
     * Called when the Activity is first called
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Log.i(TAG, ": onCreate()");

        // Create an object that matches the todo input button
        Button createButton = (Button) findViewById(R.id.create_button);

        // Handle the situation when the button is clicked
        createButton.setOnClickListener(
                // Create the interface to handle the button click
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        // Set the text in the middle of the screen to what the use entered in the input field
                        TextView todoCreateText = (TextView) findViewById(R.id.todo_create_text);
                        EditText todoInput = (EditText) findViewById(R.id.todo_input_text);
                        todoCreateText.setText(todoInput.getText());
                        todoInput.setText("");
                    }
                }
        );

    }

    /**
     * Called after the onCreate() or onRestart() method is called.
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, ": onStart()");
    }

    /**
     * Called when this Activity is the current Activity that is open.
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, ": onResume()");
    }

    /**
     * Called when the user is no longer looking at the this Activity's screen
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, ": onPause()");
    }

    /** Called when the activity is no longer visible. */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, ": onStop()");
    }

    /** Called just before the activity is destroyed. */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, ": onDestroy()");
    }
}
