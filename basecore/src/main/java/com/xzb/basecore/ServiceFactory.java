package com.xzb.basecore;

import com.xzb.basecore.service.EmptyAccountService;
import com.xzb.basecore.service.IAccountService;

/**
 * @Author: xiangzhenbiao
 * @Date: 2019-03-18 13:06
 * @Description:
 */

public class ServiceFactory {

    private IAccountService accountService;

    private ServiceFactory(){

    }

    /**
     * 通过静态内部类方式实现 ServiceFactory 的单例
     */
    public static ServiceFactory getInstance(){
        return Inner.serviceFactory;
    }

    /**
     * 返回 Login 组件的 Service 实例
     */
    public IAccountService getAccountService() {
        //这里可以不考虑线程安全问题
        if(accountService == null){
            accountService = new EmptyAccountService();
        }
        return accountService;
    }

    /**
     * 接收 Login 组件实现的 Service 实例
     */
    public void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    private static final class Inner{
        private static ServiceFactory serviceFactory = new ServiceFactory();
    }
}
