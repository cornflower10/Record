package com.record.moudle.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by xiejingbao on 2017/10/10.
 */
@Entity
public class InvolvedPerson implements Parcelable{

//    1、提取涉案人员信息
//    姓名 $involved_name$
//    性别 $involved_sex$
//    民族 $involved_nationality$
//    出生日期 $involved_birth_date$
//    年龄 $involved_age$
//    文化程度 $involved_degree$
//    籍贯 $involved_native_place$
//    驾驶证准驾车型 $involved_drive_license_type$
//    领证日期 $involved_drive_license_date$
//    身份证号是： $involved_idcard$
//    户籍地址 $involved_native_address$
//    现住址 $involved_address$
//    联系电话： $involved_mobile$

    @Id(autoincrement = true)
    private Long id;


    private int type;//存放类型1人员信息2车辆信息3涉案信息

    private long date;

    private String involved_name;
    private String involved_sex;
    private String involved_nationality;
    private String involved_birth_date;
    private String involved_age;
    private String involved_degree;
    private String involved_native_place;
    private String involved_drive_license_type;
    private String involved_drive_license_date;
    private String involved_idcard;
    private String involved_native_address;
    private String involved_address;
    private String involved_mobile;


    private String car_no;
    private String car_type;



    /*    3、提取涉案信息
    涉案人员 $thing_personnel_involved$
    涉案车辆 $thing_car$
    涉案时间 $thing_date$
    涉案地点 $thing_address$
    过错原因 $thing_reason$
    损失项目 $thing_losed$
    交通事故时间 $thing_$
    交通事故地点 $thing_11$
    当事人基本情况 $thing_personnel_involved_basic$
    车辆基本情况   $thing_car_basic$
    道路和交通环境 $thing_road_condition$
    道路交通事故发生经过 $thing_info$
    道路交通事故证据 $thing_prof$
    事故形成原因的分析 $thing_ analysis$
    适用的法律、法规 $thing_law$
    责任认定：$thing_ confirmation$*/

    private String thing_personnel_involved;
    private String thing_car;
    private String thing_date;
    private String thing_address;
    private String thing_reason;
    private String thing_losed;
    private String thing_;
    private String thing_11;
    private String thing_personnel_involved_basic;
    private String thing_car_basic;
    private String thing_road_condition;
    private String thing_info;
    private String thing_prof;

