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

public class XingZhengChuFaBiLuActivity extends BaseActivity implements ErrorView {


    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.ed_789789)
    AppCompatEditText ed789789;
    @BindView(R.id.ed_987987)
    AppCompatEditText ed987987;
    @BindView(R.id.ed_456456)
    AppCompatEditText ed456456;
    @BindView(R.id.ed_654654)
    AppCompatEditText ed654654;
    @BindView(R.id.ed_321321)
    AppCompatEditText ed321321;
    @BindView(R.id.ed_753753)
    AppCompatEditText ed753753;
    @BindView(R.id.ed_123QWE)
    AppCompatEditText ed123QWE;
    @BindView(R.id.ed_357357)
    AppCompatEditText ed357357;
    @BindView(R.id.ed_159159)
    AppCompatEditText ed159159;


    private DocType docType;

    private String _789789, _987987, _456456, _654654, _321321, _753753,_123QWE,_357357,_159159;

    private LawCaseMoulde lawCaseMoulde;
    private static final int RES = 110;

    @Override
    public int setContentView() {
        return R.layout.activity_xingzhengchufagaozhi;
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
     * 被告知人： $789789$
     * 被告知单位名称 $987987$
     * 法定告知单位  $456456$
     * 告知人    $654654$
     * 代表人   $321321$
     * 案件事实   $753753$
     * 依据条款  $123QWE$
     * 拟作出处罚   $357357$
     * 听证单位     $159159$
     */
    public void onViewClicked(boolean isClick) {
        if(isClick){
            _789789 = edit2String(ed789789);
            _987987 = edit2String(ed987987);
            _456456 = edit2String(ed456456);

            _654654 = edit2String(ed654654);
            _321321 = edit2String(ed321321);
            _753753 = edit2String(ed753753);

            _123QWE = edit2String(ed123QWE);
            _357357 = edit2String(ed357357);
            _159159 = edit2String(ed159159);
        }

        Map<String, String> map = new HashMap<String, String>();
        map.put("$789789$", _789789);
        map.put("$987987$", _987987);
        map.put("$456456$", _456456);
        map.put("$654654$", _654654);
        map.put("$321321$", _321321);
        map.put("$753753$", _753753);

        map.put("$123QWE$", _123QWE);
        map.put("$357357$", _357357);
        map.put("$159159$", _159159);

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
