package com.panda.esportingplus.user.mq.producer;

import com.alibaba.fastjson.JSON;
import com.maihaoche.starter.mq.annotation.MQProducer;
import com.maihaoche.starter.mq.base.AbstractMQProducer;
import java.nio.charset.Charset;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: shusong.liang
 * @Date: 2020-03-27 14:26
 * @Description: 通用消息生产者类
 */
@MQProducer
public class CommonProducer extends AbstractMQProducer {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public <T> void sendAsync(final String topic, final String tag, final String key, T t) {
        if (t == null) {
            logger.error(
                    "cmd=CommonProducer.sendAsync | msg=message data is null | topic={}, tag={}",
                    topic, tag);
            return;
        }
        if (StringUtils.isBlank(topic)) {
            logger.error(
                    "cmd=CommonProducer.sendAsync | msg=message topic is null | topic={}, tag={}",
                    topic, tag);
            return;
        }
        String str = JSON.toJSONString(t);
        Message message = new Message(topic, str.getBytes(Charset.forName("UTF8")));
        if (StringUtils.isNotBlank(tag)) {
            message.setTags(tag);
        }
        if (StringUtils.isNotBlank(key)) {
            message.setKeys(key);
        }
        final long start = System.currentTimeMillis();
        asyncSend(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                logger.info(
                        "cmd=CommonProducer.sendAsync | msg=消息发送成功 | req={} | topic={} | tags={} | key={} | cost time={}ms",
                        str, topic, tag, key, System.currentTimeMillis() - start);
            }

            @Override
            public void onException(Throwable throwable) {
                logger.error(
                        "cmd=CommonProducer.sendAsync | msg=消息发送异常 | req=" + str +
                                " | topic=" + topic + " | tags=" + tag + " | key=" + key,
                        throwable);
            }
        });
    }
}
