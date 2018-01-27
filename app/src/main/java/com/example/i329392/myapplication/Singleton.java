package com.example.i329392.myapplication;

import java.util.List;

/**
 * Created by i329392 on 27/01/18.
 */

public final class Singleton {
    public static List<TextData> textList;
    public static List<CardData> photoList;
    public static List<TextData> title;
    private Singleton() {

    }

    public static List<TextData> getTextList(){
        System.out.println("INSIDE SINGLETON GET TEXT LIST");
        return textList;
    }
    public static List<CardData> getPhototList(){
        System.out.println("INSIDE SINGLETON GET PHOTO LIST");
        return photoList;
    }

    public static List<TextData> getTitle() {
        System.out.println("INSIDE SINGLETON GET TITLE");
        return title;
    }
}
