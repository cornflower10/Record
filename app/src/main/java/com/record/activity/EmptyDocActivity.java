package com.record.activity;

import android.os.Bundle;
import android.text.TextUtils;
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

public class EmptyDocActivity extends BaseActivity implements ErrorView {


    @BindView(R.id.tv_right)
    TextView tv_right;

    private DocType docType;

    private String _name_9527="", _Q1="", _W1="", _R1="",
            _name_9528="",
            _Q2="", _W2="", _R2="";


    private LawCaseMoulde lawCaseMoulde;
//    private static final int RES = 110;

    @Override
    public int setContentView() {
        return R.layout.activity_empty_world;
    }

    @Override
    public String setTitleName() {
        return "信息录入";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (null != getIntent()) {
            docType = getIntent().getParcelableExtra("doc");
        }
        lawCaseMoulde = new LawCaseMoudleImpl(this);


        if((!TextUtils.isEmpty(getTypeNull()))&&getTypeNull().equals(Constants.DOC_NULL)){
            onViewClicked();
        }
    }


    public void onViewClicked() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("$565656$", _name_9527);
        map.put("$898989$", _Q1);
        map.put("$institution_police_a$", _W1);

        exportWordAndSave(docType, map, lawCaseMoulde);
    }


    @Override
    public void onError(String msg) {
        showToast(msg);
    }
}
