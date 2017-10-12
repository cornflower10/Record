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
                   String title = docTypeList.get(position).getTitle();
                    Intent intent = new Intent();

                    if(title.equals("收条")){
                        intent.setClass(mContext,RecordDocInfoActivity.class);

                    }else if(title.equals("传唤证")){
                        intent.setClass(mContext,NoTempActivity.class);

                    }else if(title.equals("公安行政处罚告知笔录")){
                        intent.setClass(mContext,XingZhengChuFaBiLuActivity.class);
                    }
                    else if(title.equals("受案回执")){
                        intent.setClass(mContext,ShouAnHuiZhiActivity.class);
                    }
                    else if(title.equals("呼气酒精含量测试笔录")){
                        intent.setClass(mContext,JiuJingBiLuActivity.class);
                    }
                    else if(title.equals("委托书")){
                        intent.setClass(mContext,WeiTuoShuActivity.class);
                    }
                    else if(title.equals("当事人血样（尿样）提取登记表")){
                        intent.setClass(mContext,XueYeNiaoJianActivity.class);
                    }
                    else if(title.equals("扣（留）押物品发还凭证")){
                        intent.setClass(mContext,WuPinFaHuanZhengActivity.class);
                    }
                    else if(title.equals("报告书")){
                        intent.setClass(mContext,ErrorActivity.class);
                    }
                    else if(title.equals("案件审核登记表")){
                        intent.setClass(mContext,AnJianShenHeDengJiActivity.class);
                    }
                    else if(title.equals("道路交通事故伤者医疗规定告知书")){
                        intent.setClass(mContext,ErrorActivity.class);
                    }
                    else if(title.equals("道路交通事故处理调查报告书")){
                        intent.setClass(mContext,JiaoTongDiaoChaBaoGaoActivity.class);
                    }
                    else if(title.equals("道路交通事故当事人陈述材料")){
                        intent.setClass(mContext,JiaoTongDangShiRenActivity.class);
                    }
                    else if(title.equals("道路交通事故抢救费支付（垫付）通知书")){
                        intent.setClass(mContext,QiangJiuFeiZhiFuActivity.class);
                    }
                    else if(title.equals("道路交通事故损害赔偿调解书")){
                        intent.setClass(mContext,JiaoTongTiaoJieShuActivity.class);
                    }
                    else if(title.equals("道路交通事故认定书 (2)")){
                        intent.setClass(mContext,JiaoTongShiGuRenDingActivity.class);
                    }
                    else if(title.equals("道路交通事故认定书")){
                        intent.setClass(mContext,JiaoTongShiGuRenDingJYActivity.class);
                    }
                    else if(title.equals("道路交通事故证明")){
                        intent.setClass(mContext,JiaoTongShiGuZhengMingActivity.class);
                    }
                    else {
                        intent.setClass(mContext,ErrorActivity.class);
                    }
                    intent.putExtra("doc",docTypeList.get(position));
                    startActivity(intent);
                }


            }
        });

    }


}
