package com.xzb.basecore.service;

/**
 * @Author: xiangzhenbiao
 * @Date: 2019-03-18 13:05
 * @Description:
 */

public interface IAccountService {

    /**
     * 是否已经登录
     * @return
     */
    boolean isLogin();

    /**
     * 获取登录用户的 AccountId
     * @return
     */
    String getAccountId();

}
