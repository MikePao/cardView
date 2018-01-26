package com.example.i329392.myapplication;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by i329392 on 26/01/18.
 */

public class NasaLoader extends AsyncTaskLoader<List<CardData>>{
    String url = "";

    public NasaLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    public List<CardData> loadInBackground() {
        return Utils.fetchData(url);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
