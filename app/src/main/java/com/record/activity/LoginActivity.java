package com.record.activity;

import android.content.Intent;
import android.os.Bundle;

import com.record.R;

import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @Override
    public int setContentView() {
        return R.layout.activity_login;
    }

    @Override
    public String setTitleName() {
        return "登录";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setShowBack(false);
        super.onCreate(savedInstanceState);

    }

    @OnClick(R.id.rl_login)
    public void onViewClicked() {
        Intent intent =new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();

    }
}
