package com.techkid.tqdu.tripadvisor.modelsearch;

import com.google.gson.annotations.SerializedName;

/**
 * Created by phamhoang on 8/28/16.
 */
public class JSONGeometryModel {
    private static final String LOCATION = "location";

    @SerializedName(LOCATION)
    private JSONGeometryModelLocation jsonGeometryModelLocation;

    public JSONGeometryModel(JSONGeometryModelLocation jsonGeometryModelLocation) {
        this.jsonGeometryModelLocation = jsonGeometryModelLocation;
    }

    public JSONGeometryModelLocation getJsonGeometryModelLocation() {
        return jsonGeometryModelLocation;
    }

    public void setJsonGeometryModelLocation(JSONGeometryModelLocation jsonGeometryModelLocation) {
        this.jsonGeometryModelLocation = jsonGeometryModelLocation;
    }

}
