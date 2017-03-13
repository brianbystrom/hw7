/*
Assignment #: Homework 07
File Name: PlayPodcastAsync.java
Group Members: Brian Bystrom, Mohamed Salad
*/

package com.example.brianbystrom.hw7;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by brianbystrom on 2/6/17.
 */

public class PlayPodcastAsync extends AsyncTask<String, Void, MediaPlayer> {

    IData activity;

    public PlayPodcastAsync(IData activity) {
        this.activity = activity;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    int duration = 0;
    int current = 0;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(MediaPlayer mediaPlayer) {
        super.onPostExecute(mediaPlayer);
        activity.playPodcast(mediaPlayer);


    }

    @Override
    protected MediaPlayer doInBackground(String... params) {

        String url = "http://programmerguru.com/android-tutorial/wp-content/uploads/2013/04/hosannatelugu.mp3";


        //String url = "https://play.podtrac.com/npr-510298/npr.mc.tritondigital.com/NPR_510298/media/anon.npr-mp3/npr/ted/2017/03/20170310_ted_tedpod.mp3?orgId=1&d=3171&p=510298&story=519250894&t=podcast&e=519250894&ft=pod&f=510298"; // your URL here
        final MediaPlayer mPlayer = new MediaPlayer();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        //audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 20, 0);

        try {
            mPlayer.setDataSource(params[0]);
        } catch (IllegalArgumentException e) {
        } catch (SecurityException e) {
        } catch (IllegalStateException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        // might take long! (for buffering, etc)

        try {
            mPlayer.prepare();
        } catch (IllegalStateException e) {
        } catch (IOException e) {
        }


        return mPlayer;
    }

    static public interface IData {
        public void playPodcast(MediaPlayer mPlayer);
    }
}
