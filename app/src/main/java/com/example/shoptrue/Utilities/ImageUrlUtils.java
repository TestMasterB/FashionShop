package com.example.shoptrue.Utilities;

import android.graphics.drawable.Drawable;

import com.example.shoptrue.R;

import java.util.ArrayList;

public class ImageUrlUtils {

    public static ArrayList <SingleItemModule> getImageUrls() {
        ArrayList<SingleItemModule> model= new ArrayList<>();
        model.add(new SingleItemModule(R.drawable.jaens,"Jeans","Narrow Bottom Jeans","100"));
        model.add(new SingleItemModule(R.drawable.tshirt,"T-Shirt","Basic","50"));
        model.add(new SingleItemModule(R.drawable.hoodie,"Hoodie","Cotton","200"));
        model.add(new SingleItemModule(R.drawable.jacke,"Jacke","Berlin Style","300"));
        return model;
    }






}
