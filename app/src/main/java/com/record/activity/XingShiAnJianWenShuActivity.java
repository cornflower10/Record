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

public class XingShiAnJianWenShuActivity extends BaseActivity implements ErrorView {


    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.ed_name)
    AppCompatEditText edName;
    @BindView(R.id.ed_ttt)
    AppCompatEditText edTtt;
    @BindView(R.id.ed_yyy)
    AppCompatEditText edYyy;
    @BindView(R.id.ed_thing_)
    AppCompatEditText edThing;
    @BindView(R.id.ed_thing_11)
    AppCompatEditText edThing11;
    @BindView(R.id.ed_thing_info)
    AppCompatEditText edThingInfo;
    @BindView(R.id.ed_464646)
    AppCompatEditText ed464646;
    @BindView(R.id.ed_535353)
    AppCompatEditText ed535353;
    @BindView(R.id.ed_353535)
    AppCompatEditText ed353535;
    @BindView(R.id.ed_7Q7Q7Q)
    AppCompatEditText ed7Q7Q7Q;
    @BindView(R.id.ed_8W8W8W)
    AppCompatEditText ed8W8W8W;


    private DocType docType;
    private static final String outPath = Constants.docPath;
    private String _name, _ttt, _yyy,
            _thing_,
            _thing_11, _thing_info,
            _464646, _535353, _353535, _7Q7Q7Q, _8W8W8W;

    private LawCaseMoulde lawCaseMoulde;
    private static final int RES = 110;

    private InvolvedPersonMoulde involvedPersonMoulde;

    private InvolvedPerson involvedPerson;

    @Override
    public int setContentView() {
        return R.layout.activity_xueyejiaojian;
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


        if((!TextUtils.isEmpty(getTypeNull()))&&getTypeNull().equals(Constants.DOC_NULL)){
            onViewClicked(false);
        }

    }

    /**
     * 当事人姓名  $name$
     * 性  别      $ttt$
     * 年  龄     $yyy$
     * 交通事故时间 $thing_$
     * 交通事故地点 $thing_11$
     * 简要案情   $thing_info$
     * 提取时间   $464646$
     * 提取地点  $535353$
     * 消毒液名称    $353535$
     * 密封方法    $7Q7Q7Q$
     * 样本量      $8W8W8W$
     */
    public void onViewClicked(boolean isClick) {
        if(!isClick){
            tv_right.setVisibility(View.GONE);
        }
        if(isClick){
            _name = edit2String(edName);
            _ttt = edit2String(edTtt);
            _yyy = edit2String(edYyy);

            _thing_ = edit2String(edThing);
            _thing_11 = edit2String(edThing11);
            _thing_info = edit2String(edThingInfo);

            _464646 = edit2String(ed464646);
            _535353 = edit2String(ed535353);

            _353535 = edit2String(ed353535);
            _7Q7Q7Q = edit2String(ed7Q7Q7Q);

            _8W8W8W = edit2String(ed8W8W8W);

            if (TextUtils.isEmpty(_name)
                    && TextUtils.isEmpty(_ttt)
                    && TextUtils.isEmpty(_yyy)
                    && TextUtils.isEmpty(_thing_)
                    && TextUtils.isEmpty(_thing_11)
                    && TextUtils.isEmpty(_thing_info)
                    && TextUtils.isEmpty(_464646)
                    && TextUtils.isEmpty(_535353)
                    && TextUtils.isEmpty(_353535)
                    && TextUtils.isEmpty(_7Q7Q7Q)
                    && TextUtils.isEmpty(_8W8W8W)
                    ) {
                showToast("请输入相关内容！");
                return;
            }
        }


        Map<String, String> map = new HashMap<String, String>();
        map.put("$name$", _name);
        map.put("$ttt$", _ttt);
        map.put("$yyy$", _yyy);
        map.put("$thing_$", _thing_);
        map.put("$thing_11$", _thing_11);
        map.put("$thing_info$", _thing_info);
        map.put("$464646$", _464646);
        map.put("$535353$", _535353);
        map.put("$353535$", _353535);

        map.put("$7Q7Q7Q$", _7Q7Q7Q);
        map.put("$8W8W8W$", _8W8W8W);

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
                    if (TextUtils.isEmpty(involvedPerson.getThing_())) {
                        involvedPerson.setThing_(_thing_);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_11())) {
                        involvedPerson.setThing_11(_thing_11);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_info())) {
                        involvedPerson.setThing_info(_thing_info);
                    }
                    involvedPerson.setDate(System.currentTimeMillis());
                    involvedPersonMoulde.upDateInvolved(involvedPerson);
                } else {
                    involvedPerson = new InvolvedPerson();
                    involvedPerson.setType(Constants.LAWCASE);
                    if (TextUtils.isEmpty(involvedPerson.getThing_())) {
                        involvedPerson.setThing_(_thing_);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_11())) {
                        involvedPerson.setThing_11(_thing_11);
                    }
                    if (TextUtils.isEmpty(involvedPerson.getThing_info())) {
                        involvedPerson.setThing_info(_thing_info);
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
                    edThing.setText(involvedPerson.getThing_());
                    edThing11.setText(involvedPerson.getThing_11());
                    edThingInfo.setText(involvedPerson.getThing_info());

                }
            }


        }
    }

}
