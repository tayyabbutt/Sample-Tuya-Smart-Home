package com.wbm.smart.models;

import java.io.Serializable;

public class DummyAction implements Serializable {

    String action;

    public DummyAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
