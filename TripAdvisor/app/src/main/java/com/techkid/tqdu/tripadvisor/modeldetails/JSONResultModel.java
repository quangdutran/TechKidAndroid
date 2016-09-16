package com.techkid.tqdu.tripadvisor.modeldetails;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by phamhoang on 8/28/16.
 */
public class JSONResultModel {

    private static final String FORMATTED_ADDRESS = "formatted_address";
    @SerializedName(FORMATTED_ADDRESS)
    private String formatted_address;

    private static final String FORMATTED_PHONE_NUMBER = "formatted_phone_number";
    @SerializedName(FORMATTED_PHONE_NUMBER)
    private String formatted_phone_number;

    private static final String ICON = "icon";
    @SerializedName(ICON)
    private String icon;

    private static final String ID = "id";
    @SerializedName(ID)
    private String id;

    private static final String INTERNATIONAL_PHONE_NUMBER = "international_phone_number";
    @SerializedName(INTERNATIONAL_PHONE_NUMBER)
    private String international_phone_number;

    private static final String OPENING_HOURS = "opening_hours";
    @SerializedName(OPENING_HOURS)
    private JSONOpenninghoursModel jsonOpenninghoursModel;

    private static final String PHOTOS = "photos";
    @SerializedName(PHOTOS)
    private JSONPhotosModel jsonPhotosModel;

    private static final String PLACE_ID = "place_id";
    @SerializedName(PLACE_ID)
    private String place_id;

    private static final String REVIEWS = "reviews";
    @SerializedName(REVIEWS)
    private List<JSONReviewsModel> jsonReviewsModelList;

    private static final String VICINITY = "vicinity";
    @SerializedName(VICINITY)
    private String vicinity;

    private static final String URL = "url";
    @SerializedName(URL)
    private String url;

    private static final String WEBSITE = "website";
    @SerializedName(WEBSITE)
    private String website;

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getFormatted_phone_number() {
        return formatted_phone_number;
    }

    public void setFormatted_phone_number(String formatted_phone_number) {
        this.formatted_phone_number = formatted_phone_number;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInternational_phone_number() {
        return international_phone_number;
    }

    public void setInternational_phone_number(String international_phone_number) {
        this.international_phone_number = international_phone_number;
    }

    public JSONOpenninghoursModel getJsonOpenninghoursModel() {
        return jsonOpenninghoursModel;
    }

    public void setJsonOpenninghoursModel(JSONOpenninghoursModel jsonOpenninghoursModel) {
        this.jsonOpenninghoursModel = jsonOpenninghoursModel;
    }

    public JSONPhotosModel getJsonPhotosModel() {
        return jsonPhotosModel;
    }

    public void setJsonPhotosModel(JSONPhotosModel jsonPhotosModel) {
        this.jsonPhotosModel = jsonPhotosModel;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public List<JSONReviewsModel> getJsonReviewsModelList() {
        return jsonReviewsModelList;
    }

    public void setJsonReviewsModelList(List<JSONReviewsModel> jsonReviewsModelList) {
        this.jsonReviewsModelList = jsonReviewsModelList;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
