package com.panda.esportingplus.common;

import com.panda.esportingplus.common.enums.BizExceptionEnum;
import com.panda.esportingplus.common.exception.BusinessException;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;

import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;

/**
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
public class ResponsePacketTest {

    private static Logger logger = LoggerFactory.getLogger(ResponsePacketTest.class);

    public static ResponsePacket handleBusinessException(BusinessException ex,
            HandlerMethod handlerMethod) {

        try {
            MethodParameter returnType = handlerMethod.getReturnType();
            Type type = ((ParameterizedType) returnType
                    .getGenericParameterType()).getActualTypeArguments()[0];
            Class respClass = (Class) type;
            Constructor constructor = respClass.getDeclaredConstructor();
            constructor.setAccessible(true);

            logger.error("业务错误信息: {} ---> {}", ex.getErrMsg(), ex.getStackTrace()[0]);
            return ResponsePacket
                    .onError(ex.getErrCode(), ex.getErrMsg(), respClass.newInstance());
        } catch (Exception e) {
            logger.error("处理接口: {} 异常时出错, 错误信息: {}", handlerMethod.getMethod(),
                    e.getStackTrace()[0]);
            return ResponsePacket.onError(ex.getErrCode(), ex.getErrMsg(), new HashMap<>(1));
        }
    }

    public static ResponsePacket test(BizExceptionEnum bizExceptionEnum,
            Class respClass) {
        try {

            /*Constructor constructor = respClass.getDeclaredConstructor();
            constructor.setAccessible(true);*/

            ResponsePacket responsePacket;
            if (Number.class.isAssignableFrom(respClass)) {
                logger.info("number");
                responsePacket = ResponsePacket
                        .onError(bizExceptionEnum.getErrCode(), bizExceptionEnum.getErrMsg(), 0);
            } else if (Collection.class.isAssignableFrom(respClass)) {
                logger.info("list");
                responsePacket = ResponsePacket
                        .onError(bizExceptionEnum.getErrCode(), bizExceptionEnum.getErrMsg(),
                                new ArrayList<>());
            } else if (Boolean.class.isAssignableFrom(respClass)) {
                logger.info("boolean");
                responsePacket = ResponsePacket
                        .onError(bizExceptionEnum.getErrCode(), bizExceptionEnum.getErrMsg(),
                                Boolean.FALSE);
            } else {
                logger.info("object");
                responsePacket = ResponsePacket
                        .onError(bizExceptionEnum.getErrCode(), bizExceptionEnum.getErrMsg(),
                                new HashMap<>(0));
            }
            logger.info("转换成功 -> {}", responsePacket);
            return responsePacket;
        } catch (Exception e) {
            logger.error("转换失败!返回值类型 -> {}, 异常 -> {}", respClass, e);
            return ResponsePacket
                    .onError(bizExceptionEnum.getErrCode(), bizExceptionEnum.getErrMsg(),
                            new HashMap<>(0));
        }
    }

    public static void main(String[] args) {
        try {

            // System.out.println(Number.class.isAssignableFrom(Long.class));

            System.out.println("Number: " + test(BizExceptionEnum.PARAM_ENTRY_ERROR, Long.class));
            System.out.println("Map: " + test(BizExceptionEnum.PARAM_ENTRY_ERROR, Hashtable.class));
            System.out.println("List: " + test(BizExceptionEnum.PARAM_ENTRY_ERROR, Set.class));
            System.out.println("Boolean: " + test(BizExceptionEnum.PARAM_ENTRY_ERROR, Boolean.class));
            System.out.println("Object: " + test(BizExceptionEnum.PARAM_ENTRY_ERROR, Object.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
