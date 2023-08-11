package com.example.shoptrue.Utilities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

// In diesem Abschnitt werden die produktrelevanten Informationen festgelegt

public class SingleItemModule implements Parcelable {

    int image;
    String name;
    String description;
    String price;


    public SingleItemModule(int image, String name, String description, String price) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    protected SingleItemModule(Parcel in) {
        image = in.readInt();
        name = in.readString();
        description = in.readString();
        price = in.readString();
    }

    public static final Creator<SingleItemModule> CREATOR = new Creator<SingleItemModule>() {
        @Override
        public SingleItemModule createFromParcel(Parcel in) {
            return new SingleItemModule(in);
        }

        @Override
        public SingleItemModule[] newArray(int size) {
            return new SingleItemModule[size];
        }
    };

    // Getter und Setter werden verwendet, um die relevanten Informationen der Produkte zu entnehmen
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
        dest.writeInt(image);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(price);
    }
}
