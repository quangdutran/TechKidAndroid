package com.techkid.tqdu.weatherapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tqdu on 8/6/2016.
 */
public class JSONModel  {
    private static final String WEATHER = "weather";
    private static final String MAIN = "main";

    @SerializedName(WEATHER)
    private List<JSONWeatherModel> jsonWeatherModel;

    @SerializedName(MAIN)
    private  JSONMainModel jsonMainModel;

    public JSONMainModel getJsonMainModel() {
        return jsonMainModel;
    }

    public void setJsonMainModel(JSONMainModel jsonMainModel) {
        this.jsonMainModel = jsonMainModel;
    }

    public List<JSONWeatherModel> getJsonWeatherModel() {
        return jsonWeatherModel;
    }

    public void setJsonWeatherModel(List<JSONWeatherModel> jsonWeatherModel) {
        this.jsonWeatherModel = jsonWeatherModel;
    }
}
