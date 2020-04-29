package com.panda.esportingplus.common;

import com.panda.esportingplus.common.enums.BizExceptionEnum;
import com.panda.esportingplus.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 响应信息主体
 *
 * @param <T>
 * @author shusong.liang
 */
@Slf4j
public class ResponsePacket<T> implements Serializable {

    private static final long serialVersionUID = -5836459412110053424L;

    private String msg = "";

    private int code;

    private T data;

    public ResponsePacket() {
    }

    /**
     * 成功, 无返回结果
     *
     * @return
     */
    public static ResponsePacket onSuccess() {
        ResponsePacket responsePacket = new ResponsePacket();
        responsePacket.setCode(BizExceptionEnum.SUCCESS.getErrCode());
        responsePacket.setMsg(BizExceptionEnum.SUCCESS.getErrMsg());
        responsePacket.setData(new HashMap<>(1));
        return responsePacket;
    }

    /**
     * 成功, 返回自定义的 vo
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponsePacket<T> onSuccess(T data) {
        ResponsePacket responsePacket = new ResponsePacket();
        responsePacket.setCode(BizExceptionEnum.SUCCESS.getErrCode());
        responsePacket.setMsg(BizExceptionEnum.SUCCESS.getErrMsg());
        responsePacket.setData(data);
        return responsePacket;
    }

    /**
     * 服务熔断, 无返回结果
     *
     * @return
     */
    public static ResponsePacket onHystrix() {
        ResponsePacket responsePacket = new ResponsePacket();
        responsePacket.setCode(BizExceptionEnum.HYSTRIX_SERVER.getErrCode());
        responsePacket.setMsg(BizExceptionEnum.HYSTRIX_SERVER.getErrMsg());
        responsePacket.setData(new HashMap<>(1));
        return responsePacket;
    }

    /**
     * 服务熔断, 无返回结果
     */
    public static ResponsePacket onHystrix(Throwable t) {
        log.error("服务熔断", t);
        throw new BusinessException(BizExceptionEnum.HYSTRIX_SERVER);
    }

    /**
     * [真的]服务熔断, 无返回结果
     */
    public static ResponsePacket hystrix() {
        throw new BusinessException(BizExceptionEnum.HYSTRIX_SERVER);
    }

    /**
     * 错误, 无返回结果, 错误码为服务器内部错误(500)
     *
     * @return
     */
    public static ResponsePacket onError() {
        ResponsePacket responsePacket = new ResponsePacket();
        responsePacket.setCode(BizExceptionEnum.INTERNAL_SERVER_ERROR.getErrCode());
        responsePacket.setMsg(BizExceptionEnum.INTERNAL_SERVER_ERROR.getErrMsg());
        responsePacket.setData(new HashMap<>(1));
        return responsePacket;
    }

    /**
     * 错误, 错误码为服务器内部错误(500), 输入data参数，避免类型转换错误
     *
     * @return
     */
    public static ResponsePacket onError(Object data) {
        ResponsePacket responsePacket = new ResponsePacket();
        responsePacket.setCode(BizExceptionEnum.INTERNAL_SERVER_ERROR.getErrCode());
        responsePacket.setMsg(BizExceptionEnum.INTERNAL_SERVER_ERROR.getErrMsg());
        responsePacket.setData(data);
        return responsePacket;
    }

    /**
     * 错误, 无返回结果, 错误码为自定义的业务异常
     *
     * @param bizExceptionEnum
     * @return
     */
    public static ResponsePacket onError(BizExceptionEnum bizExceptionEnum) {
        ResponsePacket responsePacket = new ResponsePacket();
        responsePacket.setCode(bizExceptionEnum.getErrCode());
        responsePacket.setMsg(bizExceptionEnum.getErrMsg());
        responsePacket.setData(new HashMap<>(1));
        return responsePacket;
    }

    public static ResponsePacket onError(BizExceptionEnum bizExceptionEnum, Object... v) {
        ResponsePacket responsePacket = new ResponsePacket();
        responsePacket.setCode(bizExceptionEnum.getErrCode());
        responsePacket.setMsg(String.format(bizExceptionEnum.getErrMsg(),v));
        responsePacket.setData(new HashMap<>(1));
        return responsePacket;
    }

//    public static ResponsePacket onError(BizExceptionEnum bizExceptionEnum, Object data) {
//        ResponsePacket responsePacket = new ResponsePacket();
//        responsePacket.setCode(bizExceptionEnum.getErrCode());
//        responsePacket.setMsg(bizExceptionEnum.getErrMsg());
//        responsePacket.setData(data);
//        return responsePacket;
//    }

    /**
     * 错误, 返回结果为 null, 错误码为自定义的 code 和 msg
     *
     * @param code
     * @param msg
     * @return
     */
    public static ResponsePacket onError(int code, String msg) {
        ResponsePacket responsePacket = new ResponsePacket();
        responsePacket.setCode(code);
        responsePacket.setMsg(msg);
        // responsePacket.setData(new HashMap<>(1));
        responsePacket.setData(null);
        return responsePacket;
    }

    public static <T> ResponsePacket onError(int code, String msg, T data) {
        ResponsePacket responsePacket = new ResponsePacket();
        responsePacket.setCode(code);
        responsePacket.setMsg(msg);
        responsePacket.setData(data);
        return responsePacket;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean responseSuccess() {
        return this.code == BizExceptionEnum.SUCCESS.getErrCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
