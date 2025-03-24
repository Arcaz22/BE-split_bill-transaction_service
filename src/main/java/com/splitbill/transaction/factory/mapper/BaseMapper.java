package com.splitbill.transaction.factory.mapper;

import java.util.List;

import org.mapstruct.MappingTarget;

import lombok.NonNull;

public interface BaseMapper<E, D> {
    E toEntity(@NonNull D dto);

    List<E> toEntities(@NonNull List<D> dtos);

    D toDto(@NonNull E entity);

    List<D> toDtos(@NonNull List<E> entities);

    E fromEntity(@NonNull D dto, @MappingTarget E targetEntity);

    List<E> fromEntities(@NonNull List<D> dtos, @MappingTarget List<E> targetEntities);
}
