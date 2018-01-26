package com.example.i329392.myapplication;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by i329392 on 26/01/18.
 */

public final class Utils {

    private Utils(){}

    public static List<CardData> fetchData(String requestUrl){
        String jsonResponse = null;
        List<CardData> data = null;

        if(requestUrl == "" ){
            return null;
        }

        try {
            URL url = createUrl(requestUrl);
            jsonResponse = makeHttpRequest(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }

        data = extractDataFromJSON(jsonResponse);


        return data;
    }

    private static List<CardData> extractDataFromJSON(String jsonResponse) {
        if(jsonResponse == null || jsonResponse == ""){
            return null;
        }
        List<CardData> data = new ArrayList<CardData>();

        try {
//            JSONObject response = new JSONObject(jsonResponse);
//            JSONArray resJSON = response.getJSONArray("hits");
//            for(int i = 0; i < resJSON.length(); i++ ){
//                data.add(new CardData(
//                        resJSON.getJSONObject(i).getString("tags"),
//                        resJSON.getJSONObject(i).getString("previewURL")));
                JSONArray resultArray = new JSONArray(jsonResponse);
                for(int i = 0; i < resultArray.length(); i++ ){
                    JSONObject urls = resultArray.getJSONObject(i).getJSONObject("urls");
                    data.add(new CardData(
                            resultArray.getJSONObject(i).getString("id"),
                            urls.getString("small")
                    ));
                }


        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return data;
    }

    private static String makeHttpRequest(URL url) {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        if(url == null){
            return null;
        }

        try{
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

                jsonResponse = readStream(urlConnection);

        }
        catch (IOException e){
            e.printStackTrace();
        }

        return jsonResponse;
    }

    private static String readStream(HttpURLConnection urlConnection) {
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        InputStream is;
        StringBuilder output = new StringBuilder();

        try{
            is = urlConnection.getInputStream();
            inputStreamReader = new InputStreamReader(is, Charset.forName("UTF-8"));
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while((line = bufferedReader.readLine()) != null)
                output.append(line);
        }catch (IOException e){
            e.printStackTrace();
        }

        return output.toString();
    }

    private static URL createUrl(String requestUrl) throws MalformedURLException {
        URL url = null;
        try{
            url = new URL(requestUrl);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        return url;
    }

}


