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
import com.record.moudle.entity.LawCase;
import com.record.moudle.entity.Receipt;
import com.record.moudle.moudleDao.LawCaseMoudleImpl;
import com.record.moudle.moudleDao.LawCaseMoulde;
import com.record.moudle.moudleDao.LawCaseView;
import com.record.utils.Constants;
import com.record.utils.Number2CN;
import com.record.utils.StringUtils;
import com.record.utils.TimeUtils;
import com.record.utils.WordUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class RecordDocInfoActivity extends BaseActivity implements LawCaseView {

    @BindView(R.id.ed_name)
    AppCompatEditText edName;
    @BindView(R.id.ed_money)
    AppCompatEditText edMoney;
    @BindView(R.id.ed_card_number)
    AppCompatEditText edCardNumber;
    @BindView(R.id.tv_right)
    TextView tv_right;

    private DocType docType;
    private static final String outPath = Constants.docPath;
    private String name;
    private String money, cardNo;
    private LawCaseMoulde lawCaseMoulde;
    private static final int RES = 110;
    private static final String  M = "¥:";
    @Override
    public int setContentView() {
        return R.layout.activity_record_doc_info;
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
    }

    //    @OnClick(R.id.bt)
    public void onViewClicked() {
        name = edName.getText().toString().trim();
        money = edMoney.getText().toString().trim();
        cardNo = edCardNumber.getText().toString().trim();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(money) || TextUtils.isEmpty(cardNo)) {
            showToast("请输入完整信息");
            return;
        }
        BigDecimal numberOfMoney = new BigDecimal(money);
        Map<String, String> map = new HashMap<String, String>();
        map.put("$involved_name$", name);
        map.put("$moneyUp$", Number2CN.number2CNMontrayUnit(numberOfMoney));
        map.put("$money$", M + money);
        map.put("$car_no$", cardNo);

        try {
            String outPathName = outPath + "/" + docType.getTitle() + TimeUtils.currentTimeMillis() + ".doc";
            WordUtil.doScan(getAssets().open("doc/" + docType.getType() + "/" + docType.getTitle() + ".doc"), outPathName, map);

            LawCase lawCase = new LawCase();
            lawCase.setType(docType.getType());
            lawCase.setLawCaseInfo(StringUtils.hashMapToJson(map));
            lawCase.setLawCaseTitle(docType.getTitle());
//            lawCase.setName(name);
//            lawCase.setMoney(money);
//            lawCase.setCarNo(cardNo);
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
                Intent intent =new Intent(mContext,QueryDocListActivity.class);
                startActivityForResult(intent,RES);
                break;
            case R.id.bt:
                onViewClicked();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==RES){
            if(resultCode == RESULT_OK){
                Receipt receipt = data.getParcelableExtra("Receipt");
                if(null!=receipt){
                    edName.setText(receipt.getName());
                    edCardNumber.setText(receipt.getCarNo());
                    edMoney.setText(receipt.getMoney().replace(M,""));
                }
            }


        }
    }


    //    @OnClick(R.id.title_rightIv)
//    public void onViewClicked() {
//    }
}
