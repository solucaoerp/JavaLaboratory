package com.ibrplanner.logistica.services.converterUtils;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe utilitária para conversão de objetos.
 */
public class ConverterUtils {

    private static final ModelMapper modelMapper = new ModelMapper();

    /**
     * Método genérico que realiza a conversão de um objeto de origem para um objeto de destino.
     *
     * @param source          o objeto de origem que será convertido.
     * @param destinationType a classe do objeto de destino para o qual o objeto de origem será convertido.
     * @param <T>             o tipo do objeto de origem.
     * @param <U>             o tipo do objeto de destino.
     * @return o objeto de destino convertido.
     */
    public static <T, U> U toModel(T source, Class<U> destinationType) {
        return modelMapper.map(source, destinationType);
    }

    /**
     * Método genérico que realiza a conversão de uma lista de objetos de origem para uma lista de objetos de destino.
     *
     * @param entities    a lista de objetos de origem que será convertida.
     * @param modelClass  a classe do objeto de destino para o qual os objetos de origem serão convertidos.
     * @param modelMapper o objeto do ModelMapper que será utilizado para realizar a conversão.
     * @param <T>         o tipo dos objetos de origem.
     * @param <R>         o tipo dos objetos de destino.
     * @return a lista de objetos de destino convertida.
     */
    public static <T, R> List<R> toListModel(List<T> entities, Class<R> modelClass, ModelMapper modelMapper) {
        return entities.stream().map(entity -> modelMapper.map(entity, modelClass)).collect(Collectors.toList());
    }

}
