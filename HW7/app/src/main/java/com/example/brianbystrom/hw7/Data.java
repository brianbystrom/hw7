/*
Assignment #: In Class 05
File Name: Data.java
Group Members: Brian Bystrom
*/

package com.example.brianbystrom.hw7;

/**
 * Created by brianbystrom on 2/6/17.
 */

public class Data {
    String title, author, published_date, description, urlToImage, urlToMp3;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
