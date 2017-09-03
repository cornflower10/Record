package com.record.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;

import com.record.App;
import com.record.R;
import com.record.moudle.entity.DocType;
import com.record.moudle.entity.LawCase;
import com.record.moudle.moudleDao.LawCaseMoudleImpl;
import com.record.moudle.moudleDao.LawCaseMoulde;
import com.record.moudle.moudleDao.LawCaseView;
import com.record.utils.Constants;
import com.record.utils.TimeUtils;
import com.record.utils.WordUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class RecordDocInfoActivity extends BaseActivity implements LawCaseView {

    @BindView(R.id.ed_name)
    AppCompatEditText edName;
    @BindView(R.id.ed_money)
    AppCompatEditText edMoney;
    private DocType docType;
    private static final String outPath = Constants.docPath;
    private String name ;
    private String money;
    private LawCaseMoulde lawCaseMoulde;

    @Override
    public int setContentView() {
        return R.layout.activity_record_doc_info;
    }

    @Override
    public String setTitleName() {
        return "录入信息";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (null != getIntent()) {
            docType = getIntent().getParcelableExtra("doc");
//            Log.i("RecordDocInfoActivity", docType.getPath());
        }
        lawCaseMoulde = new LawCaseMoudleImpl(this);
    }

    @OnClick(R.id.bt)
    public void onViewClicked() {
        name = edName.getText().toString().trim();
        money = edMoney.getText().toString().trim();
        Map<String, String> map = new HashMap<String, String>();
        map.put("$NAME$", name);
        map.put("$MONEY$", money);
        try {
            String outPathName = outPath+"/"+docType.getTitle()+TimeUtils.currentTimeMillis()+".doc";
            WordUtil.doScan(getAssets().open("doc/"+docType.getTitle()+".doc"),outPathName ,map);

            LawCase lawCase =new LawCase();
            lawCase.setLawCaseTitle(docType.getTitle());
            lawCase.setName(name);
            lawCase.setMoney(money);
            lawCase.setIsPrint(false);
            lawCase.setDate(TimeUtils.currentTimeMillis());
            lawCase.setDocPath(outPathName);
            if(lawCaseMoulde.addLawCase(lawCase)){
                Intent in = new Intent(mContext,DocListActivity.class);
                startActivity(in);
                showToast("生成成功");
                App.getInstance().getForegroundCallbacks().finishActivity(DocTypeActivity.class);
                finish();
            }
//            WordUtil.convert2Html(outPathName,);
        } catch (IOException e) {
            e.printStackTrace();
            showToast("生成失败"+e.getMessage());
        }
    }

    @Override
    public void onError(String msg) {
        showToast(msg);
    }
}
