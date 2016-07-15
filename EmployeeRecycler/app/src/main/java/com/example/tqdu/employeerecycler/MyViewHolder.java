package com.example.tqdu.employeerecycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    //Component in the item
    public ImageView employeeImage;
    TextView txtName, txtAge;
    Button btnBack;

    public MyViewHolder(View itemView) {
        super(itemView);
        employeeImage = (ImageView) itemView.findViewById(R.id.employee_image);
        txtName = (TextView) itemView.findViewById(R.id.txt_name);
        txtAge = (TextView) itemView.findViewById(R.id.txt_age);
        btnBack = (Button) itemView.findViewById(R.id.btn_back);
    }
}