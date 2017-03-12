/*
Assignment #: In Class 05
File Name: Data.java
Group Members: Brian Bystrom
*/

package com.example.brianbystrom.hw7;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by brianbystrom on 2/6/17.
 */

public class Data implements Parcelable {
    String title, author, published_date, description, urlToImage, urlToMp3, duration;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getUrlToMp3() {
        return urlToMp3;
    }

    public void setUrlToMp3(String urlToMp3) {
        this.urlToMp3 = urlToMp3;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public Data() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(published_date);
        dest.writeString(description);
        dest.writeString(urlToImage);
        dest.writeString(urlToMp3);
        dest.writeString(duration);

    }

    public static final Parcelable.Creator<Data> CREATOR
            = new Parcelable.Creator<Data>() {
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    private Data(Parcel in) {
        this.title = in.readString();
        this.author = in.readString();
        this.published_date = in.readString();
        this.description = in.readString();
        this.urlToImage = in.readString();
        this.urlToMp3 = in.readString();
        this.duration =in.readString();
    }
}



