package com.panda.esportingplus.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.dozer.DozerBeanMapper;

/**
 * 类对象属性值拷贝
 */
public class BeanMapper {

    private static DozerBeanMapper dozer = new DozerBeanMapper();

    /**
     * 对象属性值拷贝
     */
    public static <T> T map(Object source, Class<T> destinationClass) {
        return dozer.map(source, destinationClass);
    }

    /**
     * 对象属性值拷贝
     */
    public static void map(Object source, Object destinationObject) {
        dozer.map(source, destinationObject);
    }

    /**
     * 对象集合属性值拷贝
     */
    @SuppressWarnings("rawtypes")
    public static <T> List<T> map(Collection sourceList, Class<T> destinationClass) {
        List<T> destinationList = new ArrayList<T>();
        for (Object sourceObject : sourceList) {
            T destinationObject = dozer.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }
}
