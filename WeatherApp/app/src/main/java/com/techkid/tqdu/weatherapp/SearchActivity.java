package com.techkid.tqdu.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txtSearch;
    ImageButton btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        txtSearch = (EditText) findViewById(R.id.txtSearch);
        btnSearch = (ImageButton) findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String city = txtSearch.getText().toString();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("city",city);
        startActivity(intent);
    }
}
