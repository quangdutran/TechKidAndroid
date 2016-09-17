package com.techkid.tqdu.tripadvisor.modelsearch;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by phamhoang on 8/28/16.
 */
public class JSONResultsModel implements Serializable{
    private static final String GEOMETRY = "geometry";
    private static final String ICON = "icon";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PHOTOS = "photos";
    private static final String PLACE_ID = "place_id";
    private static final String RATING = "rating";
    private static final String REFERENCE = "reference";
    private static final String VICINITY = "vicinity";
    @SerializedName(GEOMETRY)
    private JSONGeometryModel jsonGeometryModel;
    @SerializedName(ICON)
    private String icon;
    @SerializedName(ID)
    private String id;
    @SerializedName(NAME)
    private String name;
    @SerializedName(PHOTOS)
    private List<JSONPhotosModel> jsonPhotosModelList;
    @SerializedName(PLACE_ID)
    private String place_id;
    @SerializedName(REFERENCE)
    private String reference;
    @SerializedName(RATING)
    private Double rating;
    @SerializedName(VICINITY)
    private String vicinity;

    private double distance;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public JSONResultsModel(JSONGeometryModel jsonGeometryModel, String icon, String id,
                            String name, List<JSONPhotosModel> jsonPhotosModelList, String place_id,
                            String reference, Double rating, String vicinity) {
        this.jsonGeometryModel = jsonGeometryModel;
        this.icon = icon;
        this.id = id;
        this.name = name;
        this.jsonPhotosModelList = jsonPhotosModelList;
        this.place_id = place_id;
        this.reference = reference;
        this.rating = rating;
        this.vicinity = vicinity;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONGeometryModel getJsonGeometryModel() {
        return jsonGeometryModel;
    }

    public void setJsonGeometryModel(JSONGeometryModel jsonGeometryModel) {
        this.jsonGeometryModel = jsonGeometryModel;
    }

    public List<JSONPhotosModel> getJsonPhotosModelList() {
        return jsonPhotosModelList;
    }

    public void setJsonPhotosModelList(List<JSONPhotosModel> jsonPhotosModelList) {
        this.jsonPhotosModelList = jsonPhotosModelList;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }
}
