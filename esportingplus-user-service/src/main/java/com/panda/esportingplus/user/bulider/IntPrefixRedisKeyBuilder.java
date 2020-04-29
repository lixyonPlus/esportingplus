package com.panda.esportingplus.user.bulider;

/**
 * @author shusong.liang
 * @Title: IntPrefixRedisKeyBuilder
 * @Description: 整数前缀构造器
 * @date 2020/03/25 14:15
 */
public class IntPrefixRedisKeyBuilder extends RedisKeySegmentBuilder {

    @Override
    public String bulidSegment(String segmentSeed) {
        return segmentSeed.length()<=3?"0":segmentSeed.substring(0,segmentSeed.length()-3);
    }
}
