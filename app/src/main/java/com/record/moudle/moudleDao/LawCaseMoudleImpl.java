package com.record.moudle.moudleDao;

import com.record.App;
import com.record.moudle.entity.LawCase;

import java.util.Collections;
import java.util.List;

/**
 * Created by xiejingbao on 2017/9/3.
 */

public class LawCaseMoudleImpl implements LawCaseMoulde{
    private LawCaseView lawCaseView;
    public LawCaseMoudleImpl (LawCaseView lawCaseView){
        this.lawCaseView = lawCaseView;
    }

    @Override
    public boolean addLawCase(LawCase lawCase) {
        try {
            App.getDaoSession().getLawCaseDao().insert(lawCase);
            return true;
        }catch (Exception e){
            lawCaseView.onError("保存失败");
            return false;

        }


    }

    @Override
    public boolean upDateLawCase(LawCase lawCase) {
        try {
            App.getDaoSession().getLawCaseDao().update(lawCase);
            return true;
        }catch (Exception e){
            lawCaseView.onError("更新失败");
            return false;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            App.getDaoSession().getLawCaseDao().deleteByKey(id);
            return true;
        }catch (Exception e){
            lawCaseView.onError("删除失败");
            return false;
        }
    }

    @Override
    public void deleteAll() {
        App.getDaoSession().getLawCaseDao().deleteAll();
    }

    @Override
    public LawCase seletcById(Long id) {

           return  App.getDaoSession().getLawCaseDao().load(id);

    }

    @Override
    public List<LawCase> seletcAll() {
      List<LawCase> list =  App.getDaoSession().getLawCaseDao().loadAll();
        if(null!=list&&list.size()>0){
            Collections.reverse(list);
        }
        return list;
    }

    @Override
    public List<LawCase> seletcPrinted() {
        List<LawCase> list = seletcAll();
        if(null!=list&&list.size()>0){
            for (int i=0;i<list.size();i++){
                if(!list.get(i).getIsPrint()){
                    list.remove(list.get(i));
                    i--;
                }
            }
        }
        return list;
    }
}
