package com.easy.finance.utils.mapper;

import java.util.List;

public interface Mapper<M, E, D> {

    M entityToModel(E entity);
    E modelToEntity(M model);
    D modelToDto(M model);
    M dtoToModel(D dto);
    List<M> entitiesToModels(List<E> entities);
    List<D> modelsToDtos(List<M> models);

}
