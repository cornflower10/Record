package com.record.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.record.R;
import com.record.moudle.entity.InvolvedPerson;
import com.record.utils.Constants;

import java.util.List;

/**
 * Created by 灌云县公安局 李秉键 on 2017/8/14.
 */

public class TempIncludeItemAdapter extends BaseQuickAdapter<InvolvedPerson,BaseViewHolder> {


    public TempIncludeItemAdapter(@LayoutRes int layoutResId, @Nullable List<InvolvedPerson> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final InvolvedPerson item) {
        switch (item.getType()){
            case Constants.AUTHOR:
                helper.setText(R.id.tv_name,"涉事人员信息-"+item.getInvolved_name());
                break;
            case Constants.CAR:
                helper.setText(R.id.tv_name,"车辆信息-"+item.getCar_no());
                break;

            case Constants.LAWCASE:
                helper.setText(R.id.tv_name,"案情信息-"+item.getThing_());
                break;
        }

    }


    public  void refresh(List<InvolvedPerson> data){
        mData.clear();
        if(null!=data){
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

}
