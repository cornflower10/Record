package com.record.moudle.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.record.adapter.TempIncludeAdapter;

/**
 * Created by 灌云县公安局 李秉键 on 2017/10/9.
 */

public class TempInclude  extends AbstractExpandableItem<LawCase> implements MultiItemEntity{
    private String typeName;
    private LawCase lawCase;
    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    @Override
    public int getLevel() {
        return 2;
    }

    @Override
    public int getItemType() {
        return TempIncludeAdapter.CHILD;
    }

    public LawCase getLawCase() {
        return lawCase;
    }

    public void setLawCase(LawCase lawCase) {
        this.lawCase = lawCase;
    }
}
