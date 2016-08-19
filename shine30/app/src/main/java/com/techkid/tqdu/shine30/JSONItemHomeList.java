package com.techkid.tqdu.shine30;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tqdu on 8/16/2016.
 */
public class JSONItemHomeList {
    @SerializedName("d")
    private List<JSONItemHome> jsonItems;

    public List<JSONItemHome> getJsonItems() {
        return jsonItems;
    }

    public void setJsonItems(List<JSONItemHome> jsonItems) {
        this.jsonItems = jsonItems;
    }
}
