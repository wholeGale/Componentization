package com.xzb.share;

import android.app.Application;

import com.xzb.basecore.BaseApp;

/**
 * @Author: xiangzhenbiao
 * @Date: 2019-03-18 14:07
 * @Description:
 */

public class ShareApp extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();

        initModuleApp(this);
        initModuleData(this);
    }

    @Override
    public void initModuleApp(Application application) {

    }

    @Override
    public void initModuleData(Application application) {

    }
}
