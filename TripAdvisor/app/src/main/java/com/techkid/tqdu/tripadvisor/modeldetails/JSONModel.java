package com.techkid.tqdu.tripadvisor.modeldetails;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tqdu on 9/4/2016.
 */
public class JSONModel {
    private static final String RESULT = "result";

    @SerializedName(RESULT)
    private JSONResultModel jsonResultModel;

    public JSONResultModel getJsonResultModel() {
        return jsonResultModel;
    }

    public void setJsonResultModel(JSONResultModel jsonResultModel) {
        this.jsonResultModel = jsonResultModel;
    }
}
