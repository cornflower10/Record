package com.record.activity;

import android.content.Intent;
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

public class DocTypeActivity extends BaseActivity {
    @BindView(R.id.rv)
    RecyclerView rv;
    private String[] strings = null;
    private List<DocType> docTypeList = new ArrayList<>();
    private String type;
    private DocType docType;

    @Override
    public int setContentView() {
        return R.layout.activity_doc_type;
    }

    @Override
    public String setTitleName() {
        if(!TextUtils.isEmpty(type)&&type.equals(Constants.DOC_SETTING)){
            return "常用文书模板设置";
        }else
        return "文书类型";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(null!= getIntent()){
            type = getIntent().getStringExtra(Constants.TYPE);
            docType = getIntent().getParcelableExtra("doc");
        }
        super.onCreate(savedInstanceState);
        initData();

    }

    private void initData() {
        if(null == docType)
            return;
        try {
            strings = getAssets().list("doc/"+docType.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < strings.length; i++) {
            DocType doc = new DocType();
            String name = strings[i];
            doc.setType(docType.getType());
            doc.setTitle(name.substring(0, name.indexOf(".")));
            docTypeList.add(doc);
        }

        DocTypeAdapter docTypeAdapter = new DocTypeAdapter(R.layout.doc_type_item,docTypeList);
        rv.setAdapter(docTypeAdapter);
        rv.setLayoutManager(new LinearLayoutManager(mContext));
        docTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(!TextUtils.isEmpty(type)&&type.equals(Constants.DOC_SETTING)){
                    String path = Constants.htmlPath+"/"+docTypeList.get(position).getTitle()+".html";
                    try {
                        WordUtil.convert2HtmlWithStream(
                                getAssets().open("doc/"+docType.getType()+"/"+docTypeList.get(position).getTitle()+".doc"),
                               path );
                        Intent in = new Intent(mContext,WordHtmlActivity.class);
                        in.putExtra("path",path);
                        startActivity(in);
                    } catch (Exception e) {
                        showToast("查看模板失败"+e.getMessage());
                        e.printStackTrace();
                    }
                }else {
                    Intent in = new Intent(mContext,RecordDocInfoActivity.class);
                    in.putExtra("doc",docTypeList.get(position));
                    startActivity(in);
                }


            }
        });

    }


}
