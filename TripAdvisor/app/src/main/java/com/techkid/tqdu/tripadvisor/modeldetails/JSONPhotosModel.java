package com.techkid.tqdu.tripadvisor.modeldetails;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tqdu on 9/4/2016.
 */
public class JSONPhotosModel {
    private static final String HEIGHT = "height";
    private static final String WIDTH = "width";
    private static final String PHOTO_REFERENCE = "photo_reference";
    @SerializedName(HEIGHT)
    private int height;
    @SerializedName(WIDTH)
    private int width;
    @SerializedName(PHOTO_REFERENCE)
    private String photo_reference;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getPhoto_reference() {
        return photo_reference;
    }

    public void setPhoto_reference(String photo_reference) {
        this.photo_reference = photo_reference;
    }
}
