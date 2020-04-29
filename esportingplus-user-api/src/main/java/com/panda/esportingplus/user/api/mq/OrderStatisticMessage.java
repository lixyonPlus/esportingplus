package com.panda.esportingplus.user.api.mq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author shusong.liang
 * @Description  此MQ 目录，没有业务可以不用
 *               目标其它模块可以共用此实体类（MQ）
 * @Date 2020/03/27 21:25
 **/
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatisticMessage implements Serializable {

    /**
     * 订单类型 0：所有；1：通勤车；2：周边游
     */
    private Integer type;

    /**
     * 下单时间
     */
    private Date orderCreateTime;

    /**
     * 消息id （作为消费去重判断）
     */
    private String messageId;

    /**
     * 订单
     */
    private List<String> uids;

}
