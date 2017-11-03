package com.record.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.record.R;
import com.record.moudle.entity.ImageLoad;
import com.record.utils.imageload.ImageLoaderUtil;

import java.util.List;

/**
 * Created by 灌云县公安局 李秉键 on 2017/8/14.
 */

public class LoadGifAdapter extends BaseQuickAdapter<ImageLoad,BaseViewHolder> {


    public LoadGifAdapter(@LayoutRes int layoutResId, @Nullable List<ImageLoad> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ImageLoad item) {
        ImageLoaderUtil.getInstance().loadGif(item.getUrl(),(ImageView) helper.getView(R.id.iv),0);
    }


    public  void refresh(List<ImageLoad> data){
        mData.clear();
        if(null!=data){
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

}
