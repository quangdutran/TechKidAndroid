package com.techkid.tqdu.tripadvisor;

/**
 * Created by tqdu on 7/28/2016.
 */
public class ItemSearch {
    private int image;
    private String name;

    public ItemSearch(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
