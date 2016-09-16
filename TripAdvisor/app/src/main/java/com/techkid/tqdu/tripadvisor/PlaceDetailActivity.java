package com.techkid.tqdu.tripadvisor;

import android.location.Location;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.techkid.tqdu.tripadvisor.modelsearch.JSONModel;
import com.techkid.tqdu.tripadvisor.modelsearch.JSONResultsModel;
import com.techkid.tqdu.tripadvisor.searchresult.SearchResultAdapter;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class PlaceDetailActivity extends AppCompatActivity {


    private ImageView bigPhoto;
    private ImageView smallPhoto1;
    private ImageView smallPhoto2;
    TextView placeName;
    TextView distance;
    ImageView rating;
    TextView openNow;
    TextView placeInfo;
    TextView placeTelNum;
    TextView placeEmail;
    LinearLayout imageContainer;
    ListView placeReview;

    private boolean doneLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        initialize();

        JSONResultsModel jsonResultsModel = (JSONResultsModel) getIntent().getSerializableExtra("Place");
        if (jsonResultsModel == null) {
            Toast.makeText(this,"Could not get data for this place",Toast.LENGTH_SHORT).show();
            return;
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        /*String getPlaceDetailLink = "https://maps.googleapis.com/maps/api/place/details/json?placeid=" +
                "ChIJN1t_tDeuEmsRUsoyG83frY4" +
                "&key=YOUR_API_KEY";
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        DownloadJSON downloadJSON = new DownloadJSON();
        downloadJSON.execute(API_LINK);*/
    }

    private void initialize() {
        bigPhoto = (ImageView) findViewById(R.id.big_photo);
        smallPhoto1 = (ImageView) findViewById(R.id.small_photo1);
        smallPhoto2 = (ImageView) findViewById(R.id.small_photo2);
        placeName = (TextView) findViewById(R.id.place_name);
        distance = (TextView) findViewById(R.id.distance);
        rating = (ImageView) findViewById(R.id.rating_icon);
        openNow = (TextView) findViewById(R.id.open_now);
        placeInfo = (TextView) findViewById(R.id.place_info);
        placeTelNum = (TextView) findViewById(R.id.place_tel_number);
        placeEmail = (TextView) findViewById(R.id.place_email_address);
        imageContainer = (LinearLayout) findViewById(R.id.image_container);
        placeReview = (ListView) findViewById(R.id.review_list);
    }

    public class DownloadJSON extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            InputStream inputStream;
            HttpsURLConnection httpsURLConnection;
            String result = "";

            try {
                URL url = new URL(strings[0]);
                httpsURLConnection = (HttpsURLConnection) url.openConnection();
                if (httpsURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    inputStream = new BufferedInputStream(httpsURLConnection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String inputString;
                    while ((inputString = bufferedReader.readLine()) != null) {
                        stringBuilder.append(inputString);
                    }
                    result = stringBuilder.toString();
                    httpsURLConnection.disconnect();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s != null) {
                JSONModel jsonModel = (new Gson()).fromJson(s, JSONModel.class);
                if (jsonModel == null) {
                    doneLoading = true;
                    Toast.makeText(PlaceDetailActivity.this,"Could not get data due to poor connectivity", Toast.LENGTH_SHORT).show();
                    return;
                }

                doneLoading = true;
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
