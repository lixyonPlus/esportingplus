package com.panda.esportingplus.user.thirdparty.service;

import com.panda.esportingplus.common.event.EventBus;
import com.panda.esportingplus.common.thread.pool.ThreadPoolManager;
import com.panda.esportingplus.user.UserServiceApplication;
import com.panda.esportingplus.user.external.rongyun.RongYunService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Title: RongYunServiceImplTest
 * @Auther: shusong.liang
 * @Date: 2020-03-27 14:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserServiceApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class RongYunServiceImplTest {

    @Autowired
    private RongYunService rongYunService;

    {
        EventBus.create(ThreadPoolManager.INSTANCE.getEventBusExecutor());
    }

    @Test
    public void testGetToken() {
        String token = null;
        token = rongYunService
                .getToken("userxxd2", "username", "http://www.rongcloud.cn/images/logo.png");
        System.out.println(token);
        Assert.assertNotNull(token);
    }

    @Test
    public void testUpdateUser(){
        boolean result = false;
        result = rongYunService.updateUser("userxxd2", "username1", "http://www.rongcloud.cn/images/logo.png");
        System.out.println(result);
        Assert.assertTrue(result);
    }

    @Test
    public void sendSystemMessage(){
        String[] userids = new String[150];
        for(int i=0;i<150;i++){
            userids[i] = "10000"+i;
        }
//        rongYunService.sendSystemMessage(userids,"欢迎来到l1111");
    }
}
