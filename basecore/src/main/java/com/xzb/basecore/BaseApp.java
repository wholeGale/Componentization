package com.xzb.basecore;

import android.app.Application;

/**
 * @Author: xiangzhenbiao
 * @Date: 2019-03-18 13:53
 * @Description: initModeApp 是初始化当前组件时需要调用的方法，initModuleData 是所有组件的都初始化后再调用的方法。
 */

public abstract class BaseApp extends Application {

    /**
     * Application 初始化
     */
    public abstract void initModuleApp(Application application);

    /**
     * 所有 Application 初始化后的自定义操作
     */
    public abstract void initModuleData(Application application);

}
