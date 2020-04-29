package com.panda.esportingplus.user.api.params;

import java.io.Serializable;

/**
 * @Auther: shusong.liang
 * @Date: 2020/03/31 17:23
 * @Description:
 */
public class MiniprogramUserInfo implements Serializable {

    private static final long serialVersionUID = 7582337201154396561L;

    private String encryptedData;
    
    private String iv;

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }
}
