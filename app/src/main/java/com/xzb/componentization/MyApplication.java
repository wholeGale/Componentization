package com.xzb.componentization;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.xzb.basecore.AppConfig;
import com.xzb.basecore.BaseApp;

/**
 * @Author: xiangzhenbiao
 * @Date: 2019-03-17 18:49
 * @Description:
 */

public class MyApplication extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();

        initModuleApp(this);


        initModuleData(this);
    }

    /**
     * 初始化组件 Application
     * @param application
     */
    @Override
    public void initModuleApp(Application application) {
        for(String moduleApp: AppConfig.moduleApps){
            try {
                Class clazz = Class.forName(moduleApp);
                //通过反射，初始化各个组件的 Application
                BaseApp baseApp = (BaseApp) clazz.newInstance();
                baseApp.initModuleApp(application);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 所有 Application 初始化后的操作
     * @param application
     */
    @Override
    public void initModuleData(Application application) {
        for(String moduleApp: AppConfig.moduleApps){
            try {
                Class clazz = Class.forName(moduleApp);
                //通过反射，初始化各个组件的 Application
                BaseApp baseApp = (BaseApp) clazz.newInstance();
                baseApp.initModuleData(application);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}
