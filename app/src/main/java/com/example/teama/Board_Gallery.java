package com.example.teama;

public class Board_Gallery {

    private String Title;
    private String Url ;
    private int Thumbnail ;

    public Board_Gallery() {
    }

    public Board_Gallery(String title, String url, int thumbnail) {
        Title = title;
        Url = url;
        Thumbnail = thumbnail;
    }


    public String getTitle() {
        return Title;
    }

    public String getUrl() {
        return Url;
    }

    public int getThumbnail() {
        return Thumbnail;
    }


    public void setTitle(String title) {
        Title = title;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}