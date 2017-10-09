package com.record.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.record.R;
import com.record.moudle.entity.LawCase;
import com.record.moudle.entity.Level0Item;
import com.record.moudle.entity.TempInclude;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiejingbao on 2017/10/9.
 */

public class TempIncludeAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity,BaseViewHolder> {

    public static final int GROUP = 1;
    public static final int CHILD = 2;
    private LinearLayoutManager layoutManager;
    private List<LawCase> list = new ArrayList<>();

    public List<LawCase> getList() {
        return list;
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public TempIncludeAdapter(List<MultiItemEntity> data) {
        super(data);
        layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        addItemType(GROUP, R.layout.temp_item);
        addItemType(CHILD, R.layout.temp_item_child);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case GROUP:
                helper.setText(R.id.tv_name, ((Level0Item)item).getTitle());
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = helper.getAdapterPosition();
//                     Log.d(TAG, "Level 0 item pos: " + pos);
                        if (((Level0Item)item).isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }
                    }
                });
                break;
            case CHILD:
               final TempInclude tempInclude = (TempInclude)item;
                helper.setText(R.id.tv_name,tempInclude.getLawCase().getLawCaseTitle());
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                           if(tempInclude.isCheck()){
                               tempInclude.setCheck(false);
                               if(list.size()>0){
                                   list.remove(tempInclude.getLawCase());
                               }

                           }else {
                               tempInclude.setCheck(true);
                               list.add(tempInclude.getLawCase());
                           }
                        helper.setChecked(R.id.acb,tempInclude.isCheck());
                    }
                });
                helper.setChecked(R.id.acb,tempInclude.isCheck());

                break;
        }
    }
}
