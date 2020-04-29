package com.panda.esportingplus.common.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.Serializable;
import org.springframework.beans.BeanUtils;

public interface Castable extends Serializable {

    default JSONObject cast() {
        return JSON.parseObject(JSON.toJSONString(this));
    }

    default <T> T cast(Class<T> clazz) {
        return cast(clazz, null);
    }

    default <T> T cast(Class<T> clazz, String... ignore) {
        T t = BeanUtils.instantiate(clazz);
        BeanUtils.copyProperties(this, t, ignore);
        return t;
    }

    default void cast(Object t) {
        BeanUtils.copyProperties(this, t);
    }

    default void cast(Object t, String... ignore) {
        BeanUtils.copyProperties(this, t, ignore);
    }

    default String toJsonString() {
        return JSON.toJSONString(this);
    }
}
