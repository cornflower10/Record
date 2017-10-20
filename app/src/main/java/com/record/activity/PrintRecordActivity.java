package com.record.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.record.R;
import com.record.adapter.LawCaseAdapter;
import com.record.customview.CustomBottomSheet;
import com.record.moudle.entity.LawCase;
import com.record.moudle.moudleDao.ErrorView;
import com.record.moudle.moudleDao.LawCaseMoudleImpl;
import com.record.moudle.moudleDao.LawCaseMoulde;
import com.record.utils.TimeUtils;
import com.record.utils.WordUtil;

import java.util.List;

import butterknife.BindView;

public class PrintRecordActivity extends BaseActivity implements ErrorView {
    @BindView(R.id.rv)
    RecyclerView rv;
    private LawCaseMoulde lawCaseMoulde;
    private List<LawCase> list;
    private LawCaseAdapter lawCaseAdapter;
    private CustomBottomSheet customBottomSheet;
    private long id;

    @Override
    public int setContentView() {
        return R.layout.activity_print_record;
    }

    @Override
    public String setTitleName() {
        return "打印记录";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initBottomSheet();
    }


    private void initData() {
        lawCaseMoulde = new LawCaseMoudleImpl(this);
        list = lawCaseMoulde.seletcAll();
        lawCaseAdapter = new LawCaseAdapter(R.layout.print_record_item, list);
        lawCaseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LawCase lawCase = lawCaseMoulde.seletcById(list.get(position).getId());
                try {
                    WordUtil.convert2Html(lawCase.getDocPath(),
                            Environment.getExternalStorageDirectory().getPath() + "/" +
                                    lawCase.getLawCaseTitle() + TimeUtils.currentTimeMillis() + ".html");

                    Intent intent = new Intent(mContext, WordHtmlActivity.class);
                    intent.putExtra("path", lawCase.getDocPath());
                    intent.putExtra("printRecordFragment", true);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    ((MainActivity) mContext).showToast(e.getMessage());
                }


            }
        });
        lawCaseAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                id = list.get(position).getId();
                showBottomSheet();
                return false;
            }
        });

        rv.setAdapter(lawCaseAdapter);
        rv.setLayoutManager(new LinearLayoutManager(mContext));

    }


    private void initBottomSheet() {
        customBottomSheet = new CustomBottomSheet(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.bottom_sheet, null);
        view.findViewById(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lawCaseMoulde.deleteById(id);
                dismissBottomSheet();
                lawCaseAdapter.refresh(lawCaseMoulde.seletcAll());
            }
        });
        view.findViewById(R.id.tv_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, lawCaseMoulde.seletcById(id).getDocPath(), Toast.LENGTH_LONG).show();

            }
        });
        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissBottomSheet();
            }
        });
        customBottomSheet.setContentView(view);

    }

    private void showBottomSheet() {
        if (null != customBottomSheet && !customBottomSheet.isShowing()) {
            customBottomSheet.show();
        }
    }

    private void dismissBottomSheet() {
        if (null != customBottomSheet && customBottomSheet.isShowing()) {
            customBottomSheet.dismiss();
        }
    }

    @Override
    public void onError(String msg) {
        showToast(msg);
    }
}
