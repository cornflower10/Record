package com.record.moudle.moudleDao;

import com.record.App;
import com.record.moudle.entity.User;

import java.util.List;

/**
 * Created by 灌云县公安局 李秉键 on 2017/10/12.
 */

public class UserMouldeImpl implements UserMoulde {
    private ErrorView errorView;
    public UserMouldeImpl (ErrorView errorView){
        this.errorView = errorView;
    }
    @Override
    public boolean addUser(User user) {
        try {
            App.getDaoSession().getUserDao().insert(user);
            return true;
        }catch (Exception e){
            errorView.onError("保存失败");
            return false;

        }
    }

    @Override
    public boolean upDateUser(User user) {
        try {
            App.getDaoSession().getUserDao().update(user);
            return true;
        }catch (Exception e){
            errorView.onError("更新失败");
            return false;
        }
    }

    @Override
    public User seletcById(Long id) {
        return  App.getDaoSession().getUserDao().load(id);
    }

    @Override
    public User seletcUser() {
        List<User> list = seletcAll();
        if(null!=list&&list.size()>0){
           return list.get(0);
        }
        return null;

    }

    @Override
    public List<User> seletcAll() {
        List<User> list =  App.getDaoSession().getUserDao().loadAll();
        return list;
    }
}
