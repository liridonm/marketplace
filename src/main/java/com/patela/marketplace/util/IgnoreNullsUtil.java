package com.patela.marketplace.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class IgnoreNullsUtil {

    public void myCopyProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    private String[] getNullPropertyNames(Object source) {

        Set<String> emptyNames = new HashSet<String>();
        BeanWrapper src = new BeanWrapperImpl(source);

        List<PropertyDescriptor> propertyDescriptors = Arrays.asList(src.getPropertyDescriptors());

        emptyNames = propertyDescriptors.stream()
                .map(propertyDescriptor -> {
                    Object srcValue = src.getPropertyValue(propertyDescriptor.getName());
                    if (srcValue == null) {
                        return propertyDescriptor.getName();
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }


}
