package com.bookstore.bookstorecsdksibsapplication;

import java.util.ArrayList;

public class Book {
    private final String title;
    private final String thumbnailUrl;
    private final ArrayList<String> authors;
    private final String description;
    private final String buyLink;

    public Book(String title, String thumbnailUrl, ArrayList<String> authors, String description, String buyLink) {
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.authors = authors;
        this.description = description;
        this.buyLink = buyLink;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public String getDescription() {
        return description;
    }

    public String getBuyLink() {
        return buyLink;
    }
}

