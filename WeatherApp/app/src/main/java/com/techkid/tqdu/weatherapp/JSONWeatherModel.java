package com.techkid.tqdu.weatherapp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tqdu on 8/6/2016.
 */
public class JSONWeatherModel {
    private static final String MAIN = "main";
    private static final String DESCRIPTION = "description";

    @SerializedName(MAIN)
    private String main;

    @SerializedName(DESCRIPTION)
    private String description;

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
