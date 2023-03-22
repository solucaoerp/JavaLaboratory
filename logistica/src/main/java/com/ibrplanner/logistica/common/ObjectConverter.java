package com.ibrplanner.logistica.common;

import com.ibrplanner.logistica.dtos.EntregaDTO;
import com.ibrplanner.logistica.entities.Entrega;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe utilitária para conversão de objetos.
 */
@Component
public class ObjectConverter {

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
     * Converte uma lista de entidades para uma lista de modelos usando o ModelMapper.
     *
     * @param entities   a lista de entidades a ser convertida
     * @param modelClass a classe do modelo de destino
     * @return a lista de modelos convertida
     */
    public static <T, R> List<R> toListModel(List<T> entities, Class<R> modelClass) {
        return entities.stream().map(entity -> modelMapper.map(entity, modelClass)).collect(Collectors.toList());
    }

    /* Model para DTO (Test) */
    public EntregaDTO toModel(Entrega entrega) {
        return modelMapper.map(entrega, EntregaDTO.class);
    }
}
