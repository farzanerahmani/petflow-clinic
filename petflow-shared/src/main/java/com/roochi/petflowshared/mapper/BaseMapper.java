package com.roochi.petflowshared.mapper;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 6/30/2026
 */
public interface BaseMapper<E, D> {

    D toDto(E entity);

    E toEntity(D dto);

    List<D> toDto(List<E> entities);

    List<E> toEntity(List<D> dtos);
}
