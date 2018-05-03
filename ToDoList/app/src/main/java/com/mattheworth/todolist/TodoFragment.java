package com.mattheworth.todolist;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class TodoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Initialize the fragment
        View view = inflater.inflate(R.layout.todo_fragment, container, false);

        // Create references for each component in the fragment
        CheckBox todoCheckBox = (CheckBox) view.findViewById(R.id.todoCheckOff);
        TextView todoDescription = (TextView) view.findViewById(R.id.todoTitle);
        Button todoDelete = (Button) view.findViewById(R.id.deleteTodo);

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
     * Sets the title of the to do
     * @param title
     *      The title of the to do
     */
    public void setToDoDescription(String title) {
        TextView todoDescription = (TextView) view.findViewById(R.id.todoDescription);
        todoDescription.setText(title);
    }

}
