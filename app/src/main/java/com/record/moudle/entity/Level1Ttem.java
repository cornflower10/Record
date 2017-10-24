package com.record.moudle.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.record.adapter.TempIncludeAdapter;

/**
 * Created by 灌云县公安局 李秉键 on 2017/10/9.
 */

public class Level1Ttem  extends AbstractExpandableItem<LawCase> implements MultiItemEntity {
    public String title;
    public String subTitle;

    public Level1Ttem(String title, String subTitle) {
        this.subTitle = subTitle;
        this.title = title;
    }

    @Override
    public int getItemType() {
        return TempIncludeAdapter.CHILD;
    }

    @Override
    public int getLevel() {
        return 2;
    }
}
