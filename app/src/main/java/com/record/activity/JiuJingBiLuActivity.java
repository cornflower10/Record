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
import com.record.utils.Constants;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class JiuJingBiLuActivity extends BaseActivity implements ErrorView {


    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.ed_name)
    AppCompatEditText edName;
    @BindView(R.id.ed_ttt)
    AppCompatEditText edTtt;
    @BindView(R.id.ed_yyy)
    AppCompatEditText edYyy;
    @BindView(R.id.ed_idCard1)
    AppCompatEditText edIdCard1;
    @BindView(R.id.ed_6R6R6R)
    AppCompatEditText ed6R6R6R;
    @BindView(R.id.ed_transportationType1)
    AppCompatEditText edTransportationType1;
    @BindView(R.id.ed_2d2d2d)
    AppCompatEditText ed2d2d2d;
    @BindView(R.id.ed_9M9M9M)
    AppCompatEditText ed9M9M9M;
    @BindView(R.id.ed_7N7N7N)
    AppCompatEditText ed7N7N7N;
    @BindView(R.id.ed_0a0a0a)
    AppCompatEditText ed0a0a0a;


    private DocType docType;

    private String _name="", _ttt="", _yyy="", _idCard1="", _6R6R6R="",
            _transportationType1="", _2d2d2d="", _9M9M9M="", _7N7N7N="",_0a0a0a="";

    private LawCaseMoulde lawCaseMoulde;
    private static final int RES = 110;

    @Override
    public int setContentView() {
        return R.layout.activity_jiujingbilu;
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


        if((!TextUtils.isEmpty(getTypeNull()))&&getTypeNull().equals(Constants.DOC_NULL)){
            onViewClicked(false);
        }
    }

    /**
     * 当事人姓名  $name$
     * 性  别      $ttt$
     * 年  龄     $yyy$
     * 身份证（驾驶证）号码    $idCard1$
     * 号牌种类  $6R6R6R$
     * 号牌号码  $transportationType1$
     * 酒精含量测试数值(  $2d2d2d$   mg/100ml)
     * 测试时间  $9M9M9M$
     * 测试地点  $7N7N7N$
     * 对上述测试内容有无异议  $0a0a0a$
     */
    public void onViewClicked(boolean isClick) {
        if(isClick){
            _name = edit2String(edName);
            _ttt = edit2String(edTtt);
            _yyy = edit2String(edYyy);

            _idCard1 = edit2String(edIdCard1);
            _6R6R6R = edit2String(ed6R6R6R);
            _transportationType1 = edit2String(edTransportationType1);

            _2d2d2d = edit2String(ed2d2d2d);
            _9M9M9M = edit2String(ed9M9M9M);
            _7N7N7N= edit2String(ed7N7N7N);
            _0a0a0a = edit2String(ed0a0a0a);
        }


        Map<String, String> map = new HashMap<String, String>();
        map.put("$name$", _name);
        map.put("$ttt$", _ttt);
        map.put("$yyy$", _yyy);
        map.put("$idCard1$", _idCard1);
        map.put("$6R6R6R$", _6R6R6R);
        map.put("$transportationType1$", _transportationType1);
        map.put("$2d2d2d$", _2d2d2d);

        map.put("$9M9M9M$", _9M9M9M);
        map.put("$7N7N7N$", _7N7N7N);
        map.put("$0a0a0a$", _0a0a0a);

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
                onViewClicked(true);
                break;
        }
    }
}
