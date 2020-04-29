package com.panda.esportingplus.common.exception;

import com.panda.esportingplus.common.enums.BizExceptionEnum;
import com.panda.esportingplus.common.tools.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 业务类异常
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 4718810982054393564L;

    /** 错误码 */
    private Integer errCode;
    /** 错误提示消息 */
    private String errMsg;
    /** 异常堆栈信息 */
    private Throwable caused;
    private Object data;
    public BusinessException(){

    }
    public BusinessException(BizExceptionEnum bizExceptionEnum){
        this.errCode = bizExceptionEnum.getErrCode();
        this.errMsg = bizExceptionEnum.getErrMsg();
    }

    public BusinessException(BizExceptionEnum bizExceptionEnum, Throwable caused){
        super(caused);
        this.errCode = bizExceptionEnum.getErrCode();
        this.errMsg = bizExceptionEnum.getErrMsg();
    }

    public BusinessException(BizExceptionEnum bizExceptionEnum, String[] formatMsg){
        this.errCode = bizExceptionEnum.getErrCode();
        this.errMsg = StringUtils.formatTemplate(bizExceptionEnum.getErrMsg(), formatMsg);
    }

    public BusinessException(BizExceptionEnum bizExceptionEnum, Object... formatMsg) {
        this.errCode = bizExceptionEnum.getErrCode();
        this.errMsg = String.format(bizExceptionEnum.getErrMsg(), formatMsg);
        // this.errMsg = new MessageFormat(bizExceptionEnum.getErrMsg()).format(formatMsg);
    }

    public BusinessException(Integer errCode,String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public BusinessException(BizExceptionEnum bizExceptionEnum, String formatMsg, Throwable caused){
        super(caused);
        this.errCode = bizExceptionEnum.getErrCode();
        this.errMsg = StringUtils.formatTemplate(bizExceptionEnum.getErrMsg(), formatMsg);
    }

    public BusinessException withData(Object data) {
        this.data = data;
        return this;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void getCode() {

    }

    public String getErrMsg() {
        return errMsg;
    }

    public Throwable getCaused() {
        return caused;
    }

    public Object getData() {
        return data;
    }
    public static BusinessException newInstanceExceptionWithData(BizExceptionEnum bizExceptionEnum,Object data){
        BusinessException businessException = new BusinessException(bizExceptionEnum);
        businessException.data =data;
        return businessException;
    }
    public static BusinessException newInstanceExceptionWithData(Integer errCode, String errMsg,Object data){
        BusinessException businessException = new BusinessException(errCode,errMsg);
        businessException.data =data;
        return businessException;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public static void main(String[] args) {
        BusinessException businessException =
                new BusinessException(BizExceptionEnum.PARAM_VALID_FAIL);
        System.out.println(businessException);
        FuInterface f= BusinessException::getCode;
    }



    @FunctionalInterface
    interface FuInterface{
       public void  setName(BusinessException e);
    }

}
