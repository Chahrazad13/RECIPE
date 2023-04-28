package com.example.tpjavarecipes.bean;
import java.time.LocalDateTime;

public class Recipe {
    private int id;
    private String name;
    private String description;
    private LocalDateTime dateTime;
    private String pictureUrl;

    public Recipe(String name, String description, String date_of_Creation, String pictureUrl) {
        this.name = name;
        this.description = description;
        this.dateTime = LocalDateTime.parse(date_of_Creation);
        this.pictureUrl = pictureUrl;
    }

    public Recipe(int id, String name, String description, String date_of_Creation, String pictureUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateTime = LocalDateTime.parse(date_of_Creation);
        this.pictureUrl = pictureUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDatetime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime date_of_Creation) {
        this.dateTime = date_of_Creation;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
