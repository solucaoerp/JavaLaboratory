package com.ibrplanner.pedidos.helpers;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class DTOUtils {
    public static <T, D> D toDTO(T object, Class<D> dtoClass) {
        D dto = BeanUtils.instantiateClass(dtoClass);
        BeanUtils.copyProperties(object, dto);
        return dto;
    }

    public static <T, D> List<D> toDTOList(List<T> objects, Class<D> dtoClass) {
        return objects.stream().map(object -> toDTO(object, dtoClass)).collect(Collectors.toList());
    }

    public static <T, D> Page<D> toDTOPage(Page<T> page, Class<D> dtoClass) {
        List<D> dtoList = toDTOList(page.getContent(), dtoClass);
        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }
}
