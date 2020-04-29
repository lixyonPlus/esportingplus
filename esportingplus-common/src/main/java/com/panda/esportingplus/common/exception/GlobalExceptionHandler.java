package com.panda.esportingplus.common.exception;

import com.panda.esportingplus.common.ResponsePacket;
import com.panda.esportingplus.common.enums.BizExceptionEnum;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 *
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 业务异常处理
     */
    @SuppressWarnings("rawtypes")
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponsePacket handleBusinessException(BusinessException ex) {
        logger.error("业务错误信息: {}", ex.getErrMsg());
        for (int i = 0; i < ex.getStackTrace().length; i++) {
            StackTraceElement stackTraceElement = ex.getStackTrace()[i];
            if (stackTraceElement.getClassName().startsWith("com.kaihei.") && !stackTraceElement
                    .getClassName().contains("$$")) {
                logger.error("{}", stackTraceElement);
            }
        }

        if (ex.getData() == null) {
            return ResponsePacket.onError(ex.getErrCode(), ex.getErrMsg(), null);
        }
        return ResponsePacket.onError(ex.getErrCode(), ex.getErrMsg(), ex.getData());
    }

    /**
     * 非法参数异常处理
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponsePacket paramError(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        BizExceptionEnum bizExceptionEnum = BizExceptionEnum.PARAM_VALID_FAIL;

        return ResponsePacket.onError(HttpStatus.BAD_REQUEST.value(), String.format(
                bizExceptionEnum.getErrMsg(), bindingResult.getFieldError()
                        .getDefaultMessage()), new HashMap<>(0));
    }

    /**
     * 非法参数异常处理
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponsePacket paramError(IllegalArgumentException e) {
        String bindingResult = e.getMessage();
        BizExceptionEnum bizExceptionEnum = BizExceptionEnum.PARAM_VALID_FAIL;

        return ResponsePacket.onError(HttpStatus.BAD_REQUEST.value(), String.format(
                bizExceptionEnum.getErrMsg(), bindingResult), new HashMap<>(0));
    }

    /**
     * 系统内部异常处理
     * @param ex
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponsePacket handleAllException(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return ResponsePacket.onError();
    }

    //((MethodArgumentNotValidException)ex).getBindingResult() TODO
}
