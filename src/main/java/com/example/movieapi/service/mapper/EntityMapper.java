package com.example.movieapi.service.mapper;

/**
 * @author Mahdi Sharifi
 * @since 10/4/22

 * Contract for a generic dto to entity mapper.
 *
 * @param <D> - DTO type parameter.
 * @param <E> - Entity type parameter.
 */

public interface EntityMapper<D, E> {
    E toEntity(D dto);

    D toDto(E entity);
}
