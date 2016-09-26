package com.zhangpeng.administrator.LogIn;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/9/18.
 */

public class Account extends BmobObject {
    String name;
    String password;
    String surePassWord;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setSurePassWord(String surePassWord) {
        this.surePassWord = surePassWord;
    }
    public String getPassword() {
        return password;
    }

    public String getSurePassWord() {
        return surePassWord;
    }
}
