package com.techkid.tqdu.weatherapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String API_KEY = "201b3849bd1975ca152b20fb7f3d1346";
    public static final String API = "http://api.openweathermap.org/data/2.5/weather";

    public static TextView txtCity;
    public static TextView txtMain;
    public static TextView txtTemp;
    public static TextView txtPressure;
    public static TextView txtHumidity;
    public static ImageView weatherImg;
    ImageButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DownloadJSON downloadJSON = new DownloadJSON();
        init();
        Intent intent = getIntent();
        String city = intent.getStringExtra("city");
        if (city == null || city.isEmpty()) {
            downloadJSON.execute(API + "?q=Hanoi&&APPID=" + API_KEY);
            MainActivity.txtCity.setText("Ha Noi");
        } else {
            downloadJSON.execute(API + "?q=" + city + "&&APPID=" + API_KEY);
            MainActivity.txtCity.setText(city);
        }


    }

    private void init() {
        txtCity = (TextView) findViewById(R.id.city);
        txtMain = (TextView) findViewById(R.id.weather_des);
        txtTemp = (TextView) findViewById(R.id.temparature);
        txtPressure = (TextView) findViewById(R.id.txt_pressure);
        txtHumidity = (TextView) findViewById(R.id.txt_humidity);
        weatherImg = (ImageView) findViewById(R.id.weather_image);
        btnAdd = (ImageButton) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public class DownloadJSON extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            InputStream inputStream;
            HttpURLConnection httpURLConnection;
            String result = "";

            try {
                URL url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String inputString;
                    while ((inputString = bufferedReader.readLine()) != null) {
                        stringBuilder.append(inputString);
                    }
                    result = stringBuilder.toString();
                    httpURLConnection.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            //Log.d("JSON", s + ""); //In case s is null
            if (s != null) {
                JSONModel jsonModel = (new Gson()).fromJson(s, JSONModel.class);
                Log.d("Main: ", jsonModel.getJsonWeatherModel().get(0).getMain());
                Log.d("Des: ", jsonModel.getJsonWeatherModel().get(0).getDescription());

                Log.d("Pressure: ", jsonModel.getJsonMainModel().getHumidity() + "");
                float kelvin = Float.parseFloat(jsonModel.getJsonMainModel().getTemp() + "");
                // Kelvin to Degree Celsius Conversion
                float celsius = kelvin - 273.15F;
                int cTemp = Float.valueOf(celsius).intValue();
                Log.d("Temp: ", cTemp + "");
                txtTemp.setText(cTemp + "");
                txtPressure.setText(jsonModel.getJsonMainModel().getPressure() + "");
                txtHumidity.setText(jsonModel.getJsonMainModel().getHumidity() + "");
                if (jsonModel.getJsonWeatherModel().get(0).getMain().contains("sun"))
                    weatherImg.setImageResource(R.drawable.sun_xxl);
                else if (jsonModel.getJsonWeatherModel().get(0).getMain().contains("cloud"))
                    weatherImg.setImageResource(R.drawable.cloud);
                else if (jsonModel.getJsonWeatherModel().get(0).getMain().contains("rain"))
                    weatherImg.setImageResource(R.drawable.rainy_day);
                else if (jsonModel.getJsonWeatherModel().get(0).getMain().contains("wind"))
                    weatherImg.setImageResource(R.drawable.wind);
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
