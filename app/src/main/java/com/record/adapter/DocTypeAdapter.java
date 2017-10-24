package com.record.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.record.R;
import com.record.moudle.entity.DocType;

import java.util.List;

/**
 * Created by 灌云县公安局 李秉键 on 2017/8/14.
 */

public class DocTypeAdapter extends BaseQuickAdapter<DocType,BaseViewHolder> {


    public DocTypeAdapter(@LayoutRes int layoutResId, @Nullable List<DocType> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final DocType item) {

        helper.setText(R.id.tv_title,item.getTitle());

    }


    public  void refresh(List<DocType> data){
        mData.clear();
        if(null!=data){
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

}
