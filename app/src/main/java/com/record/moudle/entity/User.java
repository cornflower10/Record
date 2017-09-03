package com.record.moudle.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by xiejingbao on 2017/9/2.
 */
@Entity
public class User {
    @Id(autoincrement = true)
    private Long id;

//    private int sex;

    private String name;

    private String userNo;

    @Generated(hash = 435052130)
    public User(Long id, String name, String userNo) {
        this.id = id;
        this.name = name;
        this.userNo = userNo;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserNo() {
        return this.userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }
}
