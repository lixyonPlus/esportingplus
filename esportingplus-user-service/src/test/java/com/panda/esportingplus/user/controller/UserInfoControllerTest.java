package com.panda.esportingplus.user.controller;

import com.panda.esportingplus.common.tools.JacksonUtils;
import com.panda.esportingplus.user.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: shusong.liang
 * @Date: 2020-03-27 14:27
 * @Description:
 */
public class UserInfoControllerTest extends BaseTest {

    @Autowired
    private UserInfoController userInfoController;

    @Test
    public void testGetCombine() {
       //s System.out.println(JacksonUtils.toJson(userInfoController.getUserCombine("482924a8", 1)));
    }
}
