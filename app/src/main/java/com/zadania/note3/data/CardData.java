package com.zadania.note3.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class CardData implements Parcelable {
    private String title;       // заголовок
    private String description; // описание

    private String description2; // описание
    private String data; // описание
    private int picture;        // изображение
    private boolean like;       // флажок
    private Date date;          // дата

    public CardData(String title, String description, String description2, String data, int picture, boolean like, Date date) {
        this.title = title;
        this.description = description;
        this.picture = picture;
        this.like = like;
        this.description2 = description2;
        this.data = data;
        this.date = date;
    }

    protected CardData(Parcel in) {
        title = in.readString();
        description = in.readString();
        description2 = in.readString();
        data = in.readString();
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
        dest.writeString(data);
        dest.writeInt(picture);
        dest.writeByte((byte) (like ? 1 : 0));
        dest.writeLong(date.getTime());
    }
}
