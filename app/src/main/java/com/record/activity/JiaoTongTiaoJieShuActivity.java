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

public class JiaoTongTiaoJieShuActivity extends BaseActivity implements ErrorView {


    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.ed_thing_)
    AppCompatEditText edThing;
    @BindView(R.id.ed_thing_11)
    AppCompatEditText edThing11;
    @BindView(R.id.ed_institution_police_a)
    AppCompatEditText edInstitutionPoliceA;
    @BindView(R.id.ed_OOOOOO)
    AppCompatEditText edOOOOOO;
    @BindView(R.id.ed_name1)
    AppCompatEditText edName1;
    @BindView(R.id.ed_ttt1)
    AppCompatEditText edTtt1;
    @BindView(R.id.ed_yyy1)
    AppCompatEditText edYyy1;
    @BindView(R.id.ed_uuu1)
    AppCompatEditText edUuu1;
    @BindView(R.id.ed_III1)
    AppCompatEditText edIII1;
    @BindView(R.id.ed_name2)
    AppCompatEditText edName2;
    @BindView(R.id.ed_ttt2)
    AppCompatEditText edTtt2;
    @BindView(R.id.ed_yyy2)
    AppCompatEditText edYyy2;
    @BindView(R.id.ed_uuu2)
    AppCompatEditText edUuu2;
    @BindView(R.id.ed_III2)
    AppCompatEditText edIII2;
    @BindView(R.id.ed_name3)
    AppCompatEditText edName3;
    @BindView(R.id.ed_ttt3)
    AppCompatEditText edTtt3;
    @BindView(R.id.ed_yyy3)
    AppCompatEditText edYyy3;
    @BindView(R.id.ed_uuu3)
    AppCompatEditText edUuu3;
    @BindView(R.id.ed_III3)
    AppCompatEditText edIII3;


    private DocType docType;
    private static final String outPath = Constants.docPath;
    private String thing, thing11, _institution_police_a,
            _OOOOOO,
            _name1, _ttt1,
            _yyy1, _uuu1, _III1,
            _name2, _ttt2,
            _yyy2, _uuu2, _III2,
            _name3, _ttt3,
            _yyy3, _uuu3, _III3;

    private LawCaseMoulde lawCaseMoulde;
    private static final int RES = 110;

    private InvolvedPersonMoulde involvedPersonMoulde;

    private InvolvedPerson involvedPerson;
    private UserMoulde userMoulde;

    @Override
    public int setContentView() {
        return R.layout.activity_tiaojieshu;
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
            edInstitutionPoliceA.setText(user.getName());
        }
    }

    /**
     * 交通事故时间 $thing_$
     * 交通事故地点 $thing_11$
     * 交通警察 $institution_police_a$
     * 调解内容  $OOOOOO$
     * 当事人1
     * <p>
     * 姓名  $name1$
     * 性别  $ttt1$
     * 年龄  $yyy1$
     * 住址或单位 $uuu1$
     * 交通方式、车牌号 $III1$
     * <p>
     * 当事人2
     * 姓名  $name2$
     * 性别  $ttt2$
     * 年龄  $yyy2$
     * 住址或单位 $uuu2$
     * 交通方式、车牌号 $III2$
     * <p>
     * 当事人3
     * 姓名  $name3$
     * 性别  $ttt3$
     * 年龄  $yyy3$
     * 住址或单位 $uuu3$
     * 交通方式、车牌号 $III3$
     */
    public void onViewClicked() {
        thing = edit2String(edThing);
        thing11 = edit2String(edThing11);
        _institution_police_a = edit2String(edInstitutionPoliceA);

        _OOOOOO = edit2String(edOOOOOO);
        _name1 = edit2String(edName1);
        _ttt1 = edit2String(edTtt1);

        _yyy1 = edit2String(edYyy1);
        _uuu1 = edit2String(edUuu1);

        _III1 = edit2String(edIII1);

        _name2 = edit2String(edName2);
        _ttt2 = edit2String(edTtt2);

        _yyy2 = edit2String(edYyy2);
        _uuu2 = edit2String(edUuu2);

        _III2 = edit2String(edIII2);

        _name3 = edit2String(edName3);
        _ttt3 = edit2String(edTtt3);

        _yyy3 = edit2String(edYyy3);
        _uuu3 = edit2String(edUuu3);

        _III3 = edit2String(edIII3);


        Map<String, String> map = new HashMap<String, String>();
        map.put("$thing_$", thing);
        map.put("$thing_11$", thing11);
        map.put("$institution_police_a$", _institution_police_a);
        map.put("$OOOOOO$", _OOOOOO);
        map.put("$name1$", _name1);
        map.put("$ttt1$", _ttt1);
        map.put("$yyy1$", _yyy1);
        map.put("$uuu1$", _uuu1);
        map.put("$III1$", _III1);

        map.put("$name2$", _name2);
        map.put("$ttt2$", _ttt2);
        map.put("$yyy2$", _yyy2);
        map.put("$uuu2$", _uuu2);
        map.put("$III2$", _III2);

        map.put("$name3$", _name3);
        map.put("$ttt3$", _ttt3);
        map.put("$yyy3$", _yyy3);
        map.put("$uuu3$", _uuu3);
        map.put("$III3$", _III3);


        if (TextUtils.isEmpty(thing)
                && TextUtils.isEmpty(thing11)
                && TextUtils.isEmpty(_institution_police_a)
                && TextUtils.isEmpty(_OOOOOO)
                && TextUtils.isEmpty(_name1)
                && TextUtils.isEmpty(_ttt1)
                && TextUtils.isEmpty(_yyy1)
                && TextUtils.isEmpty(_uuu1)
                && TextUtils.isEmpty(_III1)

                && TextUtils.isEmpty(_name2)
                && TextUtils.isEmpty(_ttt2)
                && TextUtils.isEmpty(_yyy2)
                && TextUtils.isEmpty(_uuu2)
                && TextUtils.isEmpty(_III2)

                && TextUtils.isEmpty(_name3)
                && TextUtils.isEmpty(_ttt3)
                && TextUtils.isEmpty(_yyy3)
                && TextUtils.isEmpty(_uuu3)
                && TextUtils.isEmpty(_III3)

                ) {
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

            if (null != involvedPerson) {
//                    InvolvedPerson involvedP = new InvolvedPerson();
                involvedPerson.setType(Constants.LAWCASE);
                if (TextUtils.isEmpty(involvedPerson.getThing_())) {
                    involvedPerson.setThing_(thing);
                }
                if (TextUtils.isEmpty(involvedPerson.getThing_11())) {
                    involvedPerson.setThing_11(thing11);
                }
                involvedPerson.setDate(System.currentTimeMillis());
                involvedPersonMoulde.upDateInvolved(involvedPerson);
            } else {
                involvedPerson = new InvolvedPerson();
                involvedPerson.setType(Constants.LAWCASE);
                if (TextUtils.isEmpty(involvedPerson.getThing_())) {
                    involvedPerson.setThing_(thing);
                }
                if (TextUtils.isEmpty(involvedPerson.getThing_11())) {
                    involvedPerson.setThing_11(thing11);
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
                    if(!TextUtils.isEmpty(involvedPerson.getThing_())){
                        edThing.setText(involvedPerson.getThing_());
                    }

                    if(!TextUtils.isEmpty(involvedPerson.getThing_11())){
                        edThing11.setText(involvedPerson.getThing_11());
                    }





                }
            }


        }
    }

}
