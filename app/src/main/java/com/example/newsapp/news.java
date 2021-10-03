package com.example.newsapp;

public class news {
      String discription;
     String title;

    String url;
    String imageTourl;

    public news(String title, String description, String url, String imageurl) {
        this.title = title;
        this.discription = description;
        this.url = url;
        this.imageTourl = imageurl;
    }
}
