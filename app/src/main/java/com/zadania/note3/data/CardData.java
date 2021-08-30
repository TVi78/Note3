package com.zadania.note3.data;

public class CardData {
    private String title;       // заголовок
    private String description; // описание

    private String description2; // описание
    private String data; // описание
    private int picture;        // изображение
    private boolean like;       // флажок

    public CardData(String title, String description, String description2, String data, int picture, boolean like) {
        this.title = title;
        this.description = description;
        this.picture = picture;
        this.like = like;
        this.description2 = description2;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPicture() {
        return picture;
    }

    public boolean isLike() {
        return like;
    }

    public String getDescription2() {
        return description2;
    }

    public String getData() {
        return data;
    }
}
