package com.example.tqdu.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by tqdu on 7/12/2016.
 */
public class MyAdapter extends BaseAdapter {
    private List<Employee> listEmployee;
    Context context;
    private LayoutInflater layoutInflater;

    public MyAdapter(List<Employee> list, Context context) {
        listEmployee = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return listEmployee.size();
    }

    @Override
    public Employee getItem(int position) {
        return listEmployee.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.item_on_list,null);
        Button btnAdd = (Button) view.findViewById(R.id.btn_detail);

        if (view != null) {
            TextView txtContainer = (TextView) view.findViewById(R.id.txt_name);
            txtContainer.setText(listEmployee.get(position).getName());

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Employee employee = ListActivity.getListEmployee().get(position);
                    Toast.makeText()
                }
            });
        }

        return view;
    }
}
