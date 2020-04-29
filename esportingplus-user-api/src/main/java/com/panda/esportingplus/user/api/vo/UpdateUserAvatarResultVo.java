package com.panda.esportingplus.user.api.vo;

import java.io.Serializable;

/**
 * @Auther: shusong.liang
 * @Date: 2020-03-27 15:28
 * @Description:
 */
public class UpdateUserAvatarResultVo implements Serializable {

    private static final long serialVersionUID = -7780779678210059428L;

    private String text;

    private Integer verifyStatus;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Integer verifyStatus) {
        this.verifyStatus = verifyStatus;
    }
}
