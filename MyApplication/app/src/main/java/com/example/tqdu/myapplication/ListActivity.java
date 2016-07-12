package com.example.tqdu.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private static List<Employee> listEmployee;
    Button btnBack;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        btnBack = (Button) findViewById(R.id.btn_back);

        Intent intent = getIntent();
        listEmployee = new ArrayList<Employee>();
        listEmployee.add((Employee)intent.getSerializableExtra("employee"));

        listView = (ListView) findViewById(R.id.list_employee);
        MyAdapter myAdapter = new MyAdapter(listEmployee,this);
        listView.setAdapter(myAdapter);
    }

    public static List<Employee> getListEmployee() {
        return listEmployee;
    }
}
