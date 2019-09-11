package com.smart.wbm.models;

import java.io.Serializable;

public class RoomModel implements Serializable {

    String roomName;

    public RoomModel(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
