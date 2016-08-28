package com.techkid.tqdu.weatherapp;

import android.app.ActivityManager;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tqdu on 8/6/2016.
 */
public class JSONMainModel {
    private static final String TEMP = "temp";
    private static final String PRESSURE = "pressure";
    private static final String HUMIDITY = "humidity";

    @SerializedName(TEMP)
    private double temp;

    @SerializedName(PRESSURE)
    private double pressure;

    @SerializedName(HUMIDITY)
    private int humidity;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
