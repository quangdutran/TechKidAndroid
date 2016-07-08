package com.tqdu.session3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView provinceTxt, provinceTemp;
    ImageView provinceWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        provinceTxt = (TextView) findViewById(R.id.province_txt);
        provinceTemp = (TextView) findViewById(R.id.province_temp_num);
        provinceWeather = (ImageView) findViewById(R.id.province_weather_logo);
        Bundle extras = getIntent().getExtras();

        provinceTxt.setText(extras.getString("province"));
        provinceTemp.setText(extras.getString("temp"));
        provinceWeather.setImageResource(extras.getInt("weather"));
        /*Log.d("DetailAct", extras.getString("province"));
        Log.d("DetailAct", ((Integer) extras.getInt("weather")).toString());
        Log.d("DetailAct", extras.getString("temp","abc"));*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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
}
