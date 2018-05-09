package com.mattheworth.todolist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class TodoAdapter
extends ArrayAdapter<String>  {

    public TodoAdapter(@NonNull Context context, String[] todoTitles) {
        super(context, R.layout.todo_item, todoTitles);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Render the view
        LayoutInflater todoInflater = LayoutInflater.from(getContext());

        // Create a reference to the view
        View customView = todoInflater.inflate(R.layout.todo_item, parent, false);

        // Initialize the individual components of the view
        String todoTitle = getItem(position);
        TextView todoTitleText = (TextView) customView.findViewById(R.id.todoTitle);
        todoTitleText.setText(todoTitle);
        TextView todoKey = (TextView) customView.findViewById(R.id.todoKey);
//        todoKey.setText(position);
        Button deleteButton = (Button) customView.findViewById(R.id.deleteTodo);
        deleteButton.setText("Delete");

        return customView;
    }
}
