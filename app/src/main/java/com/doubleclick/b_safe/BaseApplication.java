package com.doubleclick.b_safe;

import android.app.Application;
import android.content.Context;

/**
 * Created By Eslam Ghazy on 6/11/2022
 */
public class BaseApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }
}
