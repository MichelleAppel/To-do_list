package com.example.michelle.todolist;

/**
 * Created by Michelle on 22-11-2016.
 * To-do item class
 */

public class ToDo {
    public String todo_string;
    public int id;

    // constructor
    public ToDo(String todo_string) {
        this.todo_string = todo_string;
    }

    public ToDo(int id, String todo_string) {
        this.todo_string = todo_string;
        this.id = id;
    }

    public String toString() {
        return todo_string;
    }
}
