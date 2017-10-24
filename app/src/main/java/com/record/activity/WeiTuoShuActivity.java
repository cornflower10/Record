package com.record.activity;

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

public class WeiTuoShuActivity extends BaseActivity implements ErrorView {


    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.ed_name_9527)
    AppCompatEditText edName9527;
    @BindView(R.id.ed_Q1)
    AppCompatEditText edQ1;
    @BindView(R.id.ed_W1)
    AppCompatEditText edW1;
    @BindView(R.id.ed_R1)
    AppCompatEditText edR1;
    @BindView(R.id.ed_name_9528)
    AppCompatEditText edName9528;
    @BindView(R.id.ed_Q2)
    AppCompatEditText edQ2;
    @BindView(R.id.ed_W2)
    AppCompatEditText edW2;
    @BindView(R.id.ed_R2)
    AppCompatEditText edR2;


    private DocType docType;

    private String _name_9527="", _Q1="", _W1="", _R1="",
            _name_9528="",
            _Q2="", _W2="", _R2="";


    private LawCaseMoulde lawCaseMoulde;
//    private static final int RES = 110;

    @Override
    public int setContentView() {
        return R.layout.activity_weituoshu;
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


    public void onViewClicked(boolean isClick) {
        if(isClick){

            _name_9527 = edit2String(edName9527);
            _Q1 = edit2String(edQ1);
            _W1 = edit2String(edW1);

            _R1 = edit2String(edR1);
            _name_9528 = edit2String(edName9528);
            _Q2 = edit2String(edQ2);

            _W2 = edit2String(edW2);
            _R2 = edit2String(edR2);
        }

        if (TextUtils.isEmpty(_name_9527)
                && TextUtils.isEmpty(_Q1)
                && TextUtils.isEmpty(_W1)
                && TextUtils.isEmpty(_R1)
                && TextUtils.isEmpty(_name_9528)
                && TextUtils.isEmpty(_Q2)
                && TextUtils.isEmpty(_W2)
                && TextUtils.isEmpty(_R2)
                && isClick
                ) {
            showToast("请输入相关内容！");
            return;
        }


        Map<String, String> map = new HashMap<String, String>();
        map.put("$name-9527$", _name_9527);
        map.put("$Q1$", _Q1);
        map.put("$W1$", _W1);
        map.put("$R1$", _R1);
        map.put("$name-9528$", _name_9528);
        map.put("$Q2$", _Q2);
        map.put("$W2$", _W2);
        map.put("$R2$", _R2);

        exportWordAndSave(docType, map, lawCaseMoulde);
    }


    @Override
    public void onError(String msg) {
        showToast(msg);
    }

    @OnClick({R.id.tv_right, R.id.bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            case R.id.tv_right:
//                Intent intent = new Intent(mContext, QueryDocListActivity.class);
//                startActivityForResult(intent, RES);
//                break;
            case R.id.bt:
                onViewClicked(false);
                break;
        }
    }
}
