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

public class JiaoTongShiGuZhengMingActivity extends BaseActivity implements ErrorView {


    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.ed_pageNum)
    AppCompatEditText edPageNum;
    @BindView(R.id.ed_institution_name)
    AppCompatEditText edInstitutionName;
    @BindView(R.id.ed_institution_time)
    AppCompatEditText edInstitutionTime;
    @BindView(R.id.ed_thing_11)
    AppCompatEditText edThing11;
    @BindView(R.id.ed_34R34R)
    AppCompatEditText ed34R34R;
    @BindView(R.id.ed_thing_personnel_involved_basic)
    AppCompatEditText edThingPersonnelInvolvedBasic;
    @BindView(R.id.ed_thing_car_basic)
    AppCompatEditText edThingCarBasic;
    @BindView(R.id.ed_thing_road_condition)
    AppCompatEditText edThingRoadCondition;
    @BindView(R.id.ed_thing_info)
    AppCompatEditText edThingInfo;
    @BindView(R.id.ed_thing_prof)
    AppCompatEditText edThingProf;


    private DocType docType;
    private static final String outPath = Constants.docPath;
    private String _pageNum, _institution_name, _institution_time,
            _thing_11,
            _34R34R, _thing_personnel_involved_basic,
            _thing_car_basic, _thing_road_condition, _thing_info,
            _thing_prof;

    private LawCaseMoulde lawCaseMoulde;
    private static final int RES = 110;

    private InvolvedPersonMoulde involvedPersonMoulde;

    private InvolvedPerson involvedPerson;
    private UserMoulde userMoulde;

    @Override
    public int setContentView() {
        return R.layout.activity_jiaotongshiguzhengming;
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
        userMoulde  =new UserMouldeImpl(this);
        User user = userMoulde.seletcUser();
        if(null!=user){
            edInstitutionName.setText(user.getInstitutionName());
        }

        if((!TextUtils.isEmpty(getTypeNull()))&&getTypeNull().equals(Constants.DOC_NULL)){
            onViewClicked(false);
        }
    }

    /**
     * 文书号   $pageNum$
     * 执法单位信息  $institution_name$
     * 交通事故时间 $institution_time$
     * 交通事故地点 $thing_11$
     * 天气 $34R34R$
     * 当事人基本情况 $thing_personnel_involved_basic$
     * 车辆基本情况   $thing_car_basic$
     * 道路和交通环境 $thing_road_condition$
     * 道路交通事故发生经过 $thing_info$
     * 道路交通事故证据 $thing_prof$
     */
    public void onViewClicked(boolean isClick) {
        if(!isClick){
            tv_right.setVisibility(View.GONE);
        }
        if(isClick){
            _pageNum = edit2String(edPageNum);
            _institution_name = edit2String(edInstitutionName);
            _institution_time = edit2String(edInstitutionTime);

            _thing_11 = edit2String(edThing11);
            _34R34R = edit2String(ed34R34R);
            _thing_personnel_involved_basic = edit2String(edThingPersonnelInvolvedBasic);

            _thing_car_basic = edit2String(edThingCarBasic);
            _thing_road_condition = edit2String(edThingRoadCondition);

            _thing_info = edit2String(edThingInfo);

            _thing_prof = edit2String(edThingProf);

            if (TextUtils.isEmpty(_pageNum)
                    && TextUtils.isEmpty(_institution_name)
                    && TextUtils.isEmpty(_institution_time)
                    && TextUtils.isEmpty(_thing_11)
                    && TextUtils.isEmpty(_34R34R)
                    && TextUtils.isEmpty(_thing_personnel_involved_basic)
                    && TextUtils.isEmpty(_thing_car_basic)
                    && TextUtils.isEmpty(_thing_road_condition)
                    && TextUtils.isEmpty(_thing_info)
                    && TextUtils.isEmpty(_thing_prof)
                    ) {
                showToast("请输入相关内容！");
                return;
            }
        }

        Map<String, String> map = new HashMap<String, String>();
        map.put("$pageNum$", _pageNum);
        map.put("$institution_name$", _institution_name);
        map.put("$institution_time$", _institution_time);
        map.put("$thing_11$", _thing_11);
        map.put("$34R34R$", _34R34R);
        map.put("$thing_personnel_involved_basic$", _thing_personnel_involved_basic);
        map.put("$thing_car_basic$", _thing_car_basic);
        map.put("$thing_road_condition$", _thing_road_condition);
        map.put("$thing_info$", _thing_info);
        map.put("$thing_prof$", _thing_prof);

        try {
            String outPathName = outPath + "/" + docType.getTitle() + TimeUtils.currentTimeMillis() + ".doc";
            WordUtil.doScan(getAssets().open("doc/" + docType.getType() + "/" + docType.getTitle() + ".doc"), outPathName, map);

            LawCase lawCase = new LawCase();
            lawCase.setType(docType.getType());
            lawCase.setLawCaseTitle(docType.getTitle());
            lawCase.setIsPrint(false);
            lawCase.setDate(TimeUtils.currentTimeMillis());
            lawCase.setDocPath(outPathName);
            if(isClick){
                if (null != involvedPerson) {
                    involvedPerson.setType(Constants.LAWCASE);
                    if (TextUtils.isEmpty(involvedPerson.getThing_11())) {
                        involvedPerson.setThing_11(_thing_11);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_personnel_involved_basic())) {
                        involvedPerson.setThing_personnel_involved_basic(_thing_personnel_involved_basic);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_car_basic())) {
                        involvedPerson.setThing_car_basic(_thing_car_basic);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_road_condition())) {
                        involvedPerson.setThing_road_condition(_thing_road_condition);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_info())) {
                        involvedPerson.setThing_info(_thing_info);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_prof())) {
                        involvedPerson.setThing_prof(_thing_prof);
                    }

                    involvedPerson.setDate(System.currentTimeMillis());
                    involvedPersonMoulde.upDateInvolved(involvedPerson);
                } else {
                    involvedPerson = new InvolvedPerson();
                    involvedPerson.setType(Constants.LAWCASE);
                    if (TextUtils.isEmpty(involvedPerson.getThing_11())) {
                        involvedPerson.setThing_11(_thing_11);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_personnel_involved_basic())) {
                        involvedPerson.setThing_personnel_involved_basic(_thing_personnel_involved_basic);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_car_basic())) {
                        involvedPerson.setThing_car_basic(_thing_car_basic);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_road_condition())) {
                        involvedPerson.setThing_road_condition(_thing_road_condition);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_info())) {
                        involvedPerson.setThing_info(_thing_info);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_prof())) {
                        involvedPerson.setThing_prof(_thing_prof);
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
                    if (!TextUtils.isEmpty(involvedPerson.getThing_11())) {
                        edThing11.setText(involvedPerson.getThing_11());
                    }

                    if (!TextUtils.isEmpty(involvedPerson.getThing_personnel_involved_basic())) {
                        edThingPersonnelInvolvedBasic.setText(involvedPerson.getThing_personnel_involved_basic());
                    }

                    if (!TextUtils.isEmpty(involvedPerson.getThing_car_basic())) {
                        edThingCarBasic.setText(involvedPerson.getThing_car_basic());
                    }
                    if (!TextUtils.isEmpty(involvedPerson.getThing_road_condition())) {
                        edThingRoadCondition.setText(involvedPerson.getThing_road_condition());
                    }
                    if (!TextUtils.isEmpty(involvedPerson.getThing_info())) {
                        edThingInfo.setText(involvedPerson.getThing_info());
                    }
                    if (!TextUtils.isEmpty(involvedPerson.getThing_prof())) {
                        edThingProf.setText(involvedPerson.getThing_prof());
                    }

                }
            }


        }
    }

}
