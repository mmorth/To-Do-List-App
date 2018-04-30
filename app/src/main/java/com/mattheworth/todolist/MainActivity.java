package com.mattheworth.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.support.v4.view.GestureDetectorCompat;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    // Create a reference to the TextView object
    private TextView todoCreateText;

    // Create object to detect gestures
    private GestureDetectorCompat gestureDetector;

    // TEST: message for console output
    private static final String TAG = "Activity_Message";

    /**
     * Called when the Activity is first called
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Log.i(TAG, ": onCreate()");

        // Create an object that matches the todo input button
        Button createButton = (Button) findViewById(R.id.create_button);

        // Handle the situation when the button is tapped
        createButton.setOnClickListener(
                // Create the interface to handle the button tapped
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        // Set the text in the middle of the screen to what the use entered in the input field
                        todoCreateText = (TextView) findViewById(R.id.todo_create_text);
                        EditText todoInput = (EditText) findViewById(R.id.todo_input_text);
                        todoCreateText.setText(todoInput.getText());
                        todoInput.setText("");
                    }
                }
        );

        // TODO: Remove this method when finished testing
        // Handle the situation when the button is held
        createButton.setOnLongClickListener(
                new Button.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        todoCreateText = (TextView) findViewById(R.id.todo_create_text);
                        todoCreateText.setText("Are you sure you want to create this todo?");
                        return true;
                    }
                }
        );

        // Create object of the create text submit field
        todoCreateText = (TextView) findViewById(R.id.todo_create_text);

        // Create objects for the different gestures
        this.gestureDetector = new GestureDetectorCompat(this, this);
        gestureDetector.setOnDoubleTapListener(this);

    }

    //////////// Gestures /////////////////////

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        todoCreateText.setText("onSingleTapConfirmed");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        todoCreateText.setText("onDoubleTap");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        todoCreateText.setText("onDoubleTapEvent");
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        todoCreateText.setText("onDown");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        todoCreateText.setText("onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        todoCreateText.setText("onSingleTapUp");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        todoCreateText.setText("onScroll");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        todoCreateText.setText("onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        todoCreateText.setText("onFling");
        return true;
    }

    /////////////////// End Gestures //////////////////////

    /**
     * Determines whether a specific touch was a gesture
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent (MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
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

    /**
     * Called when the activity is no longer visible.
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, ": onStop()");
    }

    /**
     * Called just before the activity is destroyed.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, ": onDestroy()");
    }
}
