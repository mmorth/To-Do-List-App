package com.mattheworth.todolist;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class TodoListFragment extends Fragment {

    /**
     * Refers to the TextView object
     */
    private TextView todoTitle;

    /**
     * Refers to the to do delete button
     */
    private Button todoDelete;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Initialize the fragment
        View view = inflater.inflate(R.layout.todo_fragment, container, false);

        // Create references for each component in the fragment
        todoTitle = (TextView) view.findViewById(R.id.todoTitle);
        todoDelete = (Button) view.findViewById(R.id.deleteTodo);

        // Create a new to do list card when the user taps the create button.
        todoDelete.setOnClickListener (
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        // Delete the to do list fragment from the scroll list
                    }
                }
        );

        return view;
    }

}
