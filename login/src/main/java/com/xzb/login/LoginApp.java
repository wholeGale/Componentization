package com.xzb.login;

import android.app.Application;

import com.xzb.basecore.BaseApp;
import com.xzb.basecore.ServiceFactory;
import com.xzb.login.entitys.UserInfo;
import com.xzb.login.service.AccountServiceImpl;
import com.xzb.login.utils.AccountUtil;

/**
 * @Author: xiangzhenbiao
 * @Date: 2019-03-18 13:53
 * @Description:
 */

public class LoginApp extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();

        initModuleApp(this);
        initModuleData(this);
    }


    @Override
    public void initModuleApp(Application application) {

        // 将 AccountService 类的实例注册到 ServiceFactory
        ServiceFactory.getInstance().setAccountService(new AccountServiceImpl());
    }

    @Override
    public void initModuleData(Application application) {

    }
}
