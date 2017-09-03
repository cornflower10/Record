package com.record.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.record.R;
import com.record.moudle.entity.LawCase;

import java.util.List;

/**
 * Created by xiejingbao on 2017/8/14.
 */

public class LawCaseAdapter extends BaseQuickAdapter<LawCase,BaseViewHolder> {


    public LawCaseAdapter(@LayoutRes int layoutResId, @Nullable List<LawCase> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final LawCase item) {

        helper.setText(R.id.tv_name,item.getLawCaseTitle());
        helper.setText(R.id.tv_date,item.getDate());


    }


    public  void refresh(List<LawCase> data){
        mData.clear();
        if(null!=data){
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

}
