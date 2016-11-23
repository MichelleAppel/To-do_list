package com.example.michelle.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ToDoList_activity extends AppCompatActivity {
    private DBhelper dBhelper;
    ListView listView;
    ArrayAdapter<ToDo_item> adapter;
    ArrayList<ToDo_item> todo_list;
    EditText add_editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        dBhelper = new DBhelper(this);

        set_listView();

        add_editText = (EditText)findViewById(R.id.editText);


        // Performs action when enter is pressed
        add_editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    String todo_string = add_editText.getText().toString();

                    ToDo_item item = new ToDo_item(todo_string);
                    dBhelper.create(item);

                    add_editText.setText("");
                    set_listView();

                    return true;
                }
                return false;
            }
        });

    }

    public void set_listView() {
        // Read database
        todo_list = dBhelper.read();

        // Set adapter
        adapter = new ArrayAdapter<>(this, R.layout.row_layout, R.id.todo_TextView, todo_list);
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
}
