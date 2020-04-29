package com.panda.esportingplus.user.mq.consumer;

import com.kaihei.commons.cache.api.spi.common.CacheManager;
import com.kaihei.commons.cache.api.spi.common.CacheManagerFactory;

import com.panda.esportingplus.common.constant.RocketMQConstant;
import com.panda.esportingplus.common.tools.JacksonUtils;
import com.panda.esportingplus.user.api.mq.OrderStatisticMessage;
import com.panda.esportingplus.user.constant.UserRedisKey;

import com.maihaoche.starter.mq.annotation.MQConsumer;
import com.maihaoche.starter.mq.base.AbstractMQPushConsumer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.*;


/**
 * @Auther: shusong.liang
 * @Date: 2020-03-27 14:27
 * @Description:
 */
@MQConsumer(topic = RocketMQConstant.TOPIC_PVP_FREE, tag = RocketMQConstant.ORDER_STATISTICS_TAG,
        consumerGroup = RocketMQConstant.ORDER_STATISTICS_CONSUMER_GROUP)
public class OrderStatisticConsumer extends AbstractMQPushConsumer<OrderStatisticMessage> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private CacheManager cacheManager = CacheManagerFactory.create();



    @Override
    public boolean process(OrderStatisticMessage message, Map<String, Object> map) {
        logger.info("cmd=OrderStatisticConsumer.process | OrderStatisticMessage={}",
                JacksonUtils.toJson(message));
        Object msg_id = map.get("MSG_ID") + "_" + message.hashCode();
        String bizId = message.getMessageId();
        Boolean repeatConsume = cacheManager
                .exists(UserRedisKey.ORDER_STATISTICS_KEY + bizId);
        if (repeatConsume != null && repeatConsume) {
            logger.info(
                    "cmd=OrderStatisticConsumer.process | msg=重复收到订单统计mq消费请求, 忽略。messageId={}, MQ消息id={}",
                    bizId,
                    msg_id);
            return true;
        }

        List<String> uids = message.getUids();
        try {

        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
