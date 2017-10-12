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
import com.record.moudle.entity.User;
import com.record.moudle.moudleDao.ErrorView;
import com.record.moudle.moudleDao.InvolvedPersonMoulde;
import com.record.moudle.moudleDao.InvolvedPersonMouldeImpl;
import com.record.moudle.moudleDao.LawCaseMoudleImpl;
import com.record.moudle.moudleDao.LawCaseMoulde;
import com.record.moudle.moudleDao.UserMoulde;
import com.record.moudle.moudleDao.UserMouldeImpl;
import com.record.utils.Constants;
import com.record.utils.TimeUtils;
import com.record.utils.WordUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class WuPinFaHuanZhengActivity extends BaseActivity implements ErrorView {


    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.ed_institution_name)
    AppCompatEditText edInstitutionName;
    @BindView(R.id.ed_name)
    AppCompatEditText edName;
    @BindView(R.id.ed_6YH6YH)
    AppCompatEditText ed6YH6YH;
    @BindView(R.id.ed_car_no)
    AppCompatEditText edCarNo;
    @BindView(R.id.ed_19D19D)
    AppCompatEditText ed19D19D;
    @BindView(R.id.ed_73W73W)
    AppCompatEditText ed73W73W;
    @BindView(R.id.ed_05K05K)
    AppCompatEditText ed05K05K;

    private DocType docType;
    private static final String outPath = Constants.docPath;
    private String _institution_name, _name, _6YH6YH,
            _car_no,
            _19D19D, _73W73W,
            _05K05K;

    private LawCaseMoulde lawCaseMoulde;

    private static final int RES = 110;

    private InvolvedPersonMoulde involvedPersonMoulde;

    private InvolvedPerson involvedPerson;
    private UserMoulde userMoulde;

    @Override
    public int setContentView() {
        return R.layout.activity_wupinfahuan;
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
        userMoulde  = new UserMouldeImpl(this);
        User user = userMoulde.seletcUser();
        if(null!=user){
            edInstitutionName.setText(user.getInstitutionName());
        }
    }

    /**
     * 执法单位信息  $institution_name$
     * 物品所有人  $name$
     * 扣留物品种类  $6YH6YH$
     * 号牌       $car_no$
     * 扣留原因    $19D19D$
     * 扣留日期 $73W73W$
     * 发还日期  $05K05K$
     */
    public void onViewClicked() {
        _institution_name = edit2String(edInstitutionName);
        _name = edit2String(edName);
        _6YH6YH = edit2String(ed6YH6YH);

        _car_no = edit2String(edCarNo);
        _19D19D = edit2String(ed19D19D);
        _73W73W = edit2String(ed73W73W);

        _05K05K = edit2String(ed05K05K);

        if (TextUtils.isEmpty(_name)
                && TextUtils.isEmpty(_institution_name)
                && TextUtils.isEmpty(_name)
                && TextUtils.isEmpty(_6YH6YH)
                && TextUtils.isEmpty(_car_no)
                && TextUtils.isEmpty(_19D19D)
                && TextUtils.isEmpty(_73W73W)
                && TextUtils.isEmpty(_05K05K)
                ) {
            showToast("请输入相关内容！");
            return;
        }

        Map<String, String> map = new HashMap<String, String>();
        map.put("$institution_name$", _institution_name);
        map.put("$name$", _name);
        map.put("$6YH6YH$", _6YH6YH);
        map.put("$car_no$", _car_no);
        map.put("$19D19D$", _19D19D);
        map.put("$73W73W$", _73W73W);
        map.put("$05K05K$", _05K05K);

        try {
            String outPathName = outPath + "/" + docType.getTitle() + TimeUtils.currentTimeMillis() + ".doc";
            WordUtil.doScan(getAssets().open("doc/" + docType.getType() + "/" + docType.getTitle() + ".doc"), outPathName, map);

            LawCase lawCase = new LawCase();
            lawCase.setType(docType.getType());
            lawCase.setLawCaseTitle(docType.getTitle());
            lawCase.setIsPrint(false);
            lawCase.setDate(TimeUtils.currentTimeMillis());
            lawCase.setDocPath(outPathName);

            if (null != involvedPerson) {
                involvedPerson.setType(Constants.CAR);
                if (TextUtils.isEmpty(involvedPerson.getCar_no())) {
                    involvedPerson.setCar_no(_car_no);
                }

                involvedPerson.setDate(System.currentTimeMillis());
                involvedPersonMoulde.upDateInvolved(involvedPerson);
            } else {
                involvedPerson = new InvolvedPerson();
                involvedPerson.setType(Constants.CAR);
                if (TextUtils.isEmpty(involvedPerson.getCar_no())) {
                    involvedPerson.setCar_no(_car_no);
                }
                involvedPerson.setDate(System.currentTimeMillis());
                involvedPersonMoulde.addInvolved(involvedPerson);
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
                onViewClicked();
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
                    edCarNo.setText(involvedPerson.getCar_no());
                }
            }


        }
    }

}
