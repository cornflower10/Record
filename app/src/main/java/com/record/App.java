package com.record;

import android.app.Application;

import com.record.utils.ForegroundCallbacks;

/**
 * Created by xiejingbao on 2017/8/30.
 */

public class App extends Application {
    private static App INSTANCE;
    private boolean isLogin;
    private String sessionKey;
    // Returns the application instance
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
//        CrashHandler.getInstance().init(this);
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }


}
