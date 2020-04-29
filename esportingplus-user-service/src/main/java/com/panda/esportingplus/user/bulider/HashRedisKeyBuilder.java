package com.panda.esportingplus.user.bulider;

/**
 * @author shusong.liang
 * @Title: HashRedisKeyBuilder
 * @Description: hash分片构造器
 * @date 2020/03/25 14:15
 */
public class HashRedisKeyBuilder extends RedisKeySegmentBuilder {

    @Override
    public String bulidSegment(String segmentSeed) {
        return Integer.toString(hash(segmentSeed) & (HASH_SEGMENT_SIZE - 1));
    }

}
