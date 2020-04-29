package com.panda.esportingplus.common;

import com.panda.esportingplus.common.enums.BizExceptionEnum;
import com.panda.esportingplus.common.exception.BusinessException;
import com.panda.esportingplus.common.tools.ObjectTools;


import java.util.List;
import java.util.Set;

import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;


import org.hibernate.validator.HibernateValidator;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * 结合 spring validator 的验证工具类
 * 使用方式: 在需要验证的 bean 类上加 Validated 注解
 * 具体字段的注解参考 javax.validation.constraints
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
public class ValidateAssert {

    /**
     *  private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();
     *  使用 hibernate validtor 的快速失败机制
     * */
    private static final Validator VALIDATOR;

    static {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .addProperty( "hibernate.validator.fail_fast", "true" )
                .buildValidatorFactory();
        VALIDATOR =  validatorFactory.getValidator();
    }

    /**
     * 校验对象
     * @param object        待校验对象
     * @param groups        待校验的组
     * @throws BusinessException  校验不通过，则报BusinessException异常
     */
    public static void validateEntity(Object object, Class<?>... groups) throws BusinessException {
        Set<ConstraintViolation<Object>> constraintViolations = VALIDATOR.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            List<String> errors = constraintViolations.stream()
            .map(objectConstraintViolation ->
                    objectConstraintViolation.getPropertyPath().toString()+":"+ objectConstraintViolation
                            .getMessage())
                    .collect(Collectors.toList());

            throw new BusinessException(BizExceptionEnum.PARAM_VALID_FAIL, errors.toArray(new String[errors.size()]));
        }
    }

    /** spring 验证适配器 */
    private static final SpringValidatorAdapter VALIDATOR_ADAPTER = new SpringValidatorAdapter(VALIDATOR);

    /**
     * 验证结果绑定
     * @param targetObj
     * @return
     */
    private static Errors getConfiguration(Object targetObj) {
        return new BeanPropertyBindingResult(targetObj, targetObj.getClass().getSimpleName());
    }

    /**
     * 执行验证
     * @param validObj
     */
    private static void valid(Object validObj) {
        Errors errors = getConfiguration(validObj);
        ValidationUtils.invokeValidator(VALIDATOR_ADAPTER, validObj, errors);
        if (errors.hasErrors()) {
            throw new BusinessException(BizExceptionEnum.PARAM_VALID_FAIL, errors.getFieldErrors().get(0).getDefaultMessage());
        }
    }

    /**
     * 验证参数是否都不为空
     * 只要有一个参数为空就抛出异常
     * 都不为空将进行 bean 的参数校验
     * @param bizExceptionEnum
     * @param objects
     * @throws BusinessException
     */
    public static void allNotNull(BizExceptionEnum bizExceptionEnum, Object... objects) throws BusinessException {
        Assert.notNull(bizExceptionEnum, BizExceptionEnum.INTERNAL_SERVER_ERROR.getErrMsg());
        if (ObjectTools.isEmpty(objects)) {
            throw new BusinessException(bizExceptionEnum);
        }
        for (Object object : objects) {
            if (ObjectTools.isEmpty(object)) {
                throw new BusinessException(bizExceptionEnum);
            }
            // 如果不为空额外验证 JSR-303's
            Validated validated = AnnotationUtils.findAnnotation(object.getClass(), Validated.class);
            // 如果有验证注解
            if (ObjectTools.isNotEmpty(validated)) {
                valid(object);
            }
        }
    }

    /**
     * 验证参数是否都不为空
     * 只要有一个参数为空就抛出异常
     * 都不为空将进行 bean 的参数校验
     * @param bizExceptionEnum
     * @param objects
     * @throws BusinessException
     */
    public static void allNotNull(BizExceptionEnum bizExceptionEnum, List objects) throws BusinessException {
        Assert.notNull(bizExceptionEnum, BizExceptionEnum.INTERNAL_SERVER_ERROR.getErrMsg());
        if (ObjectTools.isEmpty(objects)) {
            throw new BusinessException(bizExceptionEnum);
        }
        for (Object object : objects) {
            if (ObjectTools.isEmpty(object)) {
                throw new BusinessException(bizExceptionEnum);
            }
            // 如果不为空额外验证 JSR-303's
            Validated validated = AnnotationUtils.findAnnotation(object.getClass(), Validated.class);
            // 如果有验证注解
            if (ObjectTools.isNotEmpty(validated)) {
                valid(object);
            }
        }
    }

    /**
     * 验证参数是否都为空
     * 只要有一个不为空就抛出
     * @param bizExceptionEnum
     * @param objects
     * @throws BusinessException
     */
    public static void allNull(BizExceptionEnum bizExceptionEnum, Object... objects) throws BusinessException {
        Assert.notNull(bizExceptionEnum, BizExceptionEnum.INTERNAL_SERVER_ERROR.getErrMsg());
        if (ObjectTools.isNotEmpty(objects)) {
            for (Object object : objects) {
                if (ObjectTools.isNotEmpty(object)) {
                    throw new BusinessException(bizExceptionEnum);
                }
            }

        }
    }

    /**
     * 验证参数是否包含不为空
     * 当且仅当所有参数都为空抛出异常
     * @param bizExceptionEnum
     * @param objects
     * @throws BusinessException
     */
    public static void hasNotNull(BizExceptionEnum bizExceptionEnum, Object... objects) throws BusinessException {
        Assert.notNull(bizExceptionEnum, BizExceptionEnum.INTERNAL_SERVER_ERROR.getErrMsg());
        if (ObjectTools.isEmpty(objects)) {
            throw new BusinessException(bizExceptionEnum);
        }
        boolean flag = true;
        for (Object object : objects) {
            if (ObjectTools.isNotEmpty(object)) {
                flag = false;
                break;
            }
        }
        if (flag) {
            throw new BusinessException(bizExceptionEnum);
        }

    }

    /**
     * 验证 bean 的所有字段的值是否都不为空
     * 当且仅当所有字段值都为空时抛出异常
     * @param bizExceptionEnum
     * @param objects
     * @throws BusinessException
     */
    public static void allFieldNotNull(BizExceptionEnum bizExceptionEnum, Object... objects) throws BusinessException {
        Assert.notNull(bizExceptionEnum, BizExceptionEnum.INTERNAL_SERVER_ERROR.getErrMsg());
        if (!ObjectTools.isEmpty(objects)) {
            for (Object object : objects) {
                boolean b = ObjectTools.allFieldIsEmpty(object);
                if (b) {
                    throw new BusinessException(bizExceptionEnum);
                }
            }
        }

    }

    /**
     * 验证条件是否为 true
     * 当表达式为 false 时抛出异常
     * @param expression
     * @param bizExceptionEnum
     * @throws BusinessException
     */
    public static void isTrue(boolean expression, BizExceptionEnum bizExceptionEnum) throws BusinessException {
        Assert.notNull(bizExceptionEnum, BizExceptionEnum.INTERNAL_SERVER_ERROR.getErrMsg());
        if (!expression) {
            throw new BusinessException(bizExceptionEnum);
        }
    }

    /**
     * 验证条件是否为 false
     * 当表达式为 true 时抛出异常
     * @param expression
     * @param bizExceptionEnum
     * @throws BusinessException
     */
    public static void isFalse(boolean expression, BizExceptionEnum bizExceptionEnum) throws BusinessException {
        Assert.notNull(bizExceptionEnum, BizExceptionEnum.INTERNAL_SERVER_ERROR.getErrMsg());
        if (expression) {
            throw new BusinessException(bizExceptionEnum);
        }
    }

    public static void main(String[] args) {
        allNotNull(BizExceptionEnum.PARAM_VALID_FAIL, 123, "123");
        allFieldNotNull(BizExceptionEnum.PARAM_VALID_FAIL, new Object());
    }

}

