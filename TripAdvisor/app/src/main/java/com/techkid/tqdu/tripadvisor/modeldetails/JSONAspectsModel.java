package com.techkid.tqdu.tripadvisor.modeldetails;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tqdu on 9/4/2016.
 */
public class JSONAspectsModel {
    private static final String RATING = "rating";
    @SerializedName(RATING)
    private int rating;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
