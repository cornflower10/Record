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

public class JiaoTongDangShiRenActivity extends BaseActivity implements ErrorView {


    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.ed_name)
    AppCompatEditText edName;
    @BindView(R.id.ed_involved_sex)
    AppCompatEditText edInvolvedSex;
    @BindView(R.id.ed_involved_nationality)
    AppCompatEditText edInvolvedNationality;
    @BindView(R.id.ed_involved_birth_date)
    AppCompatEditText edInvolvedBirthDate;
    @BindView(R.id.ed_involved_idcard)
    AppCompatEditText edInvolvedIdcard;
    @BindView(R.id.ed_involved_native_place)
    AppCompatEditText edInvolvedNativePlace;
    @BindView(R.id.ed_involved_degree)
    AppCompatEditText edInvolvedDegree;
    @BindView(R.id.ed_A)
    AppCompatEditText edA;
    @BindView(R.id.ed_car_no)
    AppCompatEditText edCarNo;
    @BindView(R.id.ed_B)
    AppCompatEditText edB;
    @BindView(R.id.ed_C)
    AppCompatEditText edC;
    @BindView(R.id.ed_D)
    AppCompatEditText edD;
    @BindView(R.id.ed_E)
    AppCompatEditText edE;
    @BindView(R.id.ed_F)
    AppCompatEditText edF;
    @BindView(R.id.ed_G)
    AppCompatEditText edG;


    private DocType docType;
    private static final String outPath = Constants.docPath;
    private String _name, _involved_sex, _involved_nationality,
            _involved_birth_date,
            _involved_idcard, _involved_native_place,
            _involved_degree, _A, _car_no, _B, _C,_D,_E,_F,_G;

    private LawCaseMoulde lawCaseMoulde;
    private static final int RES = 110;

    private InvolvedPersonMoulde involvedPersonMoulde;

    private InvolvedPerson involvedPerson;

    @Override
    public int setContentView() {
        return R.layout.activity_jiaotongdangshiren;
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

    }

    //    @OnClick(R.id.bt)
    public void onViewClicked() {
        _name = edit2String(edName);
        _involved_sex = edit2String(edInvolvedSex);
        _involved_nationality = edit2String(edInvolvedNationality);

        _involved_birth_date = edit2String(edInvolvedBirthDate);
        _involved_idcard = edit2String(edInvolvedIdcard);
        _involved_native_place = edit2String(edInvolvedNativePlace);

        _involved_degree = edit2String(edInvolvedDegree);
        _A = edit2String(edA);

        _car_no = edit2String(edCarNo);
        _B = edit2String(edB);

        _C = edit2String(edC);

        _D = edit2String(edD);

        _E = edit2String(edE);
        _F = edit2String(edF);

        _G = edit2String(edG);


        if (TextUtils.isEmpty(_name)
                && TextUtils.isEmpty(_involved_sex)
                && TextUtils.isEmpty(_involved_nationality)
                && TextUtils.isEmpty(_involved_nationality)
                && TextUtils.isEmpty(_involved_birth_date)
                && TextUtils.isEmpty(_involved_idcard)
                && TextUtils.isEmpty(_involved_native_place)
                && TextUtils.isEmpty(_involved_degree)
                && TextUtils.isEmpty(_A)
                && TextUtils.isEmpty(_car_no)
                && TextUtils.isEmpty(_B)
                && TextUtils.isEmpty(_C)
                && TextUtils.isEmpty(_D)
                && TextUtils.isEmpty(_E)
                && TextUtils.isEmpty(_F)
                && TextUtils.isEmpty(_G)
                ) {
            showToast("请输入相关内容！");
            return;
        }

        Map<String, String> map = new HashMap<String, String>();
        map.put("$name$", _name);
        map.put("$involved_sex$", _involved_sex);
        map.put("$involved_nationality$", _involved_nationality);
        map.put("$involved_birth_date$", _involved_birth_date);
        map.put("$involved_idcard$", _involved_idcard);
        map.put("$involved_native_place$", _involved_native_place);
        map.put("$involved_degree$", _involved_degree);
        map.put("$A$", _A);
        map.put("$car_no$", _car_no);

        map.put("$B$", _B);
        map.put("$C$", _C);

        map.put("$D$", _D);
        map.put("$E$", _E);

        map.put("$F$", _F);
        map.put("$G$", _G);


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
                if(involvedPerson.getType()==Constants.AUTHOR){
                    if (TextUtils.isEmpty(involvedPerson.getInvolved_name())) {
                        involvedPerson.setInvolved_name(_name);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getInvolved_sex())) {
                        involvedPerson.setInvolved_sex(_involved_sex);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getInvolved_nationality())) {
                        involvedPerson.setInvolved_nationality(_involved_nationality);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getInvolved_birth_date())) {
                        involvedPerson.setInvolved_birth_date(_involved_birth_date);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getInvolved_idcard())) {
                        involvedPerson.setInvolved_idcard(_involved_idcard);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getInvolved_native_place())) {
                        involvedPerson.setInvolved_native_place(_involved_native_place);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getInvolved_degree())) {
                        involvedPerson.setInvolved_degree(_involved_degree);
                    }
                }else if(involvedPerson.getType()==Constants.CAR){
                    if (TextUtils.isEmpty(involvedPerson.getCar_no())) {
                        involvedPerson.setCar_no(_car_no);
                    }
                }

                involvedPerson.setDate(System.currentTimeMillis());
                involvedPersonMoulde.upDateInvolved(involvedPerson);
            } else {
                involvedPerson = new InvolvedPerson();
                involvedPerson.setType(Constants.AUTHOR);

                if (TextUtils.isEmpty(involvedPerson.getInvolved_name())) {
                    involvedPerson.setInvolved_name(_name);
                }
                if (TextUtils.isEmpty(involvedPerson.getInvolved_sex())) {
                    involvedPerson.setInvolved_sex(_involved_sex);
                }
                if (TextUtils.isEmpty(involvedPerson.getInvolved_nationality())) {
                    involvedPerson.setInvolved_nationality(_involved_nationality);
                }
                if (TextUtils.isEmpty(involvedPerson.getInvolved_birth_date())) {
                    involvedPerson.setInvolved_birth_date(_involved_birth_date);
                }
                if (TextUtils.isEmpty(involvedPerson.getInvolved_idcard())) {
                    involvedPerson.setInvolved_idcard(_involved_idcard);
                }
                if (TextUtils.isEmpty(involvedPerson.getInvolved_native_place())) {
                    involvedPerson.setInvolved_native_place(_involved_native_place);
                }
                if (TextUtils.isEmpty(involvedPerson.getInvolved_degree())) {
                    involvedPerson.setInvolved_degree(_involved_degree);
                }

                involvedPerson.setDate(System.currentTimeMillis());
                involvedPersonMoulde.addInvolved(involvedPerson);

                if(!TextUtils.isEmpty(_car_no)){
                    InvolvedPerson   involvedPersonC = new InvolvedPerson();
                    involvedPersonC.setType(Constants.CAR);
                    involvedPersonC.setCar_no(_car_no);
                    involvedPersonC.setDate(System.currentTimeMillis());
                    involvedPersonMoulde.addInvolved(involvedPersonC);
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
                    if(involvedPerson.getType()==Constants.AUTHOR){
                        if(!TextUtils.isEmpty(involvedPerson.getInvolved_name())){
                            edName.setText(involvedPerson.getInvolved_name());
                        }
                        if(!TextUtils.isEmpty(involvedPerson.getInvolved_sex())){
                            edInvolvedSex.setText(involvedPerson.getInvolved_sex());
                        }
                        if(!TextUtils.isEmpty(involvedPerson.getInvolved_nationality())){
                            edInvolvedNationality.setText(involvedPerson.getInvolved_nationality());

                        }
                        if(!TextUtils.isEmpty(involvedPerson.getInvolved_birth_date())){
                            edInvolvedBirthDate.setText(involvedPerson.getInvolved_birth_date());

                        }
                        if(!TextUtils.isEmpty(involvedPerson.getInvolved_idcard())){
                            edInvolvedIdcard.setText(involvedPerson.getInvolved_idcard());

                        }
                        if(!TextUtils.isEmpty(involvedPerson.getInvolved_native_place())){
                            edInvolvedNativePlace.setText(involvedPerson.getInvolved_native_place());

                        }
                        if(!TextUtils.isEmpty(involvedPerson.getInvolved_degree())){
                            edInvolvedDegree.setText(involvedPerson.getInvolved_degree());

                        }

                    }else if (involvedPerson.getType()==Constants.CAR){
                        if(!TextUtils.isEmpty(involvedPerson.getCar_no())){
                            edCarNo.setText(involvedPerson.getCar_no());
                        }
                    }




                }
            }


        }
    }

}
