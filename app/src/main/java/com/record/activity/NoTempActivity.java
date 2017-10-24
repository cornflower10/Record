package com.record.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.record.App;
import com.record.R;
import com.record.moudle.entity.DocType;
import com.record.moudle.entity.LawCase;
import com.record.moudle.moudleDao.ErrorView;
import com.record.moudle.moudleDao.LawCaseMoudleImpl;
import com.record.moudle.moudleDao.LawCaseMoulde;
import com.record.utils.Constants;
import com.record.utils.TimeUtils;
import com.record.utils.WordUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class NoTempActivity extends BaseActivity implements ErrorView {


    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.ed_name)
    AppCompatEditText edName;
    @BindView(R.id.ed_anyou)
    AppCompatEditText edAnyou;
    @BindView(R.id.ed_time)
    AppCompatEditText edTime;
    @BindView(R.id.ed_address)
    AppCompatEditText edAddress;
    @BindView(R.id.ed_number)
    AppCompatEditText edNumber;
    @BindView(R.id.ed_company_info)
    AppCompatEditText edCompanyInfo;

    private DocType docType;

    private static final String outPath = Constants.docPath;

    private String name="",anyou="",time="",address="",number="",companyInfo="";

    private LawCaseMoulde lawCaseMoulde;
    private static final int RES = 110;
    private static final String M = "¥:";

    @Override
    public int setContentView() {
        return R.layout.activity_chuanhuanzheng;
    }

    @Override
    public String setTitleName() {
        return "传唤证";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        tv_right.setVisibility(View.GONE);
        tv_right.setText("导入模板");
        if (null != getIntent()) {
            docType = getIntent().getParcelableExtra("doc");
        }
        lawCaseMoulde = new LawCaseMoudleImpl(this);

        if((!TextUtils.isEmpty(getTypeNull()))&&getTypeNull().equals(Constants.DOC_NULL)){
            onViewClicked(false);
        }
    }

    public void onViewClicked(boolean isClick) {

        if(isClick){
            name = edit2String(edName);
            anyou = edit2String(edAnyou);
            time = edit2String(edTime);

            address = edit2String(edAddress);
            number = edit2String(edNumber);
            companyInfo = edit2String(edCompanyInfo);
        }


        Map<String, String> map = new HashMap<String, String>();
        map.put("$name$", name);
        map.put("$6H6H6H$", anyou);
        map.put("$5EW5EW$", time);
        map.put("$3VB3VB$", address);
        map.put("$pageNum$", number);
        map.put("$institution_name$",companyInfo );

        try {
            String outPathName = outPath + "/" + docType.getTitle() + TimeUtils.currentTimeMillis() + ".doc";
            WordUtil.doScan(getAssets().open("doc/" + docType.getType() + "/" + docType.getTitle() + ".doc"), outPathName, map);

            LawCase lawCase = new LawCase();
            lawCase.setType(docType.getType());
            lawCase.setLawCaseTitle(docType.getTitle());
            lawCase.setIsPrint(false);
            lawCase.setDate(TimeUtils.currentTimeMillis());
            lawCase.setDocPath(outPathName);
            if (lawCaseMoulde.addLawCase(lawCase)) {
                Intent in = new Intent(mContext, DocListActivity.class);
                startActivity(in);
                showToast("生成成功");
                App.getInstance().getForegroundCallbacks()
                        .finishActivity(DocTypeActivity.class);
                App.getInstance().getForegroundCallbacks()
                        .finishActivity(DocListTypeActivity.class);
                finish();
            }
        } catch (IOException e) {
            e.printStackTrace();
            showToast("生成失败" + e.getMessage());
        }
    }

    @Override
    public void onError(String msg) {
        showToast(msg);
    }

    @OnClick({R.id.tv_right, R.id.bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_right:
                Intent intent = new Intent(mContext, QueryDocListActivity.class);
                startActivityForResult(intent, RES);
                break;
            case R.id.bt:
                onViewClicked(true);
                break;
        }
    }
}