    private String thing_analysis;
    private String thing_law;
    private String thing_confirmation;
    @Generated(hash = 771544545)
    public InvolvedPerson(Long id, int type, long date, String involved_name,
            String involved_sex, String involved_nationality,
            String involved_birth_date, String involved_age, String involved_degree,
            String involved_native_place, String involved_drive_license_type,
            String involved_drive_license_date, String involved_idcard,
            String involved_native_address, String involved_address,
            String involved_mobile, String car_no, String car_type,
            String thing_personnel_involved, String thing_car, String thing_date,
            String thing_address, String thing_reason, String thing_losed,
            String thing_, String thing_11, String thing_personnel_involved_basic,
            String thing_car_basic, String thing_road_condition, String thing_info,
            String thing_prof, String thing_analysis, String thing_law,
            String thing_confirmation) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.involved_name = involved_name;
        this.involved_sex = involved_sex;
        this.involved_nationality = involved_nationality;
        this.involved_birth_date = involved_birth_date;
        this.involved_age = involved_age;
        this.involved_degree = involved_degree;
        this.involved_native_place = involved_native_place;
        this.involved_drive_license_type = involved_drive_license_type;
        this.involved_drive_license_date = involved_drive_license_date;
        this.involved_idcard = involved_idcard;
        this.involved_native_address = involved_native_address;
        this.involved_address = involved_address;
        this.involved_mobile = involved_mobile;
        this.car_no = car_no;
        this.car_type = car_type;
        this.thing_personnel_involved = thing_personnel_involved;
        this.thing_car = thing_car;
        this.thing_date = thing_date;
        this.thing_address = thing_address;
        this.thing_reason = thing_reason;
        this.thing_losed = thing_losed;
        this.thing_ = thing_;
        this.thing_11 = thing_11;
        this.thing_personnel_involved_basic = thing_personnel_involved_basic;
        this.thing_car_basic = thing_car_basic;
        this.thing_road_condition = thing_road_condition;
        this.thing_info = thing_info;
        this.thing_prof = thing_prof;
        this.thing_analysis = thing_analysis;
        this.thing_law = thing_law;
        this.thing_confirmation = thing_confirmation;
    }
    @Generated(hash = 859937960)
    public InvolvedPerson() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public long getDate() {
        return this.date;
    }
    public void setDate(long date) {
        this.date = date;
    }
    public String getInvolved_name() {
        return this.involved_name;
    }
    public void setInvolved_name(String involved_name) {
        this.involved_name = involved_name;
    }
    public String getInvolved_sex() {
        return this.involved_sex;
    }
    public void setInvolved_sex(String involved_sex) {
        this.involved_sex = involved_sex;
    }
    public String getInvolved_nationality() {
        return this.involved_nationality;
    }
    public void setInvolved_nationality(String involved_nationality) {
        this.involved_nationality = involved_nationality;
    }
    public String getInvolved_birth_date() {
        return this.involved_birth_date;
    }
    public void setInvolved_birth_date(String involved_birth_date) {
        this.involved_birth_date = involved_birth_date;
    }
    public String getInvolved_age() {
        return this.involved_age;
    }
    public void setInvolved_age(String involved_age) {
        this.involved_age = involved_age;
    }
    public String getInvolved_degree() {
        return this.involved_degree;
    }
    public void setInvolved_degree(String involved_degree) {
        this.involved_degree = involved_degree;
    }
    public String getInvolved_native_place() {
        return this.involved_native_place;
    }
    public void setInvolved_native_place(String involved_native_place) {
        this.involved_native_place = involved_native_place;
    }
    public String getInvolved_drive_license_type() {
        return this.involved_drive_license_type;
    }
    public void setInvolved_drive_license_type(String involved_drive_license_type) {
        this.involved_drive_license_type = involved_drive_license_type;
    }
    public String getInvolved_drive_license_date() {
        return this.involved_drive_license_date;
    }
    public void setInvolved_drive_license_date(String involved_drive_license_date) {
        this.involved_drive_license_date = involved_drive_license_date;
    }
    public String getInvolved_idcard() {
        return this.involved_idcard;
    }
    public void setInvolved_idcard(String involved_idcard) {
        this.involved_idcard = involved_idcard;
    }
    public String getInvolved_native_address() {
        return this.involved_native_address;
    }
    public void setInvolved_native_address(String involved_native_address) {
        this.involved_native_address = involved_native_address;
    }
    public String getInvolved_address() {
        return this.involved_address;
    }
    public void setInvolved_address(String involved_address) {
        this.involved_address = involved_address;
    }
    public String getInvolved_mobile() {
        return this.involved_mobile;
    }
    public void setInvolved_mobile(String involved_mobile) {
        this.involved_mobile = involved_mobile;
    }
    public String getCar_no() {
        return this.car_no;
    }
    public void setCar_no(String car_no) {
        this.car_no = car_no;
    }
    public String getCar_type() {
        return this.car_type;
    }
    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }
    public String getThing_personnel_involved() {
        return this.thing_personnel_involved;
    }
    public void setThing_personnel_involved(String thing_personnel_involved) {
        this.thing_personnel_involved = thing_personnel_involved;
    }
    public String getThing_car() {
        return this.thing_car;
    }
    public void setThing_car(String thing_car) {
        this.thing_car = thing_car;
    }
    public String getThing_date() {
        return this.thing_date;
    }
    public void setThing_date(String thing_date) {
        this.thing_date = thing_date;
    }
    public String getThing_address() {
        return this.thing_address;
    }
    public void setThing_address(String thing_address) {
        this.thing_address = thing_address;
    }
    public String getThing_reason() {
        return this.thing_reason;
    }
    public void setThing_reason(String thing_reason) {
        this.thing_reason = thing_reason;
    }
    public String getThing_losed() {
        return this.thing_losed;
    }
    public void setThing_losed(String thing_losed) {
        this.thing_losed = thing_losed;
    }
    public String getThing_() {
        return this.thing_;
    }
    public void setThing_(String thing_) {
        this.thing_ = thing_;
    }
    public String getThing_11() {
        return this.thing_11;
    }
    public void setThing_11(String thing_11) {
        this.thing_11 = thing_11;
    }
    public String getThing_personnel_involved_basic() {
        return this.thing_personnel_involved_basic;
    }
    public void setThing_personnel_involved_basic(
            String thing_personnel_involved_basic) {
        this.thing_personnel_involved_basic = thing_personnel_involved_basic;
    }
    public String getThing_car_basic() {
        return this.thing_car_basic;
    }
    public void setThing_car_basic(String thing_car_basic) {
        this.thing_car_basic = thing_car_basic;
    }
    public String getThing_road_condition() {
        return this.thing_road_condition;
    }
    public void setThing_road_condition(String thing_road_condition) {
        this.thing_road_condition = thing_road_condition;
    }
    public String getThing_info() {
        return this.thing_info;
    }
    public void setThing_info(String thing_info) {
        this.thing_info = thing_info;
    }
    public String getThing_prof() {
        return this.thing_prof;
    }
    public void setThing_prof(String thing_prof) {
        this.thing_prof = thing_prof;
    }
    public String getThing_analysis() {
        return this.thing_analysis;
    }
    public void setThing_analysis(String thing_analysis) {
        this.thing_analysis = thing_analysis;
    }
    public String getThing_law() {
        return this.thing_law;
    }
    public void setThing_law(String thing_law) {
        this.thing_law = thing_law;
    }
    public String getThing_confirmation() {
        return this.thing_confirmation;
    }
    public void setThing_confirmation(String thing_confirmation) {
        this.thing_confirmation = thing_confirmation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeInt(this.type);
        dest.writeLong(this.date);
        dest.writeString(this.involved_name);
        dest.writeString(this.involved_sex);
        dest.writeString(this.involved_nationality);
        dest.writeString(this.involved_birth_date);
        dest.writeString(this.involved_age);
        dest.writeString(this.involved_degree);
        dest.writeString(this.involved_native_place);
        dest.writeString(this.involved_drive_license_type);
        dest.writeString(this.involved_drive_license_date);
        dest.writeString(this.involved_idcard);
        dest.writeString(this.involved_native_address);
        dest.writeString(this.involved_address);
        dest.writeString(this.involved_mobile);
        dest.writeString(this.car_no);
        dest.writeString(this.car_type);
        dest.writeString(this.thing_personnel_involved);
        dest.writeString(this.thing_car);
        dest.writeString(this.thing_date);
        dest.writeString(this.thing_address);
        dest.writeString(this.thing_reason);
        dest.writeString(this.thing_losed);
        dest.writeString(this.thing_);
        dest.writeString(this.thing_11);
        dest.writeString(this.thing_personnel_involved_basic);
        dest.writeString(this.thing_car_basic);
        dest.writeString(this.thing_road_condition);
        dest.writeString(this.thing_info);
        dest.writeString(this.thing_prof);
        dest.writeString(this.thing_analysis);
        dest.writeString(this.thing_law);
        dest.writeString(this.thing_confirmation);
    }

    protected InvolvedPerson(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.type = in.readInt();
        this.date = in.readLong();
        this.involved_name = in.readString();
        this.involved_sex = in.readString();
        this.involved_nationality = in.readString();
        this.involved_birth_date = in.readString();
        this.involved_age = in.readString();
        this.involved_degree = in.readString();
        this.involved_native_place = in.readString();
        this.involved_drive_license_type = in.readString();
        this.involved_drive_license_date = in.readString();
        this.involved_idcard = in.readString();
        this.involved_native_address = in.readString();
        this.involved_address = in.readString();
        this.involved_mobile = in.readString();
        this.car_no = in.readString();
        this.car_type = in.readString();
        this.thing_personnel_involved = in.readString();
        this.thing_car = in.readString();
        this.thing_date = in.readString();
        this.thing_address = in.readString();
        this.thing_reason = in.readString();
        this.thing_losed = in.readString();
        this.thing_ = in.readString();
        this.thing_11 = in.readString();
        this.thing_personnel_involved_basic = in.readString();
        this.thing_car_basic = in.readString();
        this.thing_road_condition = in.readString();
        this.thing_info = in.readString();
        this.thing_prof = in.readString();
        this.thing_analysis = in.readString();
        this.thing_law = in.readString();
        this.thing_confirmation = in.readString();
    }

    public static final Creator<InvolvedPerson> CREATOR = new Creator<InvolvedPerson>() {
        @Override
        public InvolvedPerson createFromParcel(Parcel source) {
            return new InvolvedPerson(source);
        }

        @Override
        public InvolvedPerson[] newArray(int size) {
            return new InvolvedPerson[size];
        }
    };
}
