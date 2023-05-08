package com.example.basemodel.manager;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.example.basemodel.manager.account.AccountDataBean;
import com.example.common.utils.sp.SPUtils;

/**
 * @auther: pengwang
 * @date: 2023/5/8
 * @description:
 */
public class AccountManager {

    private static final String ACCOUNT_INFO_KEY = "account_info_key";

    private static volatile AccountManager instance;

    private AccountManager() {

    }

    public static AccountManager getInstance() {
        if (instance == null) {
            synchronized (AccountManager.class) {
                if (instance == null)
                    instance = new AccountManager();
            }
        }
        return instance;
    }

    public void login(AccountDataBean bean) {
        if (bean != null) {
            SPUtils.setString(ACCOUNT_INFO_KEY, JSONObject.toJSONString(bean));
        }
    }

    public void logout() {
        SPUtils.setString(ACCOUNT_INFO_KEY, "");
    }

    public AccountDataBean getAccount() {
        String strAcc = SPUtils.getString(ACCOUNT_INFO_KEY, "");

        if (!TextUtils.isEmpty(strAcc)) {
            AccountDataBean bean = JSONObject.parseObject(strAcc, AccountDataBean.class);
            return bean;
        }

        return null;
    }

    public boolean isLogin() {
        return getAccount() != null && !TextUtils.isEmpty(getUserCode());
    }

    public String getUserCode() {
        if (getAccount() != null)
            return getAccount().userCode;
        return "";
    }

}
