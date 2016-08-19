package com.techkid.tqdu.shine30;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tqdu on 8/7/2016.
 */
public class JSONThumbItem {

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @SerializedName("url")

    private String url;
}
