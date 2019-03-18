package com.xzb.basecore;

/**
 * @Author: xiangzhenbiao
 * @Date: 2019-03-18 14:10
 * @Description:
 */

public class AppConfig {

    private static final String LOGIN_APP = "com.xzb.login.LoginApp";
    private static final String SHARE_APP = "com.xzb.share.ShareApp";

    //moduleApps 是一个静态的 String 数组，我们将需要初始化的组件的Application 的完整类名放入到这个数组中。
    public static String[] moduleApps = {
            LOGIN_APP,
            SHARE_APP
    };

}
