package com.record.moudle.entity;

/**
 * Created by xiejingbao on 2017/11/6.
 */

public class OfficeItem {
    private String name;
    private int resource;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public OfficeItem(String name) {
        this.name = name;
    }
}
