package com.record.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.record.R;
import com.record.moudle.entity.PrintChoose;

import java.util.List;

/**
 * Created by 灌云县公安局 李秉键 on 2017/8/14.
 */

public class PrintChooseAdapter extends BaseQuickAdapter<PrintChoose,BaseViewHolder> {


    public PrintChooseAdapter(@LayoutRes int layoutResId, @Nullable List<PrintChoose> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final PrintChoose item) {

        helper.setText(R.id.tv_name,item.getName());

    }


    public  void refresh(List<PrintChoose> data){
        mData.clear();
        if(null!=data){
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

}
