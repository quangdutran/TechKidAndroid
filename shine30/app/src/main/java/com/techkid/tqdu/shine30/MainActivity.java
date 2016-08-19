package com.techkid.tqdu.shine30;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.daimajia.slider.library.SliderLayout;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String API_MENU = "http://api.30shine.com/category/home";
    private static final String API_SLIDER = "http://api.30shine.com/slide/home";

    private SliderLayout mDemoSlider;
    private List<String> imageUrlList;
    private List<String> imageMenuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        DownloadJSON downloadJSON = new DownloadJSON();
        downloadJSON.execute(API_MENU);
    }

    private void initialize() {
        mDemoSlider = (SliderLayout)findViewById(R.id.slider);
        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
    }

    public class DownloadJSON extends AsyncTask<String, Void, String> {

        private String option = "";

        @Override
        protected String doInBackground(String... strings) {
            InputStream inputStream;
            HttpURLConnection httpURLConnection;
            String result = "";
            option = strings[0];

            try {

                String urlParameters  = "Id=1000";
                byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
                int    postDataLength = postData.length;
                URL url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput( true );
                httpURLConnection.setInstanceFollowRedirects( false );
                httpURLConnection.setRequestMethod( "POST" );
                httpURLConnection.setRequestProperty( "Content-Type", "application/json; charset=utf-8");
                //httpURLConnection.setRequestProperty( "charset", "utf-8");
                //httpURLConnection.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
                httpURLConnection.setUseCaches( false );
                try( DataOutputStream wr = new DataOutputStream( httpURLConnection.getOutputStream())) {
                    wr.write( postData );
                    wr.flush();
                    wr.close();
                }
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

                if (option.equals(API_MENU)){
                    JSONItemList jsonItemList = (new Gson()).fromJson(s, JSONItemList.class);
                    for (JSONItem jsonItem : jsonItemList.getJsonItems()) {
                        imageUrlList.add(jsonItem.getJsonThumbItem().getUrl());
                    }
                }
                else if (option.equals(API_SLIDER)) {
                    JSONItemHomeList jsonItemHomeList = (new Gson()).fromJson(s, JSONItemHomeList.class);
                    for (JSONItemHome jsonItemHome : jsonItemHomeList.getJsonItems()) {
                        imageUrlList.add(jsonItemHome.getJsonThumbItem().getUrl());
                    }
                }
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
