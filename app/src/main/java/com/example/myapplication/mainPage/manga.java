package com.example.myapplication.mainPage;

public class manga {
    private String id;
    private String title;
    private String description;
    private int publishYear;
    private String author;
    private String artist;
    private String imageUrl;

    public manga(String id, String title, String description, int publishYear, String author, String artist, String imageUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.publishYear = publishYear;
        this.author = author;
        this.artist = artist;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public String getAuthor() {
        return author;
    }

    public String getArtist() {
        return artist;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
