package com.example.tow.todolist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText itemET;
    private Button btn;
    private ListView itemList;

    private ArrayList<String>items;
    private ArrayAdapter<String> adapter;
    private String text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemET = (EditText) findViewById(R.id.item_edit_text);
        btn= (Button) findViewById(R.id.add_btn);
        itemList= (ListView) findViewById(R.id.items_list);

        items = FileHelper.readData(this);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,items );
        itemList.setAdapter(adapter);
        btn.setOnClickListener(this);
        itemList.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_btn:
                String itemEntered = itemET.getText().toString();
                adapter.add(itemEntered);
                itemET.setText("");

                FileHelper.writeData(items, this);


                Context context;
                Toast toast=Toast.makeText(getApplicationContext(),"Item Added",Toast.LENGTH_SHORT);
                toast.show();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        items.remove(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(),"Delete",Toast.LENGTH_SHORT).show();
    }

    public void setText(String text) {
        this.text = text;
    }
}
