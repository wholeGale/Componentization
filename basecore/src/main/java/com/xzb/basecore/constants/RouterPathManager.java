package com.xzb.basecore.constants;

/**
 * @Author: xiangzhenbiao
 * @Date: 2019-03-17 17:37
 * @Description: 路由跳转管理类
 */

public final class RouterPathManager {
    private RouterPathManager(){

    }

    /*———————————————————————————————— ↓↓↓↓ Host Path ↓↓↓↓ ————————————————————————————————*/
    /**
     * APP
     */
    public static final String APP_PATH_HOST = "/app";
    /**
     * 分享
     */
    public static final String SHARE_PATH_HOST = "/share";
    /**
     * 支付
     */
    public static final String PAY_PATH_HOST = "/pay";
    /**
     * COMMON
     */
    public static final String COMMON_PATH_HOST = "/common";
    /**
     * 拦截器
     */
    public static final String APP_INTERCEPTOR_HOST = APP_PATH_HOST + "/interceptor";




    /*———————————————————————————————— ↓↓↓↓ App Path ↓↓↓↓ ————————————————————————————————*/

    /**
     * 引导页
     */
    public static final String APP_PATH_WELCOME_ACTIVITY = APP_PATH_HOST + RouterConstants.APP_PAGE_WELCOME_ACTIVITY;

    /**
     * 主页
     */
    public static final String APP_PATH_MAIN_TAB_ACTIVITY = APP_PATH_HOST + RouterConstants.APP_PAGE_MAIN_TAB_ACTIVITY;



    /*———————————————————————————————— ↓↓↓↓ Share Path ↓↓↓↓ ————————————————————————————————*/

    /**
     * 分享页
     */
    public static final String SHARE_PATH_SHARE_MAIN_ACTIVITY = SHARE_PATH_HOST + RouterConstants.SHARE_PAGE_SHARE_MAIN_ACTIVITY;


}
