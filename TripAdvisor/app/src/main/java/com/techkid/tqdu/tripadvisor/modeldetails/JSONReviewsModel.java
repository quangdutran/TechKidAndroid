package com.techkid.tqdu.tripadvisor.modeldetails;

import com.google.gson.annotations.SerializedName;

/**
 * Created by phamhoang on 8/28/16.
 */
public class JSONReviewsModel {

    private static final String ASPECTS = "aspects";
    @SerializedName(ASPECTS)
    private JSONAspectsModel jsonAspectsModel;

    private static final String AUTHOR_NAME = "author_name";
    @SerializedName(AUTHOR_NAME)
    private String author_name;

    private static final String PROFILE_PHOTO_URL = "profile_photo_url";
    @SerializedName(PROFILE_PHOTO_URL)
    private String profile_photo_url;

    private static final String RATING = "rating";
    @SerializedName(RATING)
    private int rating;

    private static final String TEXT = "text";
    @SerializedName(TEXT)
    private String text;

    public JSONAspectsModel getJsonAspectsModel() {
        return jsonAspectsModel;
    }

    public void setJsonAspectsModel(JSONAspectsModel jsonAspectsModel) {
        this.jsonAspectsModel = jsonAspectsModel;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getProfile_photo_url() {
        return profile_photo_url;
    }

    public void setProfile_photo_url(String profile_photo_url) {
        this.profile_photo_url = profile_photo_url;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
