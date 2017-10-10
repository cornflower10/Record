package com.record.moudle.moudleDao;

import com.record.moudle.entity.InvolvedPerson;

import java.util.List;

/**
 * Created by xiejingbao on 2017/9/3.
 */

public interface InvolvedPersonMoulde {
    boolean addInvolved(InvolvedPerson involvedPerson);
    boolean upDateInvolved(InvolvedPerson involvedPerson);
    boolean deleteById(Long id) ;
    void deleteAll() ;
    InvolvedPerson seletcById(Long id);
    List<InvolvedPerson> seletcAll();
}
