package com.nk.adstemp.models;

public class ModelProduct {

    private int icon;
    private String title, description;
    private float rating;

    public ModelProduct(int icon, String title, String description, float rating) {
        this.icon = icon;
        this.title = title;
        this.description = description;
        this.rating = rating;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ModelProduct{" +
                "icon=" + icon +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                '}';
    }
}
