package com.doubleclick.b_safe.model;

/**
 * Created By Eslam Ghazy on 6/13/2022
 */
public class User {

    private String id;
    private String email;
    private String name;
    private String phone;
    private String token;
    private String vehicleModel;
    private String image;

    public User() {
    }


    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getToken() {
        return token;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public String getImage() {
        return image;
    }
}
