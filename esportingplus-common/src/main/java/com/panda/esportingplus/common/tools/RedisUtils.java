/**
 * @Title: RedisUtils.java
 * @Description: redis工具类
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 * @version V1.0
 */
package com.panda.esportingplus.common.tools;


import com.kaihei.commons.cache.api.spi.common.CacheManager;
import com.kaihei.commons.cache.api.spi.common.CacheManagerFactory;
import com.panda.esportingplus.common.constant.RedisKey;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * RedisUtils
 *
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
@Slf4j
public class RedisUtils {

    /** HASH分片大小, 必须为2的幂次方 */
    private static int HASH_SEGMENT_SIZE = 2 << 12;

    private static CacheManager cacheManager = CacheManagerFactory.create();

    /**
     * 生成HASH分片key
     *
     * @param segmentSeed 分片计算种子
     * @param keyPrefixs key的前缀字符串序列，按先手顺序组装
     * @return 分片缓存key
     */
    public static String buildHashSegmentKey(String segmentSeed, String... keyPrefixs) {
        StringBuilder key = new StringBuilder();
        for (String keyPrefix : keyPrefixs) {
            key.append(keyPrefix);
            key.append(RedisKey.KEY_SEPARATOR);
        }

        key.append(Integer.toString(hash(segmentSeed) & (HASH_SEGMENT_SIZE - 1)));
        return key.toString();
    }

    private static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * 查询同游戏所有大区的Key
     */
    public static Set<String> findAllMatchKeys(String match) {
        try (Jedis jedis = cacheManager.jedis()) {
            return jedis.keys(match);
        }
    }

    /**
     * 执行一系列Jedis操作
     */
    public static void exec(Consumer<Jedis> consumer) {
        try (Jedis jedis = cacheManager.jedis()) {
            consumer.accept(jedis);
        }
    }

    /**
     * 执行一系列pipeline操作
     */
    public static void execPipelined(Consumer<Pipeline> consumer) {
        try (Jedis jedis = cacheManager.jedis()) {
            try (Pipeline pipeline = jedis.pipelined()) {
                consumer.accept(pipeline);
                pipeline.sync();
            } catch (IOException e) {
                log.error("关闭通道失败", e);
            }
        }
    }

    /**
     * 执行一系列Jedis操作、并获得返回值
     */
    public static <R> R call(Function<Jedis, R> function) {
        try (Jedis jedis = cacheManager.jedis()) {
            return function.apply(jedis);
        }
    }

    /*    *//**
     * 执行一系列Pipelined操作、并获得返回值
     *//*
    public static List<String> callPipelined(Function<Pipeline, List<Response<String>>> function) {
        try (Jedis jedis = cacheManager.jedis()) {
            try (Pipeline pipeline = jedis.pipelined()) {
                List<Response<String>> responses = function.apply(pipeline);
                pipeline.sync();
                return responses.stream().map(Response::get).collect(Collectors.toList());
            } catch (IOException e) {
                log.error("关闭通道失败", e);
                return null;
            }
        }
    }*/

    /**
     * 执行一系列Pipelined操作、并获得返回值
     */
    public static <T> T callPipelined(Function<Pipeline, T> function) {
        try (Jedis jedis = cacheManager.jedis()) {
            try (Pipeline pipeline = jedis.pipelined()) {
                T t = function.apply(pipeline);
                pipeline.sync();
                return t;
            } catch (IOException e) {
                log.error("关闭通道失败", e);
                return null;
            }
        }
    }

    /**
     * 队列消费
     */
    public static void bqueueConsumer(String key,
            Consumer<String> consumer) {
        Jedis jedis = null;
        for (; ; ) {
            try {
                //初始化
                if (jedis == null) {
                    jedis = cacheManager.jedis();
                }

                List<String> brpop = jedis.brpop(60, key);
                if (brpop.isEmpty()) {
                    continue;
                }

                //消费
                String s = brpop.get(1);
                consumer.accept(s);
            } catch (Exception e) {
                log.error("消费队列 [{}] 异常", key);
                log.error("异常信息", e);
                try {
                    if (jedis != null) {
                        jedis.close();
                    }
                } finally {
                    jedis = cacheManager.jedis();
                }
            }
        }

    }
    public static void main(String[] args) {
        System.out.println(buildHashSegmentKey("127355", RedisKey.BAOJI_ROOM_KEY_HASH_PRE));
    }
}
