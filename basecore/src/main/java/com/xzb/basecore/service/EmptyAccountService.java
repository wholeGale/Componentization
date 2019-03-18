package com.xzb.basecore.service;

/**
 * @Author: xiangzhenbiao
 * @Date: 2019-03-18 13:12
 * @Description:
 */

public class EmptyAccountService implements IAccountService {
    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public String getAccountId() {
        return null;
    }
}
