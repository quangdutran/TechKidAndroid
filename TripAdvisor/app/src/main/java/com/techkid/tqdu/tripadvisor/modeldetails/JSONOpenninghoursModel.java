package com.techkid.tqdu.tripadvisor.modeldetails;


import com.google.gson.annotations.SerializedName;

import java.util.List;
/**
 * Created by tqdu on 9/4/2016.
 */
public class JSONOpenninghoursModel {
    private static final String OPEN_NOW = "open_now";
    @SerializedName(OPEN_NOW)
    private Boolean open_now;

    private static final String WEEKDAY_TEXT = "weekday_text";
    @SerializedName(WEEKDAY_TEXT)
    private List<String> weekday_text;

    public Boolean getOpen_now() {
        return open_now;
    }

    public void setOpen_now(Boolean open_now) {
        this.open_now = open_now;
    }

    public List<String> getWeekday_text() {
        return weekday_text;
    }

    public void setWeekday_text(List<String> weekday_text) {
        this.weekday_text = weekday_text;
    }
}
