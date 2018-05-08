package com.mattheworth.todolist;

/**
 * This class represents a subtask for a to do
 */
public class Subtask {

    /**
     * Represents the subtask for a to do
     */
    private String subtaskTitle;

    /**
     * The argument constructor
     */
    public Subtask() {
        this.subtaskTitle = "";
    }

    /**
     * The argument constructor
     * @param subtaskTitle
     *      The title of the subtask
     */
    public Subtask(String subtaskTitle) {
        this.subtaskTitle = subtaskTitle;
    }

    /**
     * Returns the subtask title
     * @return
     *      The title of the subtask
     */
    public String getSubtaskTitle() {
        return subtaskTitle;
    }

    /**
     * Sets the subtask title
     * @param subtaskTitle
     *      The new title of the subtask
     */
    public void setSubtaskTitle(String subtaskTitle) {
        this.subtaskTitle = subtaskTitle;
    }

}
