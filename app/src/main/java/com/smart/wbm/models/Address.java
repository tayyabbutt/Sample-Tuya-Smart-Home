package com.smart.wbm.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Address implements Serializable {

@SerializedName("street")
@Expose
private String street;
@SerializedName("area")
@Expose
private String area;
@SerializedName("city")
@Expose
private String city;
@SerializedName("state")
@Expose
private String state;
@SerializedName("zipCode")
@Expose
private String zipCode;
@SerializedName("country")
@Expose
private String country;

public String getStreet() {
return street;
}

public void setStreet(String street) {
this.street = street;
}

public String getArea() {
return area;
}

public void setArea(String area) {
this.area = area;
}

public String getCity() {
return city;
}

public void setCity(String city) {
this.city = city;
}

public String getState() {
return state;
}

public void setState(String state) {
this.state = state;
}

public String getZipCode() {
return zipCode;
}

public void setZipCode(String zipCode) {
this.zipCode = zipCode;
}

public String getCountry() {
return country;
}

public void setCountry(String country) {
this.country = country;
}

}