package com.wbm.smart.models;

import com.tuya.smart.sdk.bean.DeviceBean;
import com.tuya.smart.sdk.bean.GroupBean;

import java.io.Serializable;
import java.util.List;

public class RoomsBean implements Serializable {
    private long roomId;
    private String name;
    private List<DeviceBean> deviceList;
    private List<GroupBean> groupList;
    private int displayOrder;
    private String background;
    private boolean sel;


    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DeviceBean> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<DeviceBean> deviceList) {
        this.deviceList = deviceList;
    }

    public List<GroupBean> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<GroupBean> groupList) {
        this.groupList = groupList;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public boolean isSel() {
        return sel;
    }

    public void setSel(boolean sel) {
        this.sel = sel;
    }
}
