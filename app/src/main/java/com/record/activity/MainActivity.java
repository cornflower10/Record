package com.record.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.record.App;
import com.record.R;
import com.record.customview.PrintChooseDialog;
import com.record.fragment.AccountFragment;
import com.record.fragment.MainFragment;
import com.record.fragment.PrintRecordFragment;

import java.util.List;

import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    private static final int EXTERNAL_STORAGE = 001;
    private String path;
    private long exitTime = 0;
    private FragmentTabHost mTabHost = null;

    @Override
    public int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    public String setTitleName() {
        return "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setShowBack(false);
        super.onCreate(savedInstanceState);
        path = Environment.getExternalStorageDirectory() + "/Test.doc";
        initView();
        methodRequiresTwoPermission();
    }

    @OnClick({R.id.bt_word, R.id.bt_print, R.id.bt_make_word})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_word:
                Intent intent = new Intent(this, WordHtmlActivity.class);
                intent.putExtra("path", path);
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

    String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    private boolean hasStoragePermission() {
        return EasyPermissions.hasPermissions(this, perms);
    }

    @AfterPermissionGranted(EXTERNAL_STORAGE)
    private void methodRequiresTwoPermission() {
        if (hasStoragePermission()) {
            // Already have permission, do the thing
            // ...
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(MainActivity.this, getString(R.string.extrernal_storage),
                    EXTERNAL_STORAGE, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.d("tag", "onPermissionsGranted:" + requestCode + ":" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d("tag", "onPermissionsDenied:" + requestCode + ":" + perms.size());

        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            String yes = "yes";
            String no = "no";

            // Do something after user returned from app settings screen, like showing a Toast.
            Toast.makeText(
                    this,
                    getString(R.string.extrernal_storage, hasStoragePermission() ? yes : no),
                    Toast.LENGTH_LONG)
                    .show();
        }
    }
}
