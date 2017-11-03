package com.record.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.record.R;
import com.record.adapter.DocTypeAdapter;
import com.record.moudle.entity.DocType;
import com.record.utils.Constants;
import com.record.utils.WordUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TrafficLawsActivity extends BaseActivity {

    @BindView(R.id.rv)
    RecyclerView rv;
    private String [] strings = null;
    private List<DocType> docTypeList = new ArrayList<>();
    private  String path = null ;
    private ProgressDialog progress;

    @Override
    public int setContentView() {
        return R.layout.activity_traffic_laws;
    }

    @Override
    public String setTitleName() {
        return "交通事故相关法律法规列表";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }


    private void initData() {
        try {
            strings = getAssets().list("law");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(null == strings)
            return;

        for (int i = 0; i < strings.length; i++) {
            String name = strings[i].substring(0, strings[i].indexOf("."));

            DocType doc = new DocType();
            doc.setTitle(name);
            doc.setPath(strings[i]);
            docTypeList.add(doc);
        }

        DocTypeAdapter docTypeAdapter = new DocTypeAdapter(R.layout.law_item, docTypeList);
        rv.setAdapter(docTypeAdapter);
        rv.setLayoutManager(new LinearLayoutManager(mContext));
        docTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
                progress = ProgressDialog.show(mContext,"","加载中...");
                progress.setCancelable(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                          path =  WordUtil.copyFile(getAssets().open("law/"+docTypeList.get(position).getPath()),
                                    Constants.LAW_PATH+"/"+docTypeList.get(position).getPath());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progress.dismiss();
                                if(!TextUtils.isEmpty(path)){
                                    WordUtil.doOpenWord(mContext,path);
                                }
                            }
                        });
                    }
                }).start();



            }
        });

    }

}
