package com.record.activity;

import android.os.Bundle;

import com.record.R;

public class ErrorActivity extends BaseActivity {

    @Override
    public int setContentView() {
        return R.layout.activity_error;
    }

    @Override
    public String setTitleName() {
        return "正在建设";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
