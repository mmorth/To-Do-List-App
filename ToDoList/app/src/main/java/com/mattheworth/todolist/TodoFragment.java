package com.mattheworth.todolist;

import android.support.annotation.NonNull;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Represents the to do list item fragment
 */
public class TodoFragment extends Fragment {

    /**
     * Refers to the CheckBox object
     */
    private CheckBox todoCheckBox;

    /**
     * Refers to the TextView object
     */
    private TextView todoTitle;

    /**
     * Refers to the to do delete button
     */
    private Button todoDelete;

    /**
     * Represents the to do item title
     */
    private String todoItemTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Initialize the fragment
        View view = inflater.inflate(R.layout.todo_fragment, container, false);

        Bundle args = getArguments();

        if (args != null && args.getString("todoTitle", "") != null) {
            todoItemTitle = args.getString("todoTitle", "");
            Log.d("OUTPUT: ", todoItemTitle);
        }

        // Create references for each component in the fragment
        todoCheckBox = (CheckBox) view.findViewById(R.id.todoCheckOff);
        todoTitle = (TextView) view.findViewById(R.id.todoTitle);
        todoDelete = (Button) view.findViewById(R.id.deleteTodo);

        // Create a new to do list card when the user taps the create button.
        todoCheckBox.setOnClickListener (
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        // Archive the to do fragment from the scroll list
                        // Move to completed to dos list
                    }
                }
        );

        // Create a new to do list card when the user taps the create button.
        todoDelete.setOnClickListener (
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        // Delete the to do fragment from the scroll list
                    }
                }
        );

        return view;
    }

    /**
     * USED FOR TESTING PURPOSES ONLY
     */
    public String getToDoTitle() {
        return todoItemTitle;
    }

}
