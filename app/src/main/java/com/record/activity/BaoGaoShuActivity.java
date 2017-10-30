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

public class BaoGaoShuActivity extends BaseActivity implements ErrorView {


    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.ed_name)
    AppCompatEditText edName;
    @BindView(R.id.ed_context)
    AppCompatEditText edContext;


    private DocType docType;
    private static final String outPath = Constants.docPath;
    private String  _name, _context;

    private LawCaseMoulde lawCaseMoulde;

    private static final int RES = 110;

    private InvolvedPersonMoulde involvedPersonMoulde;

    private InvolvedPerson involvedPerson;
    private UserMoulde userMoulde;
    private User user;

    @Override
    public int setContentView() {
        return R.layout.activity_baogaoshu;
    }

    @Override
    public String setTitleName() {
        return "录入信息";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        tv_right.setVisibility(View.GONE);
        tv_right.setText("导入模板");
//        titleRightIv.setImageResource(R.drawable.search);
        if (null != getIntent()) {
            docType = getIntent().getParcelableExtra("doc");
//            Log.i("RecordDocInfoActivity", docType.getPath());
        }
        lawCaseMoulde = new LawCaseMoudleImpl(this);
        involvedPersonMoulde = new InvolvedPersonMouldeImpl(this);
        userMoulde = new UserMouldeImpl(this);
         user = userMoulde.seletcUser();
//        if (null != user) {
//            edInstitutionName.setText(user.getInstitutionName());
//        }

        if ((!TextUtils.isEmpty(getTypeNull())) && getTypeNull().equals(Constants.DOC_NULL)) {
            onViewClicked(false);
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
    public void onViewClicked(boolean isClick) {
        if (!isClick) {
            tv_right.setVisibility(View.GONE);
        }
        if (isClick) {
            _name = edit2String(edName);
            _context = edit2String(edContext);
            if (TextUtils.isEmpty(_name)
                    && TextUtils.isEmpty(_context)
                    && TextUtils.isEmpty(_name)
                    ) {
                showToast("请输入相关内容！");
                return;
            }
        }


        Map<String, String> map = new HashMap<String, String>();
        map.put("$565656$", _name);
        map.put("$898989$", _context);
        if(null == user){
            map.put("$institution_police_a$", "");
        }else {
            map.put("$institution_police_a$", user.getName());
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


}
