package com.nest.scratch;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Editable;

import java.io.ByteArrayInputStream;

/**
 * Coded with love.
 * Created by black on 1/26/17.
 */

class Item {

    private String item_price;
    private String item_description;
    private String item_title;
    private Boolean share_item;
    private Bitmap[] images;

    Item(String title,String description,String price,Bitmap image[]){
        setItem_description(description);
        setItem_price(price);
        setItem_title(title);
        setImage(image);
    }



    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getItem_title() {
        return item_title;
    }

    public void setItem_title(String item_title) {
        this.item_title = item_title;
    }

    public Boolean getShare_item() {
        return share_item;
    }

    public void setShare_item(Boolean share_item) {
        this.share_item = share_item;
    }

    public Bitmap getImage(int index) {
      return images[index];
    }

    public Bitmap[] getImages(){
        return images;
    }

    public void setImage(Bitmap[] image) {
        this.images = image;
    }
}
