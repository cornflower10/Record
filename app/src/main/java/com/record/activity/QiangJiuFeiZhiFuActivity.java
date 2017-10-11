package com.record.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.TextView;

import com.record.R;
import com.record.moudle.entity.DocType;
import com.record.moudle.moudleDao.ErrorView;
import com.record.moudle.moudleDao.LawCaseMoudleImpl;
import com.record.moudle.moudleDao.LawCaseMoulde;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class QiangJiuFeiZhiFuActivity extends BaseActivity implements ErrorView {


    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.ed_121212)
    AppCompatEditText ed121212;
    @BindView(R.id.ed_name)
    AppCompatEditText edName;
    @BindView(R.id.ed_III)
    AppCompatEditText edIII;
    @BindView(R.id.ed_787878)
    AppCompatEditText ed787878;
    @BindView(R.id.ed_institution_police_a)
    AppCompatEditText edInstitutionPoliceA;


    private DocType docType;

    private String _121212, _name, _III, _787878, _institution_police_a;

    private LawCaseMoulde lawCaseMoulde;
    private static final int RES = 110;

    @Override
    public int setContentView() {
        return R.layout.activity_qiangjiufeizhifu;
    }

    @Override
    public String setTitleName() {
        return "信息录入";
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
    }

    /**
     * 保险公司名称：$121212$
     * 驾驶人姓名 $name$
     * 交通方式、车牌号 $III$
     * 保险凭证号   $787878$
     * 交通警察：$institution_police_a$
     */
    public void onViewClicked() {
        _121212 = edit2String(ed121212);
        _name = edit2String(edName);
        _III = edit2String(edIII);

        _787878 = edit2String(ed787878);
        _institution_police_a = edit2String(edInstitutionPoliceA);

        Map<String, String> map = new HashMap<String, String>();
        map.put("$121212$", _121212);
        map.put("$name$", _name);
        map.put("$III$", _III);
        map.put("$787878$", _787878);
        map.put("$institution_police_a$", _institution_police_a);

        exportWordAndSave(docType, map, lawCaseMoulde);
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
                onViewClicked();
                break;
        }
    }
}
