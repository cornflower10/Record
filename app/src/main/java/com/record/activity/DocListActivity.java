package com.record.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.record.R;
import com.record.adapter.LawCaseAdapter;
import com.record.moudle.entity.LawCase;
import com.record.moudle.moudleDao.LawCaseMoudleImpl;
import com.record.moudle.moudleDao.LawCaseMoulde;
import com.record.moudle.moudleDao.LawCaseView;
import com.record.utils.Constants;
import com.record.utils.WordUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DocListActivity extends BaseActivity implements LawCaseView{
    @BindView(R.id.rv)
    RecyclerView rv;
    private List<LawCase> list = new ArrayList<>();
    private LawCaseAdapter lawCaseAdapter;
    private LawCaseMoulde lawCaseMoulde;

    @Override
    public int setContentView() {
        return R.layout.activity_doc_list;
    }

    @Override
    public String setTitleName() {
        return "列表";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lawCaseMoulde = new LawCaseMoudleImpl(this);
        list = lawCaseMoulde.seletcAll();
        lawCaseAdapter =new LawCaseAdapter(R.layout.print_record_item,list);
        lawCaseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            LawCase lawCase = lawCaseMoulde.seletcById(list.get(position).getId());
                try {
                    WordUtil.convert2Html(lawCase.getDocPath(),
                            Constants.htmlPath+"/"+
                                    lawCase.getLawCaseTitle()+".html");

                    Intent intent = new Intent(mContext,WordHtmlActivity.class);
                    intent.putExtra("path",lawCase.getDocPath());
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    showToast(e.getMessage());
                }


            }
        });
        rv.setAdapter(lawCaseAdapter);
        rv.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    protected void onResume() {

        lawCaseAdapter.notifyDataSetChanged();
        super.onResume();

    }

    @Override
    public void onError(String msg) {
        showToast(msg);
    }
}
