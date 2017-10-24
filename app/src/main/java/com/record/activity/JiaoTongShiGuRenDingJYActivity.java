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

public class JiaoTongShiGuRenDingJYActivity extends BaseActivity implements ErrorView {


    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.ed_institution_name)
    AppCompatEditText edInstitutionName;
    @BindView(R.id.ed_pageNum)
    AppCompatEditText edPageNum;
    @BindView(R.id.ed_thing_)
    AppCompatEditText edThing;
    @BindView(R.id.ed_thing_11)
    AppCompatEditText edThing11;
    @BindView(R.id.ed_34R34R)
    AppCompatEditText ed34R34R;
    @BindView(R.id.ed_factAndResponsibility)
    AppCompatEditText edFactAndResponsibility;
    @BindView(R.id.ed_compensateAndResult)
    AppCompatEditText edCompensateAndResult;
    @BindView(R.id.ed_date)
    AppCompatEditText edDate;
    @BindView(R.id.ed_name1)
    AppCompatEditText edName1;
    @BindView(R.id.ed_idCard1)
    AppCompatEditText edIdCard1;
    @BindView(R.id.ed_mobile1)
    AppCompatEditText edMobile1;
    @BindView(R.id.ed_transportation1)
    AppCompatEditText edTransportation1;
    @BindView(R.id.ed_transportationType1)
    AppCompatEditText edTransportationType1;
    @BindView(R.id.ed_name2)
    AppCompatEditText edName2;
    @BindView(R.id.ed_idCard2)
    AppCompatEditText edIdCard2;
    @BindView(R.id.ed_mobile2)
    AppCompatEditText edMobile2;
    @BindView(R.id.ed_transportation2)
    AppCompatEditText edTransportation2;
    @BindView(R.id.ed_transportationType2)
    AppCompatEditText edTransportationType2;
    @BindView(R.id.ed_name3)
    AppCompatEditText edName3;
    @BindView(R.id.ed_idCard3)
    AppCompatEditText edIdCard3;
    @BindView(R.id.ed_mobile3)
    AppCompatEditText edMobile3;
    @BindView(R.id.ed_transportation3)
    AppCompatEditText edTransportation3;
    @BindView(R.id.ed_transportationType3)
    AppCompatEditText edTransportationType3;
    @BindView(R.id.ed_name4)
    AppCompatEditText edName4;
    @BindView(R.id.ed_idCard4)
    AppCompatEditText edIdCard4;
    @BindView(R.id.ed_mobile4)
    AppCompatEditText edMobile4;
    @BindView(R.id.ed_transportation4)
    AppCompatEditText edTransportation4;
    @BindView(R.id.ed_transportationType4)
    AppCompatEditText edTransportationType4;


    private DocType docType;
    private static final String outPath = Constants.docPath;
    private String _pageNum, _institution_name, _thing_,
            _thing_11,
            _34R34R, _factAndResponsibility,
            _compensateAndResult, _date,
            _name1,_idCard1,_mobile1,_transportation1,_transportationType1,
            _name2,_idCard2,_mobile2,_transportation2,_transportationType2,
            _name3,_idCard3,_mobile3,_transportation3,_transportationType3,
            _name4,_idCard4,_mobile4,_transportation4,_transportationType4
            ;

    private LawCaseMoulde lawCaseMoulde;
    private static final int RES = 110;

    private InvolvedPersonMoulde involvedPersonMoulde;

    private InvolvedPerson involvedPerson;
    private UserMoulde userMoulde;

    @Override
    public int setContentView() {
        return R.layout.activity_jiaotongshigurending_jy;
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

    public void onViewClicked(boolean isClick) {
        if(!isClick){
            tv_right.setVisibility(View.GONE);
        }
        if(isClick){
            _pageNum = edit2String(edPageNum);
            _institution_name = edit2String(edInstitutionName);
            _thing_ = edit2String(edThing);

            _thing_11 = edit2String(edThing11);
            _34R34R = edit2String(ed34R34R);
            _factAndResponsibility = edit2String(edFactAndResponsibility);

            _compensateAndResult = edit2String(edCompensateAndResult);
            _date = edit2String(edDate);

            _name1 = edit2String(edName1);
            _idCard1 = edit2String(edIdCard1);
            _mobile1 = edit2String(edMobile1);
            _transportation1 = edit2String(edTransportation1);
            _transportationType1 = edit2String(edTransportationType1);

            _name2 = edit2String(edName2);
            _idCard2 = edit2String(edIdCard2);
            _mobile2 = edit2String(edMobile2);
            _transportation2 = edit2String(edTransportation2);
            _transportationType2 = edit2String(edTransportationType2);

            _name3 = edit2String(edName3);
            _idCard3 = edit2String(edIdCard3);
            _mobile3 = edit2String(edMobile3);
            _transportation3 = edit2String(edTransportation3);
            _transportationType3 = edit2String(edTransportationType3);

            _name4 = edit2String(edName4);
            _idCard4 = edit2String(edIdCard4);
            _mobile4 = edit2String(edMobile4);
            _transportation4 = edit2String(edTransportation4);
            _transportationType4 = edit2String(edTransportationType4);

            if (TextUtils.isEmpty(_pageNum)
                    && TextUtils.isEmpty(_institution_name)
                    && TextUtils.isEmpty(_thing_)
                    && TextUtils.isEmpty(_thing_11)
                    && TextUtils.isEmpty(_34R34R)
                    && TextUtils.isEmpty(_factAndResponsibility)
                    && TextUtils.isEmpty(_compensateAndResult)
                    && TextUtils.isEmpty(_date)

                    && TextUtils.isEmpty(_name1)
                    && TextUtils.isEmpty(_idCard1)
                    && TextUtils.isEmpty(_mobile1)
                    && TextUtils.isEmpty(_transportation1)
                    && TextUtils.isEmpty(_transportationType1)

                    && TextUtils.isEmpty(_name2)
                    && TextUtils.isEmpty(_idCard2)
                    && TextUtils.isEmpty(_mobile2)
                    && TextUtils.isEmpty(_transportation2)
                    && TextUtils.isEmpty(_transportationType2)

                    && TextUtils.isEmpty(_name3)
                    && TextUtils.isEmpty(_idCard3)
                    && TextUtils.isEmpty(_mobile3)
                    && TextUtils.isEmpty(_transportation3)
                    && TextUtils.isEmpty(_transportationType3)

                    && TextUtils.isEmpty(_name4)
                    && TextUtils.isEmpty(_idCard4)
                    && TextUtils.isEmpty(_mobile4)
                    && TextUtils.isEmpty(_transportation4)
                    && TextUtils.isEmpty(_transportationType4)
                    ) {
                showToast("请输入相关内容！");
                return;
            }
        }

        Map<String, String> map = new HashMap<String, String>();
        map.put("$institution_name$", _institution_name);
        map.put("$pageNum$", _pageNum);
        map.put("$thing_$", _thing_);
        map.put("$thing_11$", _thing_11);
        map.put("$34R34R$", _34R34R);
        map.put("$factAndResponsibility$", _factAndResponsibility);
        map.put("$compensateAndResult$", _compensateAndResult);
        map.put("$date$", _date);

        map.put("$name1$", _name1);
        map.put("$idCard1$", _idCard1);
        map.put("$mobile1$", _mobile1);
        map.put("$transportation1$", _transportation1);
        map.put("$transportationType1$", _transportationType1);

        map.put("$name2$", _name2);
        map.put("$idCard2$", _idCard2);
        map.put("$mobile2$", _mobile2);
        map.put("$transportation2$", _transportation2);
        map.put("$transportationType2$", _transportationType2);

        map.put("$name3$", _name3);
        map.put("$idCard3$", _idCard3);
        map.put("$mobile3$", _mobile3);
        map.put("$transportation3$", _transportation3);
        map.put("$transportationType3$", _transportationType3);

        map.put("$name4$", _name4);
        map.put("$idCard4$", _idCard4);
        map.put("$mobile4$", _mobile4);
        map.put("$transportation4$", _transportation4);
        map.put("$transportationType4$", _transportationType4);

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

                    if (!TextUtils.isEmpty(involvedPerson.getThing_())) {
                        edThing.setText(involvedPerson.getThing_());
                    }
                    if (!TextUtils.isEmpty(involvedPerson.getThing_11())) {
                        edThing11.setText(involvedPerson.getThing_11());
                    }
                }
            }


        }
    }

}
