package com.record.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
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

public class AnJianShenHeDengJiActivity extends BaseActivity implements ErrorView {


    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.ed_name7979)
    AppCompatEditText edName7979;
    @BindView(R.id.ed_5F5F5F)
    AppCompatEditText ed5F5F5F;
    @BindView(R.id.ed_6H6H6H)
    AppCompatEditText ed6H6H6H;
    @BindView(R.id.ed_7J7J7J)
    AppCompatEditText ed7J7J7J;
    @BindView(R.id.ed_9K9K9K)
    AppCompatEditText ed9K9K9K;


    private DocType docType;

    private String _name7979, _5F5F5F, _6H6H6H, _7J7J7J, _9K9K9K;

    private LawCaseMoulde lawCaseMoulde;
    private static final int RES = 110;

    @Override
    public int setContentView() {
        return R.layout.activity_anjianshenhedengji;
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
     * 承办单位： $456MNB$
     * 承办人 $123VCX$
     * 呈报事项  $789POI$
     * 违法/犯罪嫌疑人基本情况    $745DFG$
     * 简要案情   $965RGN$
     */
    public void onViewClicked() {

        _name7979 = edit2String(edName7979);
        _5F5F5F = edit2String(ed5F5F5F);
        _6H6H6H = edit2String(ed6H6H6H);

        _7J7J7J = edit2String(ed7J7J7J);
        _9K9K9K = edit2String(ed9K9K9K);


        if(TextUtils.isEmpty(_name7979)
                &&TextUtils.isEmpty(_5F5F5F)
                &&TextUtils.isEmpty(_6H6H6H)
                &&TextUtils.isEmpty(_7J7J7J)
                &&TextUtils.isEmpty(_9K9K9K)
                ){
            showToast("请输入相关内容！");
            return;
        }

        Map<String, String> map = new HashMap<String, String>();
        map.put("$456MNB$", _name7979);
        map.put("$123VCX$", _5F5F5F);
        map.put("$789POI$", _6H6H6H);
        map.put("$745DFG$", _7J7J7J);
        map.put("$965RGN$", _9K9K9K);
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
