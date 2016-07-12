package com.example.tqdu.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editName, editAge, editAddress, editPhone;
    RadioGroup radioGroup;
    RadioButton genderBtn;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = (EditText) findViewById(R.id.edit_name);
        editAge = (EditText) findViewById(R.id.edit_age);
        editAddress = (EditText) findViewById(R.id.edit_phone);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        btnAdd = (Button) findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(this);

    }

    private boolean checkEmpty() {
        if (editName.getText().toString().isEmpty()
                || editAddress.getText().toString().isEmpty()
                || editAge.getText().toString().isEmpty())
            return false;
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                if (!checkEmpty()) {
                    Toast.makeText(this,"Empty",Toast.LENGTH_SHORT).show();
                } else {
                    boolean gender = true;
                    if (radioGroup.getCheckedRadioButtonId() == R.id.female) {
                        gender = false;
                    }
                    Employee employee = new Employee(editName.getText().toString(),
                                                    gender, editAddress.getText().toString(),
                                                    editPhone.getText().toString(),
                                                    Integer.valueOf(editAge.getText().toString()));
                    Intent intent = new Intent(this,ListActivity.class);
                    intent.putExtra("employee",employee);
                    startActivity(intent);
                }
                break;
        }
    }
}
