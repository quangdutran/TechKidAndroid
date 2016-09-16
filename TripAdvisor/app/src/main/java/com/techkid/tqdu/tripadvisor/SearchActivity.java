package com.techkid.tqdu.tripadvisor;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private ListView itemSearchList;
    public static List<ItemSearch> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        itemList.add(new ItemSearch(R.drawable.icon_restaurant_square,"Restaurant"));
        itemList.add(new ItemSearch(R.drawable.icon_attraction_square,"Entertainment"));
        itemList.add(new ItemSearch(R.drawable.icon_hotel_square,"Hotel"));

        itemSearchList = (ListView) findViewById(R.id.list_option);
        ItemSearchAdapter itemSearchAdapter = new ItemSearchAdapter(this,itemList);
        itemSearchList.setAdapter(itemSearchAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu,menu);
        // Associate searchable configuration with the SearchView
        /*SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.menu.options_menu).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));*/

        return true;
    }
}
