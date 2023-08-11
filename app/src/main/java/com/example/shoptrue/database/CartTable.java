package com.example.shoptrue.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Ähnlicher Aufbau, wie LoginTable

@Entity
public class CartTable implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="id")
    public Integer id;

    @ColumnInfo(name ="image")
    int image;
    @ColumnInfo(name ="name")
    String name;
    @ColumnInfo(name ="description")
    String description;
    @ColumnInfo(name ="price")
    String price;

    public CartTable(int image, String name, String description, String price) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;

    }

    protected CartTable(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        image = in.readInt();
        name = in.readString();
        description = in.readString();
        price = in.readString();
    }

    public static final Creator<CartTable> CREATOR = new Creator<CartTable>() {
        @Override
        public CartTable createFromParcel(Parcel in) {
            return new CartTable(in);
        }

        @Override
        public CartTable[] newArray(int size) {
            return new CartTable[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeInt(image);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(price);
    }
}
