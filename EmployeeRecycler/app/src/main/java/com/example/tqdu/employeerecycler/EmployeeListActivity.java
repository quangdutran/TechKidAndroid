package com.example.tqdu.employeerecycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class EmployeeListActivity extends AppCompatActivity implements View.OnClickListener {

    private MyAdapter myAdapter;
    private static List<Employee> listEmployee;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        Intent intent = getIntent();
        listEmployee = new ArrayList<Employee>();
        listEmployee.add((Employee)intent.getSerializableExtra("employee"));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        myAdapter = new MyAdapter(this,listEmployee);
        recyclerView.setAdapter(myAdapter);
        btnBack = (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
