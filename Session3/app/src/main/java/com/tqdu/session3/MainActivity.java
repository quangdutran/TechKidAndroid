package com.tqdu.session3;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String HAIPHONG = "Hai Phong";
    private static final int HP_WEATHER = R.drawable.rain;
    private static final String HP_TEMP = "29 C";

    private static final String HANAM = "Ha Nam";
    private static final int HN_WEATHER = R.drawable.suncloud;
    private static final String HN_TEMP = "39 C";


    private static final String NAMDINH = "Nam Dinh";
    private static final int ND_WEATHER = R.drawable.weather;
    private static final String ND_TEMP = "-9 C";

    RelativeLayout haiphongLayout, namdinhLayout, hanamLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_main);
        initializeComponents();
        haiphongLayout.setOnClickListener(this);
        namdinhLayout.setOnClickListener(this);
        hanamLayout.setOnClickListener(this);
    }

    private void initializeComponents() {
        haiphongLayout = (RelativeLayout) findViewById(R.id.layout_haiphong);
        hanamLayout = (RelativeLayout) findViewById(R.id.layout_hanam);
        namdinhLayout = (RelativeLayout) findViewById(R.id.layout_namdinh);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,DetailActivity.class);
        switch (v.getId()) {
            case R.id.layout_haiphong:
                intent.putExtra("province",HAIPHONG);
                intent.putExtra("weather",HP_WEATHER);
                intent.putExtra("temp",HP_TEMP);
                break;
            case R.id.layout_hanam:
                intent.putExtra("province",HANAM);
                intent.putExtra("weather",HN_WEATHER);
                intent.putExtra("temp",HN_TEMP);
                break;
            case R.id.layout_namdinh:
                intent.putExtra("province",NAMDINH);
                intent.putExtra("weather",ND_WEATHER);
                intent.putExtra("temp",ND_TEMP);
                break;
        }
        startActivity(intent);
    }
}
