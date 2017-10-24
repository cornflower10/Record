package com.record.moudle.moudleDao;

import com.record.moudle.entity.User;

import java.util.List;

/**
 * Created by 灌云县公安局 李秉键 on 2017/9/3.
 */

public interface UserMoulde {
    boolean addUser(User user);
    boolean upDateUser(User user);
    User seletcById(Long id);
    User seletcUser();
    List<User> seletcAll();
}
