package com.record.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.record.R;
import com.record.moudle.entity.OfficeItem;

import java.util.List;

/**
 * Created by 灌云县公安局 李秉键 on 2017/8/14.
 */

public class OfficeItemAdapter extends BaseQuickAdapter<OfficeItem,BaseViewHolder> {


    public OfficeItemAdapter(@LayoutRes int layoutResId, @Nullable List<OfficeItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final OfficeItem item) {

        helper.setText(R.id.tv_title,item.getName());
        helper.setImageResource(R.id.iv,item.getResource());

    }


}
