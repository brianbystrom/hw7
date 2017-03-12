/*
Assignment #: In Class 05
File Name: GetNewsAsync.java
Group Members: Brian Bystrom
*/

package com.example.brianbystrom.hw7;

import android.os.AsyncTask;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by brianbystrom on 2/6/17.
 */

public class GetNewsAsync extends AsyncTask<String, Void, ArrayList<Data>> {

    IData activity;

    ProgressBar pb;

    public GetNewsAsync(IData activity) {
        this.activity = activity;
    }

    @Override
    protected ArrayList<Data> doInBackground(String... params) {

        BufferedReader reader = null;

        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode = con.getResponseCode();
            if(statusCode == HttpURLConnection.HTTP_OK) {
                InputStream in = con.getInputStream();
                return DataUtil.DataSAXParser.parseData(in);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(ArrayList<Data> s) {
        super.onPostExecute(s);


        activity.setupData(s);


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    static public interface IData {
        public void setupData(ArrayList<Data> s);
    }
}

