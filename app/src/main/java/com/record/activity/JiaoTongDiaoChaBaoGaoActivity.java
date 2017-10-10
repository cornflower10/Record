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

public class JiaoTongDiaoChaBaoGaoActivity extends BaseActivity implements ErrorView {


    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.ed_thing_)
    AppCompatEditText edThing;
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
    @BindView(R.id.ed_thing_analysis)
    AppCompatEditText edThingAnalysis;
    @BindView(R.id.ed_thing_law)
    AppCompatEditText edThingLaw;
    @BindView(R.id.ed_thing_confirmation)
    AppCompatEditText edThingConfirmation;

    private DocType docType;
    private static final String outPath = Constants.docPath;
    private String thing,thing11,_34R34R,
            thingPersonnelInvolvedBasic,
            thingCarBasic,thingRoadCondition,
            thingInfo,thingProf,thingAnalysis,thingLaw,thingConfirmation;

    private LawCaseMoulde lawCaseMoulde;
    private static final int RES = 110;

    private InvolvedPersonMoulde involvedPersonMoulde;

    private InvolvedPerson involvedPerson;

    @Override
    public int setContentView() {
        return R.layout.activity_jiao_tong_diao_cha_bao_gao;
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
        thing = edit2String(edThing);
        thing11 = edit2String(edThing11);
        _34R34R = edit2String(ed34R34R);

        thingPersonnelInvolvedBasic = edit2String(edThingPersonnelInvolvedBasic);
        thingCarBasic = edit2String(edThingCarBasic);
        thingRoadCondition = edit2String(edThingRoadCondition);

        thingInfo = edit2String(edThingInfo);
        thingProf = edit2String(edThingProf);

        thingAnalysis = edit2String(edThingAnalysis);
        thingLaw = edit2String(edThingLaw);

        thingConfirmation = edit2String(edThingConfirmation);

        Map<String, String> map = new HashMap<String, String>();
        map.put("$thing_$", thing);
        map.put("$thing_11$", thing11);
        map.put("$34R34R$", _34R34R);
        map.put("$thing_personnel_involved_basic$", thingPersonnelInvolvedBasic);
        map.put("$thing_car_basic$", thingCarBasic);
        map.put("$thing_road_condition$", thingRoadCondition);
        map.put("$thing_info$", thingInfo);
        map.put("$thing_prof$", thingProf);
        map.put("$thing_analysis$", thingAnalysis);

        map.put("$thing_law$", thingLaw);
        map.put("$thing_confirmation$", thingConfirmation);

        if(TextUtils.isEmpty(thing)
                &&TextUtils.isEmpty(thing11)
                &&TextUtils.isEmpty(_34R34R)
                &&TextUtils.isEmpty(thingPersonnelInvolvedBasic)
                &&TextUtils.isEmpty(thingCarBasic)
                &&TextUtils.isEmpty(thingRoadCondition)
                &&TextUtils.isEmpty(thingInfo)
                &&TextUtils.isEmpty(thingProf)
                &&TextUtils.isEmpty(thingAnalysis)
                &&TextUtils.isEmpty(thingLaw)
                &&TextUtils.isEmpty(thingConfirmation)
                ){
            showToast("请输入相关内容！");
            return;
        }
        try {
            String outPathName = outPath + "/" + docType.getTitle() + TimeUtils.currentTimeMillis() + ".doc";
            WordUtil.doScan(getAssets().open("doc/" + docType.getType() + "/" + docType.getTitle() + ".doc"), outPathName, map);

            LawCase lawCase = new LawCase();
            lawCase.setType(docType.getType());
            lawCase.setLawCaseTitle(docType.getTitle());
            lawCase.setIsPrint(false);
            lawCase.setDate(TimeUtils.currentTimeMillis());
            lawCase.setDocPath(outPathName);

                if(null!=involvedPerson){

//                    InvolvedPerson involvedP = new InvolvedPerson();
                    involvedPerson.setType(Constants.LAWCASE);
                    if (TextUtils.isEmpty(involvedPerson.getThing_())) {
                        involvedPerson.setThing_(thing);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_11())) {
                        involvedPerson.setThing_11(thing11);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_personnel_involved_basic())) {
                        involvedPerson.setThing_personnel_involved_basic(thingPersonnelInvolvedBasic);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_car_basic())) {
                        involvedPerson.setThing_car_basic(thingCarBasic);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_road_condition())) {
                        involvedPerson.setThing_road_condition(thingRoadCondition);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_info())) {
                        involvedPerson.setThing_info(thingInfo);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_prof())) {
                        involvedPerson.setThing_prof(thingProf);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_analysis())) {
                        involvedPerson.setThing_analysis(thingAnalysis);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_law())) {
                        involvedPerson.setThing_law(thingLaw);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_confirmation())) {
                        involvedPerson.setThing_confirmation(thingConfirmation);
                    }

                    involvedPerson.setDate(System.currentTimeMillis());
                    involvedPersonMoulde.upDateInvolved(involvedPerson);
                }else {
                    involvedPerson = new InvolvedPerson();
                    involvedPerson.setType(Constants.LAWCASE);
                    if (TextUtils.isEmpty(involvedPerson.getThing_())) {
                        involvedPerson.setThing_(thing);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_11())) {
                        involvedPerson.setThing_11(thing11);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_personnel_involved_basic())) {
                        involvedPerson.setThing_personnel_involved_basic(thingPersonnelInvolvedBasic);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_car_basic())) {
                        involvedPerson.setThing_car_basic(thingCarBasic);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_road_condition())) {
                        involvedPerson.setThing_road_condition(thingRoadCondition);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_info())) {
                        involvedPerson.setThing_info(thingInfo);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_prof())) {
                        involvedPerson.setThing_prof(thingProf);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_analysis())) {
                        involvedPerson.setThing_analysis(thingAnalysis);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_law())) {
                        involvedPerson.setThing_law(thingLaw);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_confirmation())) {
                        involvedPerson.setThing_confirmation(thingConfirmation);
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
                    edThing.setText(involvedPerson.getThing_());
                    edThing11.setText(involvedPerson.getThing_11());
                    edThingPersonnelInvolvedBasic.setText(involvedPerson.getThing_personnel_involved_basic());
                    edThingCarBasic.setText(involvedPerson.getThing_car_basic());
                    edThingRoadCondition.setText(involvedPerson.getThing_road_condition());
                    edThingInfo.setText(involvedPerson.getThing_info());
                    edThingProf.setText(involvedPerson.getThing_prof());
                    edThingAnalysis.setText(involvedPerson.getThing_analysis());
                    edThingLaw.setText(involvedPerson.getThing_law());
                    edThingConfirmation.setText(involvedPerson.getThing_confirmation());


                }
            }


        }
    }

}
