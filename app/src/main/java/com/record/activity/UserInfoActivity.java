package com.record.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;

import com.record.R;
import com.record.moudle.entity.User;
import com.record.moudle.moudleDao.ErrorView;
import com.record.moudle.moudleDao.UserMoulde;
import com.record.moudle.moudleDao.UserMouldeImpl;

import butterknife.BindView;
import butterknife.OnClick;

public class UserInfoActivity extends BaseActivity implements ErrorView {

    @BindView(R.id.ed_name)
    AppCompatEditText edName;
    @BindView(R.id.ed_no)
    AppCompatEditText edNo;
    @BindView(R.id.ed_company)
    AppCompatEditText edCompany;
    private String name,no,company;
    private UserMoulde userMoulde;
    private User user;

    @Override
    public int setContentView() {
        return R.layout.activity_user_info;
    }

    @Override
    public String setTitleName() {
        return "我的信息";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userMoulde  = new UserMouldeImpl(this);

             user = userMoulde.seletcUser();
            if (null != user) {
                edNo.setText(user.getUserNo());
                edName.setText(user.getName());
                edCompany.setText(user.getInstitutionName());
            }

    }

    @OnClick(R.id.bt)
    public void onViewClicked() {
       name = edit2String(edNo);
       no = edit2String(edName);
       company = edit2String(edCompany);
        if(TextUtils.isEmpty(name)&&TextUtils.isEmpty(no)&&TextUtils.isEmpty(company)){
            showToast("请填写相关信息");
            return;
        }
        if(null==user){
            user = new User();
            user.setName(name);
            user.setUserNo(no);
            user.setInstitutionName(company);
            if(userMoulde.addUser(user)){
                showToast("设置成功");
                finish();
            }
        }else {
            user.setName(name);
            user.setUserNo(no);
            user.setInstitutionName(company);
            if(userMoulde.upDateUser(user)){
                showToast("修改成功");
                finish();
            }
        }

    }

    @Override
    public void onError(String msg) {
        showToast(msg);
    }
}
