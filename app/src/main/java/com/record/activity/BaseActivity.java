package com.record.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by caomingyu on 2016/5/28.
 */
public class BaseActivity extends AppCompatActivity {
    private Toast toast = null;
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
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
}
