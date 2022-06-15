package com.doubleclick.b_safe.model;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

/**
 * Created By Eslam Ghazy on 6/14/2022
 */
public class ServiceCenter {

    @NonNull
    private String name;
    @NonNull
    private String address;
    private float rate;
    @NonNull
    private String images;
    @NonNull
    private String phone;

    public ServiceCenter() {
        name = "";
        address = "";
        rate = 0.0f;
        images = "";
        phone = "";
    }


    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    public float getRate() {
        return rate;
    }

    @NonNull
    public List<String> getImages() {
        List<String> image = Arrays.asList(images.replace("[","").replace("]","").replace(" ","").split(","));
        return image;
    }

    public String getOnlyImage(){
        List<String> image = Arrays.asList(images.replace("[","").replace("]","").replace(" ","").split(","));
        return image.get(0);
    }

    @NonNull
    public String getPhone() {
        return phone;
    }
}
