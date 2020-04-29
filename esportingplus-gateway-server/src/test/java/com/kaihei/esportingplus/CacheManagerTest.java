package com.kaihei.esportingplus;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.kaihei.commons.cache.api.spi.common.CacheManager;
import com.kaihei.commons.cache.api.spi.common.CacheManagerFactory;
import com.kaihei.esportingplus.common.constant.RedisKey;
import com.kaihei.esportingplus.common.tools.DateUtil;
import com.kaihei.esportingplus.common.tools.JacksonUtils;
import com.kaihei.esportingplus.common.web.UserSessionContext;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;
import org.springframework.util.ResourceUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Unit test for simple App.
 */
public class CacheManagerTest
{
    private CacheManager cacheManager;

    private int totalUser = 30000;

    @Before
    public void initCacheManager()
    {
        Properties prop = new Properties();
        prop.setProperty("redis.cluster-model", "single");
//        prop.setProperty("redis.nodes", "[\"120.77.146.135:6380\"]");
//        prop.setProperty("redis.password", "baobao");
        prop.setProperty("redis.nodes", "[\"120.77.244.230:6379\"]");
        prop.setProperty("redis.password", "NzkwMTExNjVhMGFlN");
        prop.setProperty("redis.database", "0");
        prop.setProperty("redis.maxTotal", String.valueOf(2000));
        prop.setProperty("redis.minIdle", String.valueOf(1));
        prop.setProperty("redis.maxIdle", String.valueOf(10));
        prop.setProperty("redis.maxWaitMillis", String.valueOf(20000));
        prop.setProperty("redis.evictorShutdownTimeoutMillis", "60000");
        prop.setProperty("redis.testOnBorrow", "true");
        CacheManagerFactory.create().init(prop);
        cacheManager = CacheManagerFactory.create();
    }

    @Test
    public void test(){
        Set<? extends Number> numbers = Sets.newSet(1L, 11L, 22);
        Set<? extends Number> numbers2 = Sets.newSet(1L, 11L, 22,24,324);
        numbers.removeAll(numbers2);
        System.out.println(numbers);
    }

