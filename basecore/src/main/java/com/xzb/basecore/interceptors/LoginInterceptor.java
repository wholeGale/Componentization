package com.xzb.basecore.interceptors;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.xzb.basecore.ServiceFactory;
import com.xzb.basecore.constants.RouterPathManager;

/**
 * @Author: xiangzhenbiao
 * @Date: 2019-03-18 20:33
 * @Description: 登录拦截器
 */
@Interceptor(priority = 10, name = "登录判断拦截器")
public class LoginInterceptor implements IInterceptor {

    private Context context;

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        // onContinue 和 onInterrupt 至少需要调用其中一种，否则不会继续路由
        if (postcard.getPath().equals(RouterPathManager.SHARE_PATH_SHARE_MAIN_ACTIVITY)) {
            if (ServiceFactory.getInstance().getAccountService().isLogin()) {
                //是登录状态，可以直接分享，继续执行
                callback.onContinue(postcard);  // 处理完成，交还控制权
            } else {
                //不是登录状态，不可以直接分享，需要先登录
                callback.onInterrupt(new RuntimeException("分享前请先登录")); // 中断路由流程

                //手动将路径更改为跳转到登录界面，这样操作跳了2次界面，不可取
                /*postcard.setPath(RouterPathManager.LOGIN_PATH_LOGIN_MAIN_ACTIVITY);
                callback.onContinue(postcard);*/
            }
        } else {
            callback.onContinue(postcard);  // 处理完成，交还控制权
        }
    }

    @Override
    public void init(Context context) {
        // 拦截器的初始化，会在sdk初始化的时候调用该方法，仅会调用一次
        this.context = context;
    }
}
