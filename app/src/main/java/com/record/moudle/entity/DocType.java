package com.record.moudle.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 灌云县公安局 李秉键 on 2017/9/1.
 */

public class DocType implements Parcelable {

    private String title;
    private int type;
    private String path;
    private String absoluteTitle;

    public String getAbsoluteTitle() {
        return absoluteTitle;
    }

    public void setAbsoluteTitle(String absoluteTitle) {
        this.absoluteTitle = absoluteTitle;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeInt(this.type);
        dest.writeString(this.path);
    }

    public DocType() {
    }

    protected DocType(Parcel in) {
        this.title = in.readString();
        this.type = in.readInt();
        this.path = in.readString();
    }

    public static final Creator<DocType> CREATOR = new Creator<DocType>() {
        @Override
        public DocType createFromParcel(Parcel source) {
            return new DocType(source);
        }

        @Override
        public DocType[] newArray(int size) {
            return new DocType[size];
        }
    };
}
