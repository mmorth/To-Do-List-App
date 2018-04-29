package com.mattheworth.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.widget.RelativeLayout;
import android.widget.Button;
import android.graphics.Color;
import android.widget.EditText;
import android.content.res.Resources;
import android.util.TypedValue;

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

//        // Create a new Relative Layout for the UI
//        RelativeLayout todoLayout = new RelativeLayout(this);
//        todoLayout.setBackgroundColor(Color.YELLOW);
//
//        // Create a new button
//        Button createButton = new Button(this);
//        createButton.setText(getResources().getString(R.string.create_button));
//        createButton.setTextColor(Color.WHITE);
//        createButton.setBackgroundColor(Color.BLACK);
//        createButton.setId(1);
//
//        // Create an input field
//        EditText inputField = new EditText(this);
//
//        // Create the layout parameters (container)
//        RelativeLayout.LayoutParams buttonInfo = new RelativeLayout.LayoutParams(
//                // Get the height and width of the button automatically
//                RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT
//        );
//
//        // Create the layout parameters (container)
//        RelativeLayout.LayoutParams inputFieldInfo = new RelativeLayout.LayoutParams(
//                // Get the height and width of the input field automatically
//                RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT
//        );
//
//        // Align the button position
//        buttonInfo.addRule(RelativeLayout.CENTER_HORIZONTAL);
//        buttonInfo.addRule(RelativeLayout.CENTER_VERTICAL);
//
//        // Align the input field on the layout
//        inputFieldInfo.addRule(RelativeLayout.ABOVE, createButton.getId());
//        inputFieldInfo.addRule(RelativeLayout.CENTER_HORIZONTAL);
//        inputFieldInfo.setMargins(0,0,0,50);
//
//        // Get information about the app
//        Resources r = getResources();
//
//        // Convert dip to pixels and store in variable
//        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, r.getDisplayMetrics());
//
//        // Make the input be the same size on each display
//        inputField.setWidth(px);
//
//        // Add the button to the layout
//        todoLayout.addView(createButton, buttonInfo);
//        todoLayout.addView(inputField, inputFieldInfo);
//
//        // Set the content view to display the created layout
//        setContentView(todoLayout);

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
