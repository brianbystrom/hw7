/*
Assignment #: In Class 05
File Name: Data.java
Group Members: Brian Bystrom
*/

package com.example.brianbystrom.hw7;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.Collections;
import java.util.Comparator;

/**
 * Created by brianbystrom on 2/6/17.
 */

public class Data implements Parcelable {
    String title, author, published_date, description, urlToImage, urlToMp3, duration;

    public int dateStrength;

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

  //  public void setPublished_date(String published_date) {


        //this.published_date = published_date;
    //}

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

    private int convertMonthToNumber(String s){
        if(s.equals("Jan")){
            return 1;
        }
        else  if(s.equals("Feb")){
            return 2;
        }
        else  if(s.equals("Mar")){
            return 3;
        }
        else  if(s.equals("Apr")){
            return 4;
        }
        else  if(s.equals("May")){
            return 5;
        }
        else  if(s.equals("Jun")){
            return 6;
        }
        else  if(s.equals("Jul")){
            return 7;
        }
        else  if(s.equals("Aug")){
            return 8;
        }
        else  if(s.equals("Sep")){
            return 9;
        }
        else  if(s.equals("Oct")){
            return 10;
        }
        else  if(s.equals("Nov")){
            return 11;
        }
        else  if(s.equals("Dec")){
            return 12;
        }
        Log.d("Couldnt find ", s);
        return 77;
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

    public void setPublished_date(String published_date) {
        //  Log.d("Date string",dateStrength); //Works, its retrieveing data
        this.published_date = published_date;
        int day = Integer.parseInt(published_date.substring(5,7));
        Log.d("POOP",published_date);
        int month = convertMonthToNumber(published_date.substring(8,11));
        int year = Integer.parseInt(published_date.substring(12,16));
       // Log.d("Day",day);
        //Log.d("Month",month);
        //Log.d("Year",year);
        // this.setDateStrength("1");
        if(day == 1){
            this.dateStrength = (day); //if its january just get the day
            //Log.d("dssa",this.dateStrength+"");
        }else{
            //Log.d("Dates are",month + "month" +day
            //      +"day");
            this.dateStrength = convertToDay(month,day,true);


        }





    }

    //Recursive method that will get the strength of every date
    public int convertToDay(int month,int days, boolean isJanuary){
        int daysInMonths[] = {31,28,31,30,31,30,31,31,30,31,30,31};
        if(month == 1 && isJanuary)
        {
            return days;
        }else if(month == 1){
            return 31;
        }
        else
            return days+=convertToDay(month-1,daysInMonths[month-1],false);
    }

//    @Override
//    public int compareTo(Object another) {
//        Data d = (Data) another;
//        if (this.dateStrength < d.dateStrength){
//            return -1;
//        }else{
//            return 1;
//        }
//    }
}



