package com.example.i329392.myapplication;

/**
 * Created by i329392 on 27/01/18.
 */

public class TextData {
    String title;
    String text;

    public TextData(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
