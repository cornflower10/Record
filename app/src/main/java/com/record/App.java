package com.record;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.record.dao.DaoMaster;
import com.record.dao.DaoSession;
import com.record.utils.ForegroundCallbacks;

import org.greenrobot.greendao.database.Database;

/**
 * Created by xiejingbao on 2017/8/30.
 */

public class App extends Application {
    private static App INSTANCE;
    private boolean isLogin;
    private String sessionKey;
    private static DaoSession daoSession;
    public static App getInstance() {
        return INSTANCE;
    }
    private ForegroundCallbacks foregroundCallbacks ;

    public ForegroundCallbacks getForegroundCallbacks() {
        return foregroundCallbacks;
    }

    @Override
    public void onCreate() {
        INSTANCE = this;
        super.onCreate();
        foregroundCallbacks = ForegroundCallbacks.init(this);
        initDataBase();
        if(BuildConfig.debug){
            Stetho.initializeWithDefaults(this);
        }
//        CrashHandler.getInstance().init(this);
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }


    private void initDataBase() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, "record.db");
        Database db = openHelper.getWritableDb();

        DaoMaster daoMaster  = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }
    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