    @Test
    public void testBitMap(){
        Boolean b1 = cacheManager.getbit("pvp:freeteam:baojis:distributed:bitmap:1:dcf121b7", 312);
        Boolean b2= cacheManager.getbit("pvp:freeteam:baojis:distributed:bitmap:1:dcf121b7", 1453);
        Boolean b3 = cacheManager.getbit("pvp:freeteam:baojis:distributed:bitmap:1:dcf121b7", 1455);
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);

    }

    @Test
    public void testNull(){
        // cacheManager.set(RedisKey.PVP_FREE_TEAM_MATCHING_BOSS_TODAY_MATCHED,"");
        //  System.out.println(cacheManager.exists(RedisKey.PVP_FREE_TEAM_MATCHING_BOSS_TODAY_MATCHED));
    }

    @Test
    public void testExpire(){
        long x = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MAX).toEpochSecond(ZoneOffset.of("+8"));
        System.out.println(x);
        System.out.println(x - System.currentTimeMillis() / 1000);
        System.out.println(DateUtil.localDateTime2Date(LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MAX)));
        cacheManager.set("1","1");
        cacheManager.expire("1", (int)DateUtil.secondsBetween(LocalDateTime.now(),DateUtil.nowMax()));
    }

    @Test
    public void testScan(){
        Jedis jedis = cacheManager.jedis();
        ScanResult<String> scan = null;
        ScanParams params = new ScanParams();
        params.match("*");
        scan = jedis.scan(0 + "",params);
        for (;;){
            Integer index = Integer.valueOf(new String(scan.getCursorAsBytes()));
            System.out.println(index);
            System.out.println(scan.getResult());
            if(index == 0){
                break;
            }else{
                scan = jedis.scan(index+ "",params);
            }
        }


        if(jedis != null){
            jedis.close();
        }
    }

    @Test
    public void testBjdj(){
        System.out.println(JacksonUtils.toJson(cacheManager.hgetAll("randori:premade:detail:").get("9fa1eb35")));
    }

    @Test
    public void testTtl() throws InterruptedException {
        cacheManager.set("111","111",-1);
        System.out.println(cacheManager.ttl("111"));
        Thread.sleep(3000);
        cacheManager.set("111","111",10);
        System.out.println(cacheManager.ttl("111"));

    }
    @Test
    public void tesPpush() {
        cacheManager.lpush("test_push","yzhData");
    }
    @Test
    public void testBrpop() {
        Jedis jedis = cacheManager.jedis();
        while (true) {
            try {
                List<String> brpop = jedis.brpop(60, "test_push");
                if (brpop.isEmpty()) {
                    continue;
                }
                String s = brpop.get(1);
                System.out.println(s);
            } catch (Exception e) {
                try {
                    jedis.close();
                } finally {
                    jedis = cacheManager.jedis();
                }
            }
        }
    }

    @Test
    public void testTtl2(){

        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i <100 ; i++) {
            executorService.submit(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String proKey = String.format(RedisKey.PREVENT_REPEAT_OPERATION, "1111", "test");
                Boolean exists = cacheManager.exists(proKey);
                if (!exists) {
                    // 标记不存在, 放置标记并放行
                    cacheManager.set(proKey, "1111", 100);
                }
            });
            countDownLatch.countDown();
        }

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDate(){
        System.out.println(DateUtil.getTimeStrampMiniseconds());
        System.out.println(DateUtil.getTimeStamp());
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void testRalation(){
        Map<String, String> hgetAll = cacheManager.hgetAll("order:exclusive:relation:4629");
        System.out.println(hgetAll);
    }

    @Test
    public void testLua() throws IOException {
        System.out.println("开始....");
        int ttl = new Random().nextInt(3) + 1;
//        int ttl = 1;
        int qps =  1600;
        int qpsTotal = qps * ttl;
        System.out.println("ttl : " + ttl);
        System.out.println("qpsTotal : " + qpsTotal);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 30000; i++) {
            String limitLua = Files
                    .toString(ResourceUtils.getFile("classpath:rateLimit.lua"),
                            StandardCharsets.UTF_8);
            long eval = cacheManager.eval(limitLua, Arrays.asList("a"),
                    Lists.newArrayList(qpsTotal + "", ttl+""));
            if(eval / ttl >= qps){
                System.out.println("lua自增达到限流qps:eval="+eval +":" + ttl);
            }
            if((System.currentTimeMillis() - start) == 1000){
                System.out.println("qps："+ i);
                System.out.println("lua："+ eval);
            }
            if(eval == 0){
                System.out.println("lua自增达到限流耗时："+ (System.currentTimeMillis() - start));
                break;
            }
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - start));

    }

    @Test
    public void testZset(){
        cacheManager.zAdd("a",1,"yzh");
        cacheManager.zAdd("a",2,"yzh");
        cacheManager.zRem("a","yzh");
    }

    @Test
    public void testPickMatching(){
        cacheManager.redissonClient().getKeys()
                .getKeysByPattern("*level_score_change*")
                .forEach(key->{
                    System.out.println(key);
                    cacheManager.del(key);
                });
    }

    @Test
    public void addUsers() throws IOException {
        Path path = Paths.get("C:\\Users\\admin\\Desktop\\Jemeter\\10w匹配用户.txt");
        Pipeline pipelined = cacheManager.jedis().pipelined();
        for (int i = 0; i < totalUser; i++) {
            String uid = String.format("%08d", i);
            UserSessionContext boss = new UserSessionContext();
            boss.setUsername("test_" + uid);
            boss.setUid(uid);
            boss.setChickenId(uid);
            boss.setId(i);
            boss.setPythonToken(uid + "." + "yzh_test_" + i);
            boss.setAvatar(
                    "http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTL6dYhxxccWiaq7qbUVTVibNJukLzCkROdazaRl1SeLGtToOlPMyicZvdQZupjVQJHAPWQeCY66JDPkw/132");
            boss.setSex(1);
            pipelined.set("user:yzh_test_" + i, JSON.toJSONString(boss));
            pipelined.set("access_to_refresh:yzh_test_" + i, UUID.randomUUID().toString());
            java.nio.file.Files.write(path, (uid+".yzh_test_" + i +"\r\n").getBytes(), StandardOpenOption.APPEND);
            System.out.println("add user:"+i);
        }
        pipelined.sync();
    }

    @Test
    public void delUsers(){
        Pipeline pipelined = cacheManager.jedis().pipelined();
        for (int i = 0; i < totalUser; i++) {
            pipelined.del("user:yzh_test_" + i);
            pipelined.del("access_to_refresh:yzh_test_" + i);
            System.out.println("del user:"+i);
        }
        pipelined.sync();
    }
    @Test
    public void delUsers2(){
        cacheManager.redissonClient().getKeys()
                    .getKeysByPattern("app_token:*")
                    .forEach(key->{
                        String token = cacheManager.get(key,String.class);
                        cacheManager.del("user:" + token);
                        cacheManager.del("access_to_refresh:" + token);
                        cacheManager.del("access:" + token);
                        cacheManager.del(key);
                        System.out.println("del user:"+key);
                    });
    }

    @Test
    public void testPunishment() {

    }
    @Test
    public void addUser() {
        UserSessionContext baoji1 = new UserSessionContext();
        baoji1.setUsername("AOp7t");
        baoji1.setUid("1a2f4704");
        baoji1.setChickenId("500500");
        baoji1.setId(1285);
        baoji1.setPythonToken("1a2f4704.yzh_test_baoji");
        baoji1.setAvatar(
                "http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTL6dYhxxccWiaq7qbUVTVibNJukLzCkROdazaRl1SeLGtToOlPMyicZvdQZupjVQJHAPWQeCY66JDPkw/132");
        baoji1.setSex(1);
        cacheManager.set("user:yzh_test_baoji", JSON.toJSONString(baoji1));
        cacheManager.set("access_to_refresh:yzh_test_baoji", UUID.randomUUID().toString());

//        UserSessionContext baoji2 = new UserSessionContext();
//        baoji2.setUsername("懒懒");
//        baoji2.setUid("370b8537");
//        baoji2.setChickenId("54237724");
//        baoji2.setId(1179);
//        baoji2.setPythonToken("370b8537.yzh_test_baoji2");
//        baoji2.setAvatar(
//                "http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTL6dYhxxccWiaq7qbUVTVibNJukLzCkROdazaRl1SeLGtToOlPMyicZvdQZupjVQJHAPWQeCY66JDPkw/132");
//        baoji2.setSex(1);
//        cacheManager.set("user:yzh_test_baoji2", JSON.toJSONString(baoji2));
//        cacheManager.set("access_to_refresh:yzh_test_baoji2", UUID.randomUUID().toString());
//
//        UserSessionContext boss1 = new UserSessionContext();
//        boss1.setUsername("阿西吧");
//        boss1.setUid("940fdf2a");
//        boss1.setChickenId("46024239");
//        boss1.setId(36);
//        boss1.setPythonToken("658da157.yzh_test_boss1");
//        boss1.setAvatar(
//                "http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTL6dYhxxccWiaq7qbUVTVibNJukLzCkROdazaRl1SeLGtToOlPMyicZvdQZupjVQJHAPWQeCY6w/132");
//        boss1.setSex(1);
//        cacheManager.set("user:yzh_test_boss1", JSON.toJSONString(boss1));
//        cacheManager.set("access_to_refresh:yzh_test_boss1", UUID.randomUUID().toString());
//
//        UserSessionContext boss2 = new UserSessionContext();
//        boss2.setUsername("赤城之焰");
//        boss2.setUid("4895e54a");
//        boss2.setChickenId("39957092");
//        boss2.setId(30);
//        boss2.setPythonToken("658da157.yzh_test_boss2");
//        boss2.setAvatar(
//                "http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTL6dYhxxccWiaq7qbUVTVibNJukLzCkROdazaRl1SeLGtToOlPMyicZvdQZupjVQJHAPWQeCY66JDPkw/132");
//        boss2.setSex(1);
//        cacheManager.set("user:yzh_test_boss2", JSON.toJSONString(boss2));
//        cacheManager.set("access_to_refresh:yzh_test_boss2", UUID.randomUUID().toString());
//
//        UserSessionContext boss3 = new UserSessionContext();
//        boss3.setUsername("西瓜皮s");
//        boss3.setUid("0cfdf512");
//        boss3.setChickenId("57094638");
//        boss3.setId(14);
//        boss3.setPythonToken("658da157.yzh_test_boss3");
//        boss3.setAvatar(
//                "http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTL6dYhxxccWiaq7qbUVTVibNJukLzCkROdazaRl1SeLGtToOlPMyicZvdQZupjVQJHAPWQeCY66JDPkw/132");
//        boss3.setSex(1);
//        cacheManager.set("user:yzh_test_boss3", JSON.toJSONString(boss3));
//        cacheManager.set("access_to_refresh:yzh_test_boss3", UUID.randomUUID().toString());
//
//        UserSessionContext boss4 = new UserSessionContext();
//        boss4.setUsername("暴鸡电竞-JOJO桑");
//        boss4.setUid("2ceee331");
//        boss4.setChickenId("89008569");
//        boss4.setId(17);
//        boss4.setPythonToken("658da157.yzh_test_boss4");
//        boss4.setAvatar(
//                "http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTL6dYhxxccWiaq7qbUVTVibNJukLzCkROdazaRl1SeLGtToOlPMyicZvdQZupjVQJHAPWQeCY66JDPkw/132");
//        boss4.setSex(1);
//        cacheManager.set("user:yzh_test_boss4", JSON.toJSONString(boss4));
//        cacheManager.set("access_to_refresh:yzh_test_boss4", UUID.randomUUID().toString());
//
    }
}
