package com.mattheworth.todolist;

import java.util.ArrayList;

public class Todo {

    /**
     * Represents the title of the to do task
     */
    private String todoTitle;

    /**
     * Represents the to do description
     */
    private String todoDescription;

    /**
     * Represents the to do priority
     */
    private String todoPriority;

    /**
     * Represents the due date of the to do
     */
    private String todoDate;

    /**
     * Represents the subtasks of the to do
     */
    private ArrayList<String> todoSubtasks;

    /**
     * Default to do constructor
     */
    public Todo() {
        todoTitle = "";
        todoDescription = "";
        todoPriority = "";
        todoDate = "";
        todoSubtasks = new ArrayList<>();
    }

    /**
     * The to do title that sets the to do title
     * @param todoTitle
     *      The to do title
     */
    public Todo(String todoTitle) {
        this.todoTitle = todoTitle;
        todoDescription = "";
        todoPriority = "";
        todoDate = "";
        todoSubtasks = new ArrayList<>();
    }

    /**
     * Constructor that initializes all variables of a to do
     * @param todoTitle
     *      The title of the to do
     * @param todoDescription
     *      The description of the to do
     * @param todoPriority
     *      The priority of the to do
     * @param todoDate
     *      The due date of the to do
     * @param todoSubtasks
     *      The list of subtasks related to the to do
     */
    public Todo(String todoTitle, String todoDescription, String todoPriority, String todoDate, ArrayList<String> todoSubtasks) {
        this.todoTitle = todoTitle;
        this.todoDescription = todoDescription;
        this.todoPriority = todoPriority;
        this.todoDate = todoDate;
        this.todoSubtasks = todoSubtasks;
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

    public String getTodoDescription() {
        return todoDescription;
    }

    /**
     * Sets the description of the to do
     * @param todoDescription
     *      The new description of the to do
     */
    public void setTodoDescription(String todoDescription) {
        this.todoDescription = todoDescription;
    }

    /**
     * Returns the priority of the to do
     * @return
     *      The priority of the to do
     */
    public String getTodoPriority() {
        return todoPriority;
    }

    /**
     * Sets the priority of the to do
     * @param todoPriority
     *      The priority of the to do
     */
    public void setTodoPriority(String todoPriority) {
        this.todoPriority = todoPriority;
    }

    /**
     * Returns the due date for the to do
     * @return
     *      The due date for the to do
     */
    public String getTodoDate() {
        return todoDate;
    }

    /**
     * Sets the due date for the to do
     * @param todoDate
     *      The new to do due date
     */
    public void setTodoDate(String todoDate) {
        this.todoDate = todoDate;
    }

    /**
     * Returns the list of subtasks for the to do
     * @return
     *      The subtask list for the to do
     */
    public ArrayList<String> getTodoSubtasks() {
        return todoSubtasks;
    }

    /**
     * Sets the subtask list of the to do
     * @param todoSubtasks
     *      The new subtask list of the to do
     */
    public void setTodoSubtasks(ArrayList<String> todoSubtasks) {
        this.todoSubtasks = todoSubtasks;
    }
}
