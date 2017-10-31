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

public class XianChangJianChaActivity extends BaseActivity implements ErrorView {


    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.ed_5353KJ)
    AppCompatEditText ed5353KJ;
    @BindView(R.id.ed_4545HG)
    AppCompatEditText ed4545HG;
    @BindView(R.id.ed_7878FD)
    AppCompatEditText ed7878FD;
    @BindView(R.id.ed_9898SA)
    AppCompatEditText ed9898SA;
    @BindView(R.id.ed_7979RT)
    AppCompatEditText ed7979RT;
    @BindView(R.id.ed_0303NH)
    AppCompatEditText ed0303NH;
    @BindView(R.id.ed_1618GF)
    AppCompatEditText ed1618GF;


    private DocType docType;
    private static final String outPath = Constants.docPath;
    private String _5353KJ, _4545HG, _7878FD, _9898SA, _7979RT, _0303NH, _1618GF;

    private LawCaseMoulde lawCaseMoulde;
    private static final int RES = 110;

    private InvolvedPersonMoulde involvedPersonMoulde;

    private InvolvedPerson involvedPerson,involvedCar;

    @Override
    public int setContentView() {
        return R.layout.activity_xian_chang_jian_cha;
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
     * 现场检查记录  所需规则
     * 检查时间  $5353KJ$
     * 检查地点  $4545HG$
     * 驾驶人姓名  $7878FD$
     * 身份证或驾驶证号  $9898SA$
     * 驾驶车辆类型  $7979RT$
     * 车号   $0303NH$
     * 涉嫌的违法行为状态：  $1618GF$
     *
     * @param isClick
     */

    public void onViewClicked(boolean isClick) {
        if (!isClick) {
            tv_right.setVisibility(View.GONE);
        }
        if (isClick) {
            _5353KJ = edit2String(ed5353KJ);
            _4545HG = edit2String(ed4545HG);
            _7878FD = edit2String(ed7878FD);
            _9898SA = edit2String(ed9898SA);
            _7979RT = edit2String(ed7979RT);
            _0303NH = edit2String(ed0303NH);
            _1618GF = edit2String(ed1618GF);

            if (TextUtils.isEmpty(_5353KJ)
                    && TextUtils.isEmpty(_4545HG)
                    && TextUtils.isEmpty(_7878FD)
                    && TextUtils.isEmpty(_9898SA)
                    && TextUtils.isEmpty(_7979RT)
                    && TextUtils.isEmpty(_0303NH)
                    && TextUtils.isEmpty(_1618GF)
                    ) {
                showToast("请输入相关内容！");
                return;
            }
        }


        Map<String, String> map = new HashMap<String, String>();
        map.put("$5353KJ$", _5353KJ);
        map.put("$4545HG$", _4545HG);
        map.put("$7878FD$", _7878FD);
        map.put("$9898SA$", _9898SA);
        map.put("$7979RT$", _7979RT);
        map.put("$0303NH$", _0303NH);
        map.put("$1618GF$", _1618GF);


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
                if (null != involvedCar) {
                    involvedCar.setType(Constants.CAR);
                    if (TextUtils.isEmpty(involvedCar.getCar_no())) {
                        involvedCar.setCar_no(_0303NH);
                    }

                    if (TextUtils.isEmpty(involvedCar.getCar_type())) {
                        involvedCar.setCar_type(_7979RT);
                    }
                    involvedCar.setDate(System.currentTimeMillis());
                    involvedPersonMoulde.upDateInvolved(involvedCar);
                } else {
                    involvedCar = new InvolvedPerson();
                    involvedCar.setType(Constants.CAR);
                    if (TextUtils.isEmpty(involvedCar.getCar_no())) {
                        involvedCar.setCar_no(_0303NH);
                    }
                    if (TextUtils.isEmpty(involvedCar.getCar_type())) {
                        involvedCar.setCar_type(_7979RT);
                    }
                    involvedCar.setDate(System.currentTimeMillis());
                    involvedPersonMoulde.addInvolved(involvedCar);
                }
                if(null!=involvedPerson){
                    involvedPerson.setType(Constants.AUTHOR);
                    if (TextUtils.isEmpty(involvedCar.getInvolved_name())) {
                        involvedPerson.setInvolved_name(_7878FD);
                    }
                    if (TextUtils.isEmpty(involvedCar.getInvolved_idcard())) {
                        involvedPerson.setInvolved_idcard(_0303NH);
                    }

                    involvedPerson.setDate(System.currentTimeMillis());
                    involvedPersonMoulde.upDateInvolved(involvedPerson);

                }else {
                    involvedPerson = new InvolvedPerson();
                    involvedPerson.setType(Constants.AUTHOR);
                    if (TextUtils.isEmpty(involvedCar.getInvolved_name())) {
                        involvedPerson.setInvolved_name(_7878FD);
                    }
                    if (TextUtils.isEmpty(involvedCar.getInvolved_idcard())) {
                        involvedPerson.setInvolved_idcard(_0303NH);
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
                InvolvedPerson  involved = data.getParcelableExtra("Receipt");
                if (null != involved) {
                    if(involved.getType()==Constants.AUTHOR){
                        involvedPerson = involved;
                    }else if(involved.getType()==Constants.CAR){
                        involvedCar = involved;
                    }
                }

                if(null!=involvedPerson){
                    ed7878FD.setText(involvedPerson.getInvolved_name());
                    ed9898SA.setText(involvedPerson.getInvolved_idcard());
                }
                   if(null!=involvedCar){
                       ed7979RT.setText(involvedCar.getCar_type());
                       ed0303NH.setText(involvedCar.getCar_no());
                   }
                   }

            }

    }

}
