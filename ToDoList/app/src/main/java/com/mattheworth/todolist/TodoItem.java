//package com.mattheworth.todolist;
//
//import android.content.Context;
//import android.support.annotation.Nullable;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//public class TodoItem extends LinearLayout {
//
//    /**
//     * Refers to the CheckBox object
//     */
//    private CheckBox todoCheckBox;
//
//    /**
//     * Refers to the TextView object
//     */
//    private TextView todoTitle;
//
//    /**
//     * Refers to the to do delete button
//     */
//    private Button todoDelete;
//
//    /**
//     * Represents the to do item title
//     */
//    private String todoItemTitle;
//
//    public TodoItem(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        LayoutInflater inflater = LayoutInflater.from(context);
//        inflater.inflate(R.layout.todo_item, this);
//
//        // Create references for each component in the fragment
//        todoCheckBox = (CheckBox) findViewById(R.id.todoCheckOff);
//        todoTitle = (TextView) findViewById(R.id.todoTitle);
//        todoDelete = (Button) findViewById(R.id.deleteTodo);
//        todoDelete.setText("Delete");
//
//        if (todoCheckBox.getParent() != null) {
//            ((ViewGroup) todoCheckBox.getParent()).removeView(todoCheckBox);
//        }
//        this.addView(todoCheckBox);
//
//        if (todoTitle.getParent() != null) {
//            ((ViewGroup) todoTitle.getParent()).removeView(todoTitle);
//        }
//        this.addView(todoTitle);
//
//        if (todoDelete.getParent() != null) {
//            ((ViewGroup) todoDelete.getParent()).removeView(todoDelete);
//        }
//        this.addView(todoDelete);
//
//    }
//
//
//    public void setTodoTitle(String title) {
//        todoItemTitle = title;
//    }
//
//}
