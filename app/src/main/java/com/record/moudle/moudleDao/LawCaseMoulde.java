package com.record.moudle.moudleDao;

import com.record.moudle.entity.LawCase;

import java.util.List;

/**
 * Created by xiejingbao on 2017/9/3.
 */

public interface LawCaseMoulde {
    boolean addLawCase(LawCase lawCase);
    boolean upDateLawCase(LawCase lawCase);
    boolean deleteById (Long id) ;
    LawCase seletcById (Long id);
    List<LawCase> seletcAll();
    List<LawCase> seletcPrinted();
}
