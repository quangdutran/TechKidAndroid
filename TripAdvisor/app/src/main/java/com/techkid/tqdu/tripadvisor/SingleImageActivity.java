package com.techkid.tqdu.tripadvisor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

public class SingleImageActivity extends AppCompatActivity {

    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_image);
        overridePendingTransition(R.anim.trans_back_in,R.anim.trans_back_out);
        imageView = (ImageView) findViewById(R.id.imageView);
//        imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//        imageView.setAdjustViewBounds(true);
        String picUrl = getIntent().getStringExtra("pic_url");
        Picasso.with(this).load(picUrl)
                .placeholder(R.drawable.ic_error_owl)
                .error(R.drawable.ic_error_owl)
                .into(imageView);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_back_in,R.anim.trans_back_out);
    }
}
