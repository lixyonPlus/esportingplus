package com.panda.esportingplus.common.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import java.io.Serializable;
import java.util.Arrays;
import javax.validation.constraints.NotNull;

/**
 * @author shusong.liang
 */
public interface JsonSerializable extends Serializable {

    default String toJSONString() {
        return JSON.toJSONString(this);
    }

    default String toJSONString(String... ignore) {
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().addAll(Arrays.asList(ignore));
        return JSON.toJSONString(this, filter);
    }

    default JSONObject cast() {
        return JSON.parseObject(toJSONString());
    }

    default JSONObject cast(String... ignore) {
        return JSON.parseObject(toJSONString(ignore));
    }

    default <T> T cast(@NotNull Class<? extends T> t) {
        return JSON.parseObject(toJSONString(), t);
    }

    default <T> T cast(@NotNull Class<? extends T> t, String... ignore) {
        return JSON.parseObject(toJSONString(ignore), t);
    }

    default <T> T cast(@NotNull TypeReference<T> typeReference) {
        return JSON.parseObject(toJSONString(), typeReference.getType());
    }

    default <T> T cast(@NotNull TypeReference<T> typeReference, String... ignore) {
        return JSON.parseObject(toJSONString(ignore), typeReference.getType());
    }

    default <T> T cast(@NotNull T t) {
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(t));
        jsonObject.putAll(cast());

        return JSON.parseObject(jsonObject.toJSONString(), (Class<T>) t.getClass());
    }


    default <T> T cast(@NotNull T t, String... ignore) {
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(t));
        jsonObject.putAll(cast(ignore));

        return JSON.parseObject(jsonObject.toJSONString(), (Class<T>) t.getClass());
    }

    default <T> T cast(@NotNull T t, TypeReference<T> typeReference) {
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(t));
        jsonObject.putAll(cast());
        return JSON.parseObject(jsonObject.toJSONString(), typeReference.getType());
    }

    default <T> T cast(@NotNull T t, TypeReference<T> typeReference, String... ignore) {
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(t));
        jsonObject.putAll(cast(ignore));
        return JSON.parseObject(jsonObject.toJSONString(), typeReference.getType());
    }
}
