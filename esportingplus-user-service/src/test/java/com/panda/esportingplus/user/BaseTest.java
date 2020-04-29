package com.panda.esportingplus.user;

import com.panda.esportingplus.common.event.EventBus;
import com.panda.esportingplus.common.thread.pool.ThreadPoolManager;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TODO 功能描述
 *
 * @version 1.0
 * @Auther: shusong.liang
 * @Date: 2020-03-27 14:27
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserServiceApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class BaseTest {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    {
        EventBus.create(ThreadPoolManager.INSTANCE.getEventBusExecutor());
    }

}
