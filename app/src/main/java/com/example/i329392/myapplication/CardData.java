package com.example.i329392.myapplication;

/**
 * Created by i329392 on 26/01/18.
 */

public class CardData {
    String text;
    String photoUrl;

    public CardData(String text, String photoUrl) {
        this.text = text;
        this.photoUrl = photoUrl;
    }

    public String getText() {
        return text;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
