package com.record.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.record.R;
import com.record.adapter.TempIncludeItemAdapter;
import com.record.moudle.entity.InvolvedPerson;
import com.record.moudle.moudleDao.ErrorView;
import com.record.moudle.moudleDao.InvolvedPersonMoulde;
import com.record.moudle.moudleDao.InvolvedPersonMouldeImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class QueryDocListActivity extends BaseActivity implements ErrorView {
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.tv_right)
    TextView tvRight;
    private TempIncludeItemAdapter tempIncludeAdapter;
    private InvolvedPersonMoulde involvedPersonMoulde;
    private List<InvolvedPerson> list  = new ArrayList<>();

    @Override
    public int setContentView() {
        return R.layout.activity_doc_list;
    }

    @Override
    public String setTitleName() {
        return "模板列表";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tvRight.setVisibility(View.GONE);
//        tvRight.setText("确定");

        involvedPersonMoulde = new InvolvedPersonMouldeImpl(this);
        list = involvedPersonMoulde.seletcAll();
        tempIncludeAdapter = new TempIncludeItemAdapter(R.layout.temp_item_child,list);
        rv.setAdapter(tempIncludeAdapter);
        rv.setLayoutManager(new LinearLayoutManager(mContext));
        tempIncludeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = getIntent();
                intent.putExtra("Receipt", list.get(position));
                setResult(RESULT_OK,intent);
                 finish();
            }
        });
    }

    @Override
    protected void onResume() {

        tempIncludeAdapter.notifyDataSetChanged();
        super.onResume();

    }

    @Override
    public void onError(String msg) {
        showToast(msg);
    }

    @OnClick(R.id.tv_right)
    public void onViewClicked() {
    }
}
