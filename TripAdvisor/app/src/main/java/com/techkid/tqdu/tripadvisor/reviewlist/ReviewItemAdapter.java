package com.techkid.tqdu.tripadvisor.reviewlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.techkid.tqdu.tripadvisor.R;
import com.techkid.tqdu.tripadvisor.modeldetails.JSONReviewsModel;

import java.util.List;

/**
 * Created by tqdu on 9/16/2016.
 */
public class ReviewItemAdapter extends BaseAdapter {

    private List<JSONReviewsModel> reviewList;
    private Context context;
    private LayoutInflater layoutInflater;

    public ReviewItemAdapter(Context context, List<JSONReviewsModel> reviewList) {
        this.context = context;
        this.reviewList = reviewList;
        layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return reviewList.size();
    }

    @Override
    public Object getItem(int i) {
        return reviewList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        JSONReviewsModel review = reviewList.get(i);
        View _view = view;
        if (review != null) {
            if (view == null) {
                _view = layoutInflater.inflate(R.layout.review_item, viewGroup, false);
            }
            ImageView userImage = (ImageView) _view.findViewById(R.id.user_image);
            TextView userReview = (TextView) _view.findViewById(R.id.user_review);
            TextView userName = (TextView) _view.findViewById(R.id.user_name);
            Picasso.with(context).load(review.getProfile_photo_url())
                    .placeholder(R.drawable.avatar)
                    .error(R.drawable.avatar)
                    .into(userImage);
            userReview.setText(review.getText());
            userName.setText(review.getAuthor_name() + " - " + "Rating: " + review.getRating());
        }
        return _view;
    }
}
