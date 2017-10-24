package com.record.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.record.R;
import com.record.moudle.entity.DocType;
import com.record.utils.Constants;

import java.util.List;

/**
 * Created by 灌云县公安局 李秉键 on 2017/9/12.
 */

public class DocTypeGroupAdapter extends BaseQuickAdapter<DocType,BaseViewHolder> {


    public DocTypeGroupAdapter(@LayoutRes int layoutResId, @Nullable List<DocType> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final DocType item) {

        helper.setText(R.id.tv_title,item.getTitle());
        switch (item.getType()){
            case Constants.TYPE_ACCI:
                helper.setImageResource(R.id.iv,R.mipmap.traffic_accident);
                break;
            case Constants.TYPE_CRIMINAL:
                helper.setImageResource(R.id.iv,R.mipmap.criminal_case);
                break;
            case Constants.TYPE_ADMINISTRATIVE_CASE:
                helper.setImageResource(R.id.iv,R.mipmap.administrative_case);
                break;
            case Constants.TYPE_ADMINISTRATIVE_DOC:
                helper.setImageResource(R.id.iv,R.mipmap.administrative_documents);
                break;
            default:
                break;

        }

    }


    public  void refresh(List<DocType> data){
        mData.clear();
        if(null!=data){
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }
}
