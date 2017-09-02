package com.record.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.record.App;
import com.record.R;
import com.record.customview.PrintChooseDialog;
import com.record.fragment.AccountFragment;
import com.record.fragment.MainFragment;
import com.record.fragment.PrintRecordFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    private String path;
    private long exitTime = 0;
    private FragmentTabHost mTabHost = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        path = Environment.getExternalStorageDirectory() + "/Test.doc";
        initView();
    }

    @OnClick({R.id.bt_word, R.id.bt_print, R.id.bt_make_word})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_word:
                Intent intent = new Intent(this,WordHtmlActivity.class);
                intent.putExtra("path",path);
                startActivity(intent);
                break;
            case R.id.bt_print:
                PrintChooseDialog.Builder builder = new PrintChooseDialog.Builder(this).setCancelable(true).setPath(path);
                builder.show();
                break;
            case R.id.bt_make_word:
//                makeWrod();
                break;
        }
    }


    private void initView() {
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null);
        // 添加tab名称和图标
        View productListView = getLayoutInflater().inflate(R.layout.print_recorder_indicator, null);
        mTabHost.addTab(mTabHost.newTabSpec("printRecord").setIndicator(productListView), PrintRecordFragment.class, null);

        View homeView = getLayoutInflater().inflate(R.layout.home_indicator, null);
        mTabHost.addTab(mTabHost.newTabSpec("home").setIndicator(homeView), MainFragment.class, null);

        View myAccountView = getLayoutInflater().inflate(R.layout.account_indicator, null);
        mTabHost.addTab(mTabHost.newTabSpec("myAccount").setIndicator(myAccountView), AccountFragment.class, null);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再点一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                App.getInstance().getForegroundCallbacks().AppExit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
