package com.panda.esportingplus.user.bulider;

import com.panda.esportingplus.common.constant.RedisKey;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * @author shusong.liang
 * @Title: RedisKeySegmentBuilder
 * @Description: redis分片key构造器
 * @date 2020/03/25 14:15
 */
public abstract class RedisKeySegmentBuilder {

    /**
     * HASH分片大小,为尽量保证每个HASH的数量小于1000，以1000w用户计算设置HASH总分片数量为16383
     */
    protected static int HASH_SEGMENT_SIZE = 2 << 13;

    public static String bulid(RedisKeySegmentType segmentType, String segmentSeed,
            String... keyPrefixs) {
        StringBuilder key = new StringBuilder();
        for (String keyPrefix : keyPrefixs) {
            key.append(keyPrefix);
            key.append(RedisKey.KEY_SEPARATOR);
        }
        key.append(segmentType.getBuilder().bulidSegment(segmentSeed));
        return key.toString();
    }

    public int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public abstract String bulidSegment(String segmentSeed);

    public static void main(String[] args) throws IOException {

        String[] uids = getUids();
        String[] token = getToken();
        FileWriter writer = new FileWriter("C:\\Users\\Administrator\\Desktop\\uid-token.txt");
        for (int i=0;i< uids.length-1; i++) {
            if (StringUtils.isNotBlank(uids[i]) && StringUtils.isNotBlank(token[i])) {
                writer.write(uids[i] + "." + token[i] + "\r\n");
            }
        }
        writer.flush();
        writer.close();

//        String[] uids = getUids();//{"3wcuz000", "3we7z000"/*, "4737274f", "018db03d", "c2a1e026", "386a3166",
//                //"3e932648", "d0b56be0", "f7623359", "ed40c968", "da3e6c67", "0b2a41e0", "eaa1ed19",
//                //"8eee1eac", "ee8b9965", "d10da81a", "b4d8386f", "f09e25a1", "4b14c32e", "c0316c3e",
//                //"83d77e83"*/};
//        FileWriter writer = new FileWriter("C:\\Users\\Administrator\\Desktop\\redis-token.txt");
//        for(String uid : uids){
        System.out.println("hget "+ bulid(RedisKeySegmentType.HASH, "81a31cb5",
                    "user:uid:mapping:token:type:app_token")+" "+"81a31cb5" + "\r\n");
//        }
//        writer.flush();
//        writer.close();

    }

    private static String[] getUids() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\uids.txt"));
        List<String> list = new ArrayList<>();
        String s = null;
        while ((s = reader.readLine()) != null) {
            list.add(s.trim());
        }
        return list.toArray(new String[list.size()]);
    }

    private static String[] getToken() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\tonken.txt"));
        List<String> list = new ArrayList<>();
        String s = null;
        while ((s = reader.readLine()) != null) {
            list.add(s.trim());
        }
        return list.toArray(new String[list.size()]);
    }
}
