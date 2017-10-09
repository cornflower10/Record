package com.record.moudle.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.record.adapter.TempIncludeAdapter;

/**
 * Created by xiejingbao on 2017/10/9.
 */

public class Level0Item extends AbstractExpandableItem<TempInclude> implements MultiItemEntity {
private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Level0Item(String title) {
        this.title = title;
        }

@Override
public int getItemType() {
        return TempIncludeAdapter.GROUP;
        }

@Override
public int getLevel() {
        return 1;
        }
        }
