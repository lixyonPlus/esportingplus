package com.panda.esportingplus.common.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

public final class FastJsonUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(FastJsonUtils.class);

    public static String toJson(Object bean) {
        try {
            return JSON.toJSONString(bean);
        } catch (Exception e) {
            LOGGER.error("FastJsonUtils.toJson ex, bean=" + bean, e);
        }
        return null;
    }

    public static <T> T fromJson(String json, Class<T> clazz) {

        try {
            return JSON.parseObject(json, clazz);
        } catch (Exception e) {
            LOGGER.error("FastJsonUtils.fromJson ex, json=" + json + ", clazz=" + clazz, e);
        }
        return null;
    }

    public static <T> T fromMap(Object map, TypeReference<T> type) {
        try {
            return JSON.parseObject(toJson(map), type);
        } catch (Exception e) {
            LOGGER.error("FastJsonUtils.fromJson ex, map=" + map + ", type=" + type, e);
        }

        return null;
    }

    public static <T> T fromJson(byte[] json, Class<T> clazz) {
        return fromJson(new String(json, StandardCharsets.UTF_8), clazz);
    }

    public static <T> List<T> fromJsonToList(String json, Class<T> type) {
        try {
            return JSON.parseArray(json, type);
        } catch (Exception e) {
            LOGGER.error("FastJsonUtils.fromJsonToList ex, json=" + json + ", type=" + type, e);
        }
        return null;
    }

    public static <T> T fromJson(String json, Type type) {
        try {
            return JSON.parseObject(json, type);
        } catch (Exception e) {
            LOGGER.error("FastJsonUtils.fromJson ex, json=" + json + ", type=" + type, e);
        }
        return null;
    }

    public static boolean mayJson(String json) {
        if (StringUtils.isBlank(json)) {
            return false;
        }
        if (json.charAt(0) == '{' && json.charAt(json.length() - 1) == '}') {
            return true;
        }
        return json.charAt(0) == '[' && json.charAt(json.length() - 1) == ']';
    }

    public static String toJson(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(64 * map.size());
        sb.append('{');
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        if (it.hasNext()) {
            append(it.next(), sb);
        }
        while (it.hasNext()) {
            sb.append(',');
            append(it.next(), sb);
        }
        sb.append('}');
        return sb.toString();
    }

    private static void append(Map.Entry<String, String> entry, StringBuilder sb) {
        String key = entry.getKey(), value = entry.getValue();
        if (value == null) {
            value = StringUtils.EMPTY;
        }

        sb.append('"').append(key).append('"');
        sb.append(':');
        sb.append('"').append(value).append('"');
    }

    public static JSONObject toParamMap(Object object) {
        return toParamMap("", object);
    }

    public static JSONObject toParamMap(String prefix, Object object) {
        if (!(object instanceof Map)) {
            object = JSON.parseObject(JSON.toJSONString(object));
        }
        JSONObject result = new JSONObject();
        mapParamHandler(prefix, object, result);
        return result;
    }

    @SuppressWarnings("unchecked")
    private static void mapParamHandler(String prefix, Object o, JSONObject jsonObject) {

        //处理普通类型
        if (BeanUtils.isSimpleValueType(o.getClass())) {
            jsonObject.put(prefix, o);
            return;
        }

        //是集合的处理方法
        if (o instanceof List) {
            List list = (List) o;
            for (int i = 0; i < list.size(); i++) {
                mapParamHandler(prefix + "." + i, list.get(i), jsonObject);
            }
        }
        //是Map的处理方式
        if (o instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) o;
            map.entrySet().stream()
                    .filter(it -> Objects.nonNull(it.getValue()))
                    .forEach(it -> {
                        String newPrefix =
                                StringUtils.isNotEmpty(prefix) ? prefix + "." + it.getKey()
                                        : it.getKey();
                        mapParamHandler(newPrefix, it.getValue(), jsonObject);
                    });
        }
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("1","a");
        map.put("2","b");
        map.put("3","c");
        System.out.println(toJson(map));
    }
}