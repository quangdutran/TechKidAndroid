package com.techkid.tqdu.shine30;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tqdu on 8/7/2016.
 */
public class JSONItemList {
    @SerializedName("d")
    private List<JSONItem> jsonItems;

    public List<JSONItem> getJsonItems() {
        return jsonItems;
    }

    public void setJsonItems(List<JSONItem> jsonItems) {
        this.jsonItems = jsonItems;
    }
}
