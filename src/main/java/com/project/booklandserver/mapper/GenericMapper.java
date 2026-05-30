package com.project.booklandserver.mapper;


public interface GenericMapper<D, E> {

    E toEntity(D dto);

    D toDto(E entity);

}

