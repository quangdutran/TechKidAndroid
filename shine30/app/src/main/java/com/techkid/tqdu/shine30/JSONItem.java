package com.techkid.tqdu.shine30;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tqdu on 8/7/2016.
 */
public class JSONItem {

    @SerializedName("id")
    private int id;

    @SerializedName("thumb")
    private JSONThumbItem jsonThumbItem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JSONThumbItem getJsonThumbItem() {
        return jsonThumbItem;
    }

    public void setJsonThumbItem(JSONThumbItem jsonThumbItem) {
        this.jsonThumbItem = jsonThumbItem;
    }
}
