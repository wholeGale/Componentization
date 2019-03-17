package com.xzb.componentization;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @Author: xiangzhenbiao
 * @Date: 2019-03-17 18:49
 * @Description:
 */

public class MyApplication extends Application {

    private static final boolean isDebug = true;
    @Override
    public void onCreate() {
        super.onCreate();

        if (isDebug) {
            // 打印日志
            ARouter.openLog();
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        ARouter.init( this ); // 尽可能早，推荐在Application中初始化

    }
}
