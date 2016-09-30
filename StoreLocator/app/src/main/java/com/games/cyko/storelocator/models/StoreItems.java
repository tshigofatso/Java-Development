package com.games.cyko.storelocator.models;

/**
 * Created by Cyko on 9/29/2016.
 */

public class StoreItems {

    //store items to occupy the list
    private String storeName;
    private String storeArea;
    private int imageResId;
    private boolean favourite = false;

    public int getAd_hoc_id() {
        return ad_hoc_id;
    }

    public void setAd_hoc_id(int ad_hoc_id) {
        this.ad_hoc_id = ad_hoc_id;
    }

    private int ad_hoc_id;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreArea() {
        return storeArea;
    }

    public void setStoreArea(String storeArea) {
        this.storeArea = storeArea;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}
