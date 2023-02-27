package com.ibrplanner.pedidos.helpers;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DTOUtils {
    /**
     * Converte um objeto de uma classe de domínio para um objeto DTO.
     *
     * @param source      o objeto de origem que será convertido em DTO
     * @param targetClass a classe do DTO
     * @param <S>         o tipo do objeto de origem
     * @param <T>         o tipo do DTO
     * @return o objeto DTO criado a partir do objeto de origem
     * @throws IllegalArgumentException se o parâmetro source for nulo
     */
    public static <S, T> T convertToDTO(S source, Class<T> targetClass) {
        try {
            Objects.requireNonNull(source, "O parâmetro source não pode ser nulo");
            T target = BeanUtils.instantiateClass(targetClass);
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    /**
     * Converte uma lista de objetos de uma classe de domínio para uma lista de objetos DTO.
     *
     * @param objects  a lista de objetos de origem que serão convertidos em DTO
     * @param dtoClass a classe do DTO
     * @param <T>      o tipo da lista de objetos de origem
     * @param <D>      o tipo da lista de DTOs
     * @return a lista de objetos DTO criados a partir da lista de objetos de origem
     * @throws IllegalArgumentException se o parâmetro objects for nulo
     */
    public static <T, D> List<D> toDTOList(List<T> objects, Class<D> dtoClass) {
        Objects.requireNonNull(objects, "A lista de objetos não pode ser nula");

        return objects.stream().map(object -> convertToDTO(object, dtoClass)).collect(Collectors.toList());
    }

    /**
     * Converte uma página de objetos de uma classe de domínio para uma página de objetos DTO.
     *
     * @param page     a página de objetos de origem que serão convertidos em DTO
     * @param dtoClass a classe do DTO
     * @param <T>      o tipo da página de objetos de origem
     * @param <D>      o tipo da página de DTOs
     * @return a página de objetos DTO criados a partir da página de objetos de origem
     * @throws IllegalArgumentException se o parâmetro page for nulo
     */
    public static <T, D> Page<D> toDTOPage(Page<T> page, Class<D> dtoClass) {
        Objects.requireNonNull(page, "A página de objetos não pode ser nula");

        List<D> dtoList = toDTOList(page.getContent(), dtoClass);
        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }
}
