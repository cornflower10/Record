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
import com.record.moudle.entity.InvolvedPerson;
import com.record.moudle.entity.LawCase;
import com.record.moudle.moudleDao.ErrorView;
import com.record.moudle.moudleDao.InvolvedPersonMoulde;
import com.record.moudle.moudleDao.InvolvedPersonMouldeImpl;
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

public class QianKeLieJiActivity extends BaseActivity implements ErrorView {


    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.ed_involved_name)
    AppCompatEditText edInvolvedName;
    @BindView(R.id.ed_98765Q)
    AppCompatEditText ed98765Q;
    @BindView(R.id.ed_65432H)
    AppCompatEditText ed65432H;

    @BindView(R.id.ed_756942D)
    AppCompatEditText ed756942D;
    @BindView(R.id.ed_745896J)
    AppCompatEditText ed745896J;
    @BindView(R.id.ed_12345W)
    AppCompatEditText ed12345W;


    private DocType docType;
    private static final String outPath = Constants.docPath;
    private String _involved_name, _98765Q,_12345W, _65432H,

            _756942D, _745896J;

    private LawCaseMoulde lawCaseMoulde;
    private static final int RES = 110;

    private InvolvedPersonMoulde involvedPersonMoulde;

    private InvolvedPerson involvedPerson;

    @Override
    public int setContentView() {
        return R.layout.activity_xingshianjianwenshu;
    }

    @Override
    public String setTitleName() {
        return "录入信息";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        tv_right.setVisibility(View.VISIBLE);
        tv_right.setText("导入模板");
//        titleRightIv.setImageResource(R.drawable.search);
        if (null != getIntent()) {
            docType = getIntent().getParcelableExtra("doc");
//            Log.i("RecordDocInfoActivity", docType.getPath());
        }
        lawCaseMoulde = new LawCaseMoudleImpl(this);
        involvedPersonMoulde = new InvolvedPersonMouldeImpl(this);


        if ((!TextUtils.isEmpty(getTypeNull())) && getTypeNull().equals(Constants.DOC_NULL)) {
            onViewClicked(false);
        }

    }

    /**
     * 前科劣迹调查情况  所需规则
     * 嫌疑人名称  $involved_name$
     * 平台名称   $98765Q$
     * 查询情况   $12345W$
     * 案发时间    $65432H$
     * 嫌疑人名称   $95135N$
     * 嫌疑人基本信息   $756942D$
     * 查询单位    $745896J$
     *
     * @param isClick
     */

    public void onViewClicked(boolean isClick) {
        if (!isClick) {
            tv_right.setVisibility(View.GONE);
        }
        if (isClick) {
            _involved_name = edit2String(edInvolvedName);
            _98765Q = edit2String(ed98765Q);
            _12345W = edit2String(ed12345W);
            _65432H = edit2String(ed65432H);

            _756942D = edit2String(ed756942D);
            _745896J = edit2String(ed745896J);

            if (TextUtils.isEmpty(_involved_name)
                    && TextUtils.isEmpty(_98765Q)
                    && TextUtils.isEmpty(_12345W)
                    && TextUtils.isEmpty(_65432H)
                    && TextUtils.isEmpty(_756942D)
                    && TextUtils.isEmpty(_745896J)
                    ) {
                showToast("请输入相关内容！");
                return;
            }
        }


        Map<String, String> map = new HashMap<String, String>();
        map.put("$involved_name$", _involved_name);
        map.put("$98765Q$", _98765Q);
        map.put("$12345W$", _12345W);
        map.put("$65432H$", _65432H);
        map.put("$756942D$", _756942D);
        map.put("$745896J$", _745896J);

        try {
            String outPathName = outPath + "/" + docType.getTitle() + TimeUtils.currentTimeMillis() + ".doc";
            WordUtil.doScan(getAssets().open("doc/" + docType.getType() + "/" + docType.getTitle() + ".doc"), outPathName, map);

            LawCase lawCase = new LawCase();
            lawCase.setType(docType.getType());
            lawCase.setLawCaseTitle(docType.getTitle());
            lawCase.setIsPrint(false);
            lawCase.setDate(TimeUtils.currentTimeMillis());
            lawCase.setDocPath(outPathName);
            if (isClick) {
                if (null != involvedPerson) {
                    involvedPerson.setType(Constants.AUTHOR);
                    if (TextUtils.isEmpty(involvedPerson.getInvolved_name())) {
                        involvedPerson.setInvolved_name(_involved_name);
                    }
                    involvedPerson.setDate(System.currentTimeMillis());
                    involvedPersonMoulde.upDateInvolved(involvedPerson);
                } else {
                    involvedPerson = new InvolvedPerson();
                    involvedPerson.setType(Constants.AUTHOR);
                    if (TextUtils.isEmpty(involvedPerson.getInvolved_name())) {
                        involvedPerson.setInvolved_name(_involved_name);
                    }
                    involvedPerson.setDate(System.currentTimeMillis());
                    involvedPersonMoulde.addInvolved(involvedPerson);
                }
            }

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
//            WordUtil.convert2Html(outPathName,);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RES) {
            if (resultCode == RESULT_OK) {
                involvedPerson = data.getParcelableExtra("Receipt");
                if (null != involvedPerson) {
                    edInvolvedName.setText(involvedPerson.getInvolved_name());
                }
            }


        }
    }

}
