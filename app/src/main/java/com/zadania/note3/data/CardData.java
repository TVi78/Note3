package com.zadania.note3.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class CardData implements Parcelable {
    private String id;
    private String title;       // заголовок
    private String description; // описание
    private String description2; // описание
    private String ddata; // описание
    private int picture;        // изображение
    private boolean like;       // флажок
    private Date date;          // дата

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public void setDdata(String data) {
        this.ddata = data;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CardData(String title, String description, String description2,
                    String ddata, int picture, boolean like, Date date) {
        this.title = title;
        this.description = description;
        this.picture = picture;
        this.like = like;
        this.description2 = description2;
        this.ddata = ddata;
        this.date = date;
    }

    protected CardData(Parcel in) {
        title = in.readString();
        description = in.readString();
        description2 = in.readString();
        ddata = in.readString();
        picture = in.readInt();
        like = in.readByte() != 0;
        date = new Date(in.readLong());
    }

    public static final Creator<CardData> CREATOR = new Creator<CardData>() {
        @Override
        public CardData createFromParcel(Parcel in) {
            return new CardData(in);
        }

        @Override
        public CardData[] newArray(int size) {
            return new CardData[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDescription2() {
        return description2;
    }

    public String getDdata() {
        return ddata;
    }

    public int getPicture() {
        return picture;
    }

    public boolean isLike() {
        return like;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(description2);
        dest.writeString(ddata);
        dest.writeInt(picture);
        dest.writeByte((byte) (like ? 1 : 0));
        dest.writeLong(date.getTime());
    }
}
