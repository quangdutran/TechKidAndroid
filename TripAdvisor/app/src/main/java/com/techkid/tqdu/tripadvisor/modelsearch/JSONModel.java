package com.techkid.tqdu.tripadvisor.modelsearch;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by phamhoang on 8/28/16.
 */
public class JSONModel {
    private static final String RESULTS = "results";
    @SerializedName(RESULTS)
    private List<JSONResultsModel> jsonResultsModelList;

    public List<JSONResultsModel> getJsonResultsModelList() {
        return jsonResultsModelList;
    }

    public void setJsonResultsModelList(List<JSONResultsModel> jsonResultsModelList) {
        this.jsonResultsModelList = jsonResultsModelList;
    }
}
