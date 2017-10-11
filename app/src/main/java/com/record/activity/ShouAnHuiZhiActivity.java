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

public class ShouAnHuiZhiActivity extends BaseActivity implements ErrorView {


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
    @BindView(R.id.ed_1B1B1B)
    AppCompatEditText ed1B1B1B;


    private DocType docType;

    private String _Name7979, _5F5F5F, _6H6H6H, _7J7J7J, _9K9K9K, _1B1B1B;

    private LawCaseMoulde lawCaseMoulde;
    private static final int RES = 110;

    @Override
    public int setContentView() {
        return R.layout.activity_shouanhuizhi;
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
     报警人 $name7979$
     报警时间 $5F5F5F$
     案由 $6H6H6H$
     公安单位固定电话   $7J7J7J$
     公安联系人及方式   $9K9K9K$
     发文时间   $1B1B1B$
     */
    public void onViewClicked() {
        _Name7979 = edit2String(edName7979);
        _5F5F5F = edit2String(ed5F5F5F);
        _6H6H6H = edit2String(ed6H6H6H);

        _7J7J7J = edit2String(ed7J7J7J);
        _9K9K9K = edit2String(ed9K9K9K);
        _1B1B1B = edit2String(ed1B1B1B);

        Map<String, String> map = new HashMap<String, String>();
        map.put("$name7979$", _Name7979);
        map.put("$5F5F5F$", _5F5F5F);
        map.put("$6H6H6H$", _6H6H6H);
        map.put("$7J7J7J$", _7J7J7J);
        map.put("$9K9K9K$", _9K9K9K);
        map.put("$1B1B1B$", _1B1B1B);
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
