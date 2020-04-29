package com.panda.esportingplus.common.exception;

import com.panda.esportingplus.common.enums.BizExceptionEnum;
import com.panda.esportingplus.common.tools.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 苹果支付异常
 * @author xusisi
 */
public class ApplePayException extends RuntimeException {

    private static final long serialVersionUID = 4718810982054393564L;

    /** 错误码 */
    private Integer errCode;
    /** 错误提示消息 */
    private String errMsg;
    /** 异常堆栈信息 */
    private Throwable caused;

    public ApplePayException(BizExceptionEnum bizExceptionEnum){
        this.errCode = bizExceptionEnum.getErrCode();
        this.errMsg = bizExceptionEnum.getErrMsg();
    }

    public ApplePayException(BizExceptionEnum bizExceptionEnum, Throwable caused){
        super(caused);
        this.errCode = bizExceptionEnum.getErrCode();
        this.errMsg = bizExceptionEnum.getErrMsg();
    }

    public ApplePayException(BizExceptionEnum bizExceptionEnum, String[] formatMsg){
        this.errCode = bizExceptionEnum.getErrCode();
        this.errMsg = StringUtils.formatTemplate(bizExceptionEnum.getErrMsg(), formatMsg);
    }

    public ApplePayException(BizExceptionEnum bizExceptionEnum, String formatMsg){
        this.errCode = bizExceptionEnum.getErrCode();
        this.errMsg = String.format(bizExceptionEnum.getErrMsg(), formatMsg);
        // this.errMsg = new MessageFormat(bizExceptionEnum.getErrMsg()).format(formatMsg);
    }

    public ApplePayException(Integer errCode, String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public ApplePayException(BizExceptionEnum bizExceptionEnum, String formatMsg, Throwable caused){
        super(caused);
        this.errCode = bizExceptionEnum.getErrCode();
        this.errMsg = StringUtils.formatTemplate(bizExceptionEnum.getErrMsg(), formatMsg);
    }

    public Integer getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public Throwable getCaused() {
        return caused;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public static void main(String[] args) {
        ApplePayException businessException =
                new ApplePayException(BizExceptionEnum.PARAM_VALID_FAIL);
        System.out.println(businessException);
    }

}
