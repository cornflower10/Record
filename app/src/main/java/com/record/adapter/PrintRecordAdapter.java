package com.record.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.record.R;
import com.record.moudle.entity.PrintRecord;

import java.util.List;

/**
 * Created by xiejingbao on 2017/8/14.
 */

public class PrintRecordAdapter extends BaseQuickAdapter<PrintRecord,BaseViewHolder> {


    public PrintRecordAdapter(@LayoutRes int layoutResId, @Nullable List<PrintRecord> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final PrintRecord item) {

        helper.setText(R.id.tv_name,item.getTitle());
        helper.setText(R.id.tv_date,item.getDate());


    }


    public  void refresh(List<PrintRecord> data){
        mData.clear();
        if(null!=data){
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

}
