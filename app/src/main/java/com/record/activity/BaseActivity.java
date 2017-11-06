package com.record.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.record.App;
import com.record.R;
import com.record.moudle.entity.DocType;
import com.record.moudle.entity.LawCase;
import com.record.moudle.moudleDao.LawCaseMoulde;
import com.record.utils.Constants;
import com.record.utils.TimeUtils;
import com.record.utils.WordUtil;

import java.io.IOException;
import java.util.Map;

import butterknife.ButterKnife;


public abstract class  BaseActivity extends AppCompatActivity {
    private Toast toast = null;
    public Context mContext;
    public abstract int setContentView();

    public abstract String setTitleName();

    private boolean showBack = true;

    public boolean isShowBack() {
        return showBack;
    }

    public void setShowBack(boolean showBack) {
        this.showBack = showBack;
    }

    private static final String outPath = Constants.docPath;

    public String getTypeNull() {
        return typeNull;
    }

    private String typeNull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentView());
        ButterKnife.bind(this);

        mContext=this;

        initToolar();
        initTypeNull();
    }

    private void initTypeNull() {
        if(null!=getIntent()){
            typeNull = getIntent().getStringExtra(Constants.TYPE);
        }
    }


    private void initToolar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(null!=toolbar){
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("");
            if(showBack){
                toolbar.setNavigationIcon(R.drawable.nav_arrow_white);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }
            TextView textView = (TextView) findViewById(R.id.title_name);
            textView.setText(setTitleName());
        }


    }

    public void mStartActivity(Class<?> cls){
        Intent intent =new Intent(this,cls);
        mContext.startActivity(intent);
    }
    public void showToast(String msg){
        if(null!=toast){
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_SHORT);
        }else
        {
            toast = Toast.makeText(this,msg,Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                finishAfterTransition();
            } else {
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }


    public String edit2String(EditText editText){
        return editText.getText().toString().trim();
    }



    public void exportWordAndSave(DocType docType,
                                  Map map, LawCaseMoulde lawCaseMoulde){
        String outPathName = outPath + "/" + docType.getTitle() + TimeUtils.currentTimeMillis() + ".doc";
        try {
            String title = docType.getTitle();
            WordUtil.doScan(getAssets().open("doc/" +
                            docType.getType() + "/" +
                            title +
                            (title.equals(Constants.WPS_NAME)?".wps":".doc")),
                    outPathName, map);

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
        } catch (IOException e) {
            e.printStackTrace();
            showToast("生成失败" + e.getMessage());
        }
    }

}
