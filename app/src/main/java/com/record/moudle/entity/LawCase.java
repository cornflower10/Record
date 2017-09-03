package com.record.moudle.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by xiejingbao on 2017/9/3.
 */
@Entity
public class LawCase {
    @Id(autoincrement = true)
    private Long id;
    /**
     * 人员信息
     */
    private String name;
    private String birthDay;
    private String address;
    /**
     * 案发时间
     */
    private String date;
    private String carNo;
    private boolean isPrint;

    private String money;

    private String lawCaseTitle;

    private String docPath;
    private String doc2Htmlpath;

    @Generated(hash = 2078451482)
    public LawCase(Long id, String name, String birthDay, String address,
            String date, String carNo, boolean isPrint, String money,
            String lawCaseTitle, String docPath, String doc2Htmlpath) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
        this.address = address;
        this.date = date;
        this.carNo = carNo;
        this.isPrint = isPrint;
        this.money = money;
        this.lawCaseTitle = lawCaseTitle;
        this.docPath = docPath;
        this.doc2Htmlpath = doc2Htmlpath;
    }
    @Generated(hash = 1106844273)
    public LawCase() {
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBirthDay() {
        return this.birthDay;
    }
    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getCarNo() {
        return this.carNo;
    }
    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }
    public boolean getIsPrint() {
        return this.isPrint;
    }
    public void setIsPrint(boolean isPrint) {
        this.isPrint = isPrint;
    }
    public String getMoney() {
        return this.money;
    }
    public void setMoney(String money) {
        this.money = money;
    }
    public String getLawCaseTitle() {
        return this.lawCaseTitle;
    }
    public void setLawCaseTitle(String lawCaseTitle) {
        this.lawCaseTitle = lawCaseTitle;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDocPath() {
        return this.docPath;
    }
    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }
    public String getDoc2Htmlpath() {
        return this.doc2Htmlpath;
    }
    public void setDoc2Htmlpath(String doc2Htmlpath) {
        this.doc2Htmlpath = doc2Htmlpath;
    }



}
