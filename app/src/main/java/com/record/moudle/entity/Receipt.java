package com.record.moudle.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 灌云县公安局 李秉键 on 2017/9/8.
 */

public class Receipt implements Parcelable{
    private String name;
    private String carNo;
    private String money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.carNo);
        dest.writeString(this.money);
    }

    public Receipt() {
    }

    protected Receipt(Parcel in) {
        this.name = in.readString();
        this.carNo = in.readString();
        this.money = in.readString();
    }

    public static final Creator<Receipt> CREATOR = new Creator<Receipt>() {
        @Override
        public Receipt createFromParcel(Parcel source) {
            return new Receipt(source);
        }

        @Override
        public Receipt[] newArray(int size) {
            return new Receipt[size];
        }
    };
}
