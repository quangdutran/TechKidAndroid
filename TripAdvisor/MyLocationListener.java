package com.techkid.tqdu.tripadvisor;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by tqdu on 9/11/2016.
 */
public class MyLocationListener implements LocationListener {

    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            Log.d("Lat:",location.getLatitude() + "");
            Log.d("Long:",location.getLongitude() + "");
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
