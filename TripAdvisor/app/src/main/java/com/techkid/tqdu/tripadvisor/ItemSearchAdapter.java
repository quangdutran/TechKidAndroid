package com.techkid.tqdu.tripadvisor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by tqdu on 7/27/2016.
 */
public class ItemSearchAdapter extends BaseAdapter {

    Context context;
    List<ItemSearch> items = new ArrayList<>();
    private LayoutInflater layoutInflater;


    public ItemSearchAdapter(Context context, List<ItemSearch> items) {
        this.context = context;
        this.items = items;
        this.layoutInflater = LayoutInflater.from(this.context);

    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public ItemSearch getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View _view = layoutInflater.inflate(R.layout.item_search_list,viewGroup,false);
        if (view != null) {
            ImageView itemImage = (ImageView) _view.findViewById(R.id.item_search_image);
            TextView itemName = (TextView) _view.findViewById(R.id.item_search_txt);
            itemImage.setImageResource(((ItemSearch)getItem(i)).getImage());
            itemName.setText(((ItemSearch)getItem(i)).getName());
        }
        return _view;
    }
}
