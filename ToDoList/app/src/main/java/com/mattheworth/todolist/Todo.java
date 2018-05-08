package com.mattheworth.todolist;

import java.util.ArrayList;

public class Todo {

//    /**
//     * Represents the title of the to do task
//     */
    private String todoTitle;
//
//    /**
//     * Represents the to do description
//     */
//    private String todoDescription;
//
//    /**
//     * Represents the to do priority
//     */
//    private String todoPriority;
//
//    /**
//     * Represents the due date of the to do
//     */
//    private String todoDate;
//
//    /**
//     * Represents the subtasks of the to do
//     */
//    private ArrayList<String> todoSubtasks;

    /**
     * Default to do constructor
     */
    public Todo() {
        todoTitle = "";
    }

    /**
     * The to do title that sets the to do title
     * @param todoTitle
     *      The to do title
     */
    public Todo(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    /**
     * Returns the title of the to do
     * @return
     *      The title of the to do
     */
    public String getTodoTitle() {
        return todoTitle;
    }

    /**
     * Sets the title of the to do
     * @param todoTitle
     *      The new title of the to do to set
     */
    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }
}
