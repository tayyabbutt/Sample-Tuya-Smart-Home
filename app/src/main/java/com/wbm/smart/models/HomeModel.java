package com.wbm.smart.models;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeModel implements Serializable {

@SerializedName("location")
@Expose
private Location location;
@SerializedName("address")
@Expose
private Address address;
@SerializedName("contact")
@Expose
private Contact contact;
@SerializedName("rooms")
@Expose
private List<Object> rooms = null;
@SerializedName("_id")
@Expose
private String id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("createdAt")
@Expose
private String createdAt;
@SerializedName("updatedAt")
@Expose
private String updatedAt;
@SerializedName("__v")
@Expose
private Integer v;

public Location getLocation() {
return location;
}

public void setLocation(Location location) {
this.location = location;
}

public Address getAddress() {
return address;
}

public void setAddress(Address address) {
this.address = address;
}

public Contact getContact() {
return contact;
}

public void setContact(Contact contact) {
this.contact = contact;
}

public List<Object> getRooms() {
return rooms;
}

public void setRooms(List<Object> rooms) {
this.rooms = rooms;
}

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getCreatedAt() {
return createdAt;
}

public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

public String getUpdatedAt() {
return updatedAt;
}

public void setUpdatedAt(String updatedAt) {
this.updatedAt = updatedAt;
}

public Integer getV() {
return v;
}

public void setV(Integer v) {
this.v = v;
}

}