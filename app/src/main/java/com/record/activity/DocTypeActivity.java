package com.record.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.record.R;
import com.record.adapter.DocTypeAdapter;
import com.record.moudle.entity.DocType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DocTypeActivity extends BaseActivity {
    @BindView(R.id.rv)
    RecyclerView rv;
    private String[] strings = null;
    private List<DocType> docTypeList = new ArrayList<>();

    @Override
    public int setContentView() {
        return R.layout.activity_doc_type;
    }

    @Override
    public String setTitleName() {
        return "文书类型";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();

    }

    private void initData() {
        try {
            strings = getAssets().list("doc");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < strings.length; i++) {
            DocType docType = new DocType();
            String name = strings[i];
//            docType.setPath(path+name);
            docType.setTitle(name.substring(0, name.indexOf(".")));
            docTypeList.add(docType);
        }

        DocTypeAdapter docTypeAdapter = new DocTypeAdapter(R.layout.doc_type_item,docTypeList);
        rv.setAdapter(docTypeAdapter);
        rv.setLayoutManager(new LinearLayoutManager(mContext));
        docTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent in = new Intent(mContext,RecordDocInfoActivity.class);
                in.putExtra("doc",docTypeList.get(position));
                startActivity(in);

            }
        });

    }


}
