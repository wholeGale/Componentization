package com.xzb.basecore;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @Author: xiangzhenbiao
 * @Date: 2019-03-18 13:53
 * @Description: initModeApp 是初始化当前组件时需要调用的方法，initModuleData 是所有组件的都初始化后再调用的方法。
 */

public abstract class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //注：IS_DEBUG在gradle文件中debug和release节点配置
        if (BuildConfig.IS_DEBUG) {
            // 打印日志
            ARouter.openLog();
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        ARouter.init( this ); // 尽可能早，推荐在Application中初始化

    }

    /**
     * Application 初始化
     */
    public abstract void initModuleApp(Application application);

    /**
     * 所有 Application 初始化后的自定义操作
     */
    public abstract void initModuleData(Application application);

}
