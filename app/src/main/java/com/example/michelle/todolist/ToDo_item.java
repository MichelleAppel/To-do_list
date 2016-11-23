package com.example.michelle.todolist;

/**
 * Created by Michelle on 22-11-2016.
 * To-do item class
 */

class ToDo_item {
    String todo_string;
    int id;

    // constructor
    ToDo_item(String todo_string) {
        this.todo_string = todo_string;
    }

    ToDo_item(int id, String todo_string) {
        this.todo_string = todo_string;
        this.id = id;
    }

    public String toString() {
        return todo_string;
    }
}
