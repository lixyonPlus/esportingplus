package com.panda.esportingplus.user.api.vo;

import java.io.Serializable;

/**
 * @Auther: shusong.liang
 * @Date: 2020-03-28 10:25
 * @Description:
 */
public class UserPreRegistVo implements Serializable {

    private static final long serialVersionUID = -8094246173822206151L;

    private boolean regist;

    public boolean isRegist() {
        return regist;
    }

    public void setRegist(boolean regist) {
        this.regist = regist;
    }
}
