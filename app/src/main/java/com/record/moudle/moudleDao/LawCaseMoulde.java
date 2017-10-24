package com.record.moudle.moudleDao;

import com.record.moudle.entity.LawCase;

import java.util.List;

/**
 * Created by 灌云县公安局 李秉键 on 2017/9/3.
 */

public interface LawCaseMoulde {
    boolean addLawCase(LawCase lawCase);
    boolean upDateLawCase(LawCase lawCase);
    boolean deleteById (Long id) ;
    void deleteAll () ;
    LawCase seletcById (Long id);
    List<LawCase> seletcAll();
    List<LawCase> seletcPrinted();
}
