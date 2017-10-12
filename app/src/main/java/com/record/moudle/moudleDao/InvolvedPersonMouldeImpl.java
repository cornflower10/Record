package com.record.moudle.moudleDao;

import com.record.App;
import com.record.dao.InvolvedPersonDao;
import com.record.moudle.entity.InvolvedPerson;

import java.util.List;

/**
 * Created by xiejingbao on 2017/9/3.
 */

public class InvolvedPersonMouldeImpl implements InvolvedPersonMoulde {

    private ErrorView errorView;

    public InvolvedPersonMouldeImpl (ErrorView errorView){
        this.errorView = errorView;
    }

    @Override
    public boolean addInvolved(InvolvedPerson involvedPerson) {

        try {
            App.getDaoSession().getInvolvedPersonDao().insert(involvedPerson);
            return true;
        }catch (Exception e){
            errorView.onError("保存失败");
            return false;

        }
    }

    @Override
    public boolean upDateInvolved(InvolvedPerson involvedPerson) {
        try {
            App.getDaoSession().getInvolvedPersonDao().update(involvedPerson);
            return true;
        }catch (Exception e){
            errorView.onError("更新失败");
            return false;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public InvolvedPerson seletcById(Long id) {
        return null;
    }

    @Override
    public List<InvolvedPerson> seletcAll() {
        List<InvolvedPerson> list =  App.getDaoSession().getInvolvedPersonDao().
                queryBuilder().orderDesc(InvolvedPersonDao.Properties.Date).list();
        return list;
    }
}
