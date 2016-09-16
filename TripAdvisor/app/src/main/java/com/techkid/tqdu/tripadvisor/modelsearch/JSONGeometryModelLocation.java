package com.techkid.tqdu.tripadvisor.modelsearch;

import com.google.gson.annotations.SerializedName;

/**
 * Created by phamhoang on 8/28/16.
 */
public class JSONGeometryModelLocation {
    private static final String LAT = "lat";
    private static final String LNG = "lng";

    @SerializedName(LAT)
    private double lat;
    @SerializedName(LNG)
    private double lng;

    public JSONGeometryModelLocation(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
