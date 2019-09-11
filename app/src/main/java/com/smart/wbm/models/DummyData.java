package com.smart.wbm.models;

import java.io.Serializable;

public class DummyData implements Serializable {

    String image, name;

    public DummyData(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
