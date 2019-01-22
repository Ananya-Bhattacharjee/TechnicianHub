package com.example.user.tchub;

/**
 * Created by User on 7/8/2017.
 */

public class TechPhoto {
    String id;
    String name;
    String photo;

    public TechPhoto() {
    }

    public TechPhoto(String id,String name, String photo) {
        this.id = id;
        this.name = name;
        this.photo = photo;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

}
