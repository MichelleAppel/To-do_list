package com.example.michelle.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ToDoList_activity extends AppCompatActivity {
    private DBhelper dBhelper;
    ListView listView;
    ArrayAdapter<ToDo> adapter;
    ArrayList<ToDo> todo_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        dBhelper = new DBhelper(this);

        set_listView();
    }

    public void set_listView() {
        // Read database
        todo_list = dBhelper.read();

        // Set adapter
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, todo_list);
        listView = (ListView)findViewById(R.id.Todo_listView);
        listView.setAdapter(adapter);

        // Performed when long clicked
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                delete(pos);
                return true;
            }
        });
    }

    public void delete(int pos) {
        dBhelper.delete(adapter.getItem(pos));
        set_listView();
    }

    public void add(View view) {
        EditText editText = (EditText)findViewById(R.id.editText);
        String todo_string = editText.getText().toString();

        ToDo item = new ToDo(todo_string);
        dBhelper.create(item);

        set_listView();
    }
}
