package com.record.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.record.R;
import com.record.adapter.DocTypeGroupAdapter;
import com.record.moudle.entity.DocType;
import com.record.utils.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class DocListTypeActivity extends BaseActivity {

    @BindView(R.id.rv)
    RecyclerView rv;
    private String strings[] = null;
    private List<DocType> docTypeList = new ArrayList<>();
    private String type;

    @Override
    public int setContentView() {
        return R.layout.activity_doc_list_type;
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
        }
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
            String name = Constants.SPARSEARRAY_NAME.get(Integer.parseInt(strings[i]));
            docType.setType(Integer.parseInt(strings[i]));
//            docType.setPath(path+name);
            docType.setTitle(name);
            docTypeList.add(docType);
        }

        DocTypeGroupAdapter docTypeAdapter = new DocTypeGroupAdapter(R.layout.doc_type_item,docTypeList);
        rv.setAdapter(docTypeAdapter);
        rv.setLayoutManager(new LinearLayoutManager(mContext));
        docTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                Intent in = new Intent(mContext,DocTypeActivity.class);
                if(!TextUtils.isEmpty(type)&&type.equals(Constants.DOC_SETTING)){
                    in.putExtra(Constants.TYPE,type);
                }
                if(!TextUtils.isEmpty(type)&&type.equals(Constants.DOC_NULL)){
                    in.putExtra(Constants.TYPE,type);
                }
                    in.putExtra("doc",docTypeList.get(position));
                startActivity(in);

            }
        });

    }
}
