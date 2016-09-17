package com.techkid.tqdu.tripadvisor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.techkid.tqdu.tripadvisor.modeldetails.JSONModel;
import com.techkid.tqdu.tripadvisor.modeldetails.JSONReviewsModel;
import com.techkid.tqdu.tripadvisor.reviewlist.ReviewItemAdapter;
import com.techkid.tqdu.tripadvisor.searchresult.SearchResultAdapter;
import com.techkid.tqdu.tripadvisor.utility.Utility;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

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
    private boolean continuePullData = true;

    String splaceId;
    String splaceName;
    double srate;
    double sdistance;
    double lat;
    double lng;

    JSONModel jsonModel;

    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);

        splaceId = getIntent().getStringExtra(SearchResultAdapter.PLACE_ID);
        splaceName = getIntent().getStringExtra(SearchResultAdapter.PLACE_NAME);
        srate = getIntent().getDoubleExtra(SearchResultAdapter.PLACE_RATE, 0.0);
        sdistance = getIntent().getDoubleExtra(SearchResultAdapter.PLACE_DIS, 0.0);
        lat = getIntent().getDoubleExtra(SearchResultAdapter.PLACE_LAT,0.0);
        lng = getIntent().getDoubleExtra(SearchResultAdapter.PLACE_LNG,0.0);
        if (splaceId.isEmpty()) {
            continuePullData = false;
            Toast.makeText(this, "Could not get data for this place", Toast.LENGTH_SHORT).show();
            return;
        }
        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(false);
        progressBar.setMessage("Loading");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setIndeterminateDrawable(getResources().getDrawable(R.drawable.progressbar_custom));
        progressBar.show();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (doneLoading == false) {

                        }
                        progressBar.dismiss();
                    }
                }
        ).start();
        initialize();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (continuePullData) {
            String getPlaceDetailLink = "https://maps.googleapis.com/maps/api/place/details/json?placeid=" +
                    splaceId +
                    "&key=" + Utility.GOOGLE_API_KEY;
            DownloadJSON downloadJSON = new DownloadJSON();
            downloadJSON.execute(getPlaceDetailLink);
        }
    }

    private void initialize() {
        bigPhoto = (ImageView) findViewById(R.id.big_photo);
        smallPhoto1 = (ImageView) findViewById(R.id.small_photo1);
        smallPhoto2 = (ImageView) findViewById(R.id.small_photo2);
        placeName = (TextView) findViewById(R.id.place_name_address);
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
                jsonModel = (new Gson()).fromJson(s, JSONModel.class);
                if (jsonModel == null) {
                    doneLoading = true;
                    Toast.makeText(PlaceDetailActivity.this, "Could not get data due to poor connectivity", Toast.LENGTH_SHORT).show();
                    return;
                }
                fillPhotos();
                fillData();
                setOnClick();
                doneLoading = true;
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    private void fillPhotos() {
        String linkPhoto = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=";
        String googleKey = "&key=" + Utility.GOOGLE_API_KEY;
        if (jsonModel.getJsonResultModel() != null
                && jsonModel.getJsonResultModel().getJsonPhotosModel().size() > 0) {
            Picasso.with(this).load(linkPhoto + jsonModel.getJsonResultModel().getJsonPhotosModel().get(0).getPhoto_reference() + googleKey)
                    .placeholder(R.drawable.ic_error_owl)
                    .error(R.drawable.ic_error_owl)
                    .into(bigPhoto);
            if (jsonModel.getJsonResultModel().getJsonPhotosModel().size() > 1)
                Picasso.with(this).load(linkPhoto + jsonModel.getJsonResultModel().getJsonPhotosModel().get(1).getPhoto_reference() + googleKey)
                        .placeholder(R.drawable.ic_error_owl)
                        .error(R.drawable.ic_error_owl)
                        .into(smallPhoto1);
            if (jsonModel.getJsonResultModel().getJsonPhotosModel().size() > 2)
                Picasso.with(this).load(linkPhoto + jsonModel.getJsonResultModel().getJsonPhotosModel().get(2).getPhoto_reference() + googleKey)
                        .placeholder(R.drawable.ic_error_owl)
                        .error(R.drawable.ic_error_owl)
                        .into(smallPhoto2);
        }
        for (int i = 3; i < jsonModel.getJsonResultModel().getJsonPhotosModel().size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setId(i);
            imageView.setPadding(2, 2, 2, 2);
            Picasso.with(this).load(linkPhoto + jsonModel.getJsonResultModel().getJsonPhotosModel().get(i).getPhoto_reference() + googleKey)
                    .placeholder(R.drawable.ic_error_owl)
                    .error(R.drawable.ic_error_owl)
                    .into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageContainer.addView(imageView);
        }
    }

    private void fillData() {
        placeName.setText(splaceName + jsonModel.getJsonResultModel().getFormatted_address());
        distance.setText(sdistance + " km from here");
        double rate = srate;
        if (rate < 1.5) {
            rating.setImageResource(R.drawable.ta_rating_1_small);
        } else if (rate > 1.5 && rate < 2) {
            rating.setImageResource(R.drawable.ta_rating_1h_small);
        } else if (rate >= 2 && rate < 2.5) {
            rating.setImageResource(R.drawable.ta_rating_2_small);
        } else if (rate >= 2.5 && rate < 3) {
            rating.setImageResource(R.drawable.ta_rating_2h_small);
        } else if (rate >= 3 && rate < 3.5) {
            rating.setImageResource(R.drawable.ta_rating_3_small);
        } else if (rate >= 3.5 && rate < 4) {
            rating.setImageResource(R.drawable.ta_rating_3h_small);
        } else if (rate >= 4 && rate < 4.5) {
            rating.setImageResource(R.drawable.ta_rating_4_small);
        } else if (rate >= 4.5 && rate < 5) {
            rating.setImageResource(R.drawable.ta_rating_4h_small);
        } else {
            rating.setImageResource(R.drawable.ta_rating_5_small);
        }
        if (jsonModel.getJsonResultModel().getJsonOpenninghoursModel() != null) {
            boolean open = jsonModel.getJsonResultModel().getJsonOpenninghoursModel().getOpen_now();
            if (open)
                openNow.setText("Opened");
            else openNow.setText("Closed");
        }
        placeTelNum.setText(jsonModel.getJsonResultModel().getInternational_phone_number());
        placeEmail.setText(jsonModel.getJsonResultModel().getWebsite());
        List<JSONReviewsModel> reviewList = jsonModel.getJsonResultModel().getJsonReviewsModelList();
        ReviewItemAdapter reviewItemAdapter = new ReviewItemAdapter(this,reviewList);
        placeReview.setAdapter(reviewItemAdapter);
    }

    private void setOnClick(){
        placeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double latitude = lat;
                double longitude = lng;
                String label = "It is here";
                String uriBegin = "geo:" + latitude + "," + longitude;
                String query = latitude + "," + longitude + "(" + label + ")";
                String encodedQuery = Uri.encode(query);
                String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                Uri uri = Uri.parse(uriString);
                Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, uri);
                startActivity(mapIntent);
            }
        });
        placeTelNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "tel:" + jsonModel.getJsonResultModel().getFormatted_phone_number().trim() ;
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            }
        });
        placeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = jsonModel.getJsonResultModel().getWebsite();
                if (!url.isEmpty()) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            }
        });
    }
}
