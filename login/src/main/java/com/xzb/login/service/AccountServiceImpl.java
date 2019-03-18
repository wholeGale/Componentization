package com.xzb.login.service;

import com.xzb.basecore.service.IAccountService;
import com.xzb.login.utils.AccountUtil;

/**
 * @Author: xiangzhenbiao
 * @Date: 2019-03-18 13:46
 * @Description:
 */

public class AccountServiceImpl implements IAccountService {
    @Override
    public boolean isLogin() {
        return AccountUtil.userInfo!= null;
    }

    @Override
    public String getAccountId() {
        return AccountUtil.userInfo== null? null: AccountUtil.userInfo.getAccountId();
    }
}
