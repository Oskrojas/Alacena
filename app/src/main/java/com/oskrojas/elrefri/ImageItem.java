package com.oskrojas.elrefri;

import android.graphics.Bitmap;

/**
 * Created by oskrojas on 4/5/15.
 */
public class ImageItem {
    private Bitmap image;
    private String title;
    private String price;

    public ImageItem(Bitmap image, String title, String price) {
        super();
        this.image = image;
        this.title = title;
        this.price = price;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}