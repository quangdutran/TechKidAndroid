package com.example.tqdu.employeerecycler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by tqdu on 7/15/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    private static List<Employee> listEmployee;

    public MyAdapter(Context context, List<Employee> employeeList) {
        super();
        this.context = context;
        listEmployee = employeeList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.employee_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        parent.setTag(myViewHolder);
        return  myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.employeeImage.setImageResource(R.drawable.ic_face_black_48dp);
        holder.txtName.setText(listEmployee.get(position).getName());
        holder.txtAge.setText(listEmployee.get(position).getAge());
      /*  holder.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,MainActivity.class);

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return listEmployee.size();
    }
}
