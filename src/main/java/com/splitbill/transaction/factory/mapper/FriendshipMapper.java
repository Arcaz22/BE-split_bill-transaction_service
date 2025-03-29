package com.splitbill.transaction.factory.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.splitbill.transaction.factory.dto.friendship.FriendshipCreateDTO;
import com.splitbill.transaction.factory.dto.friendship.FriendshipDTO;
import com.splitbill.transaction.factory.dto.friendship.FriendshipUpdateDTO;
import com.splitbill.transaction.factory.entity.Friendship;

@Mapper(componentModel = "spring", config = BaseMapperConfig.class)
public interface FriendshipMapper {

    Friendship toEntity(FriendshipDTO dto);

    FriendshipDTO toDto(Friendship entity);

    List<FriendshipDTO> toDtos(List<Friendship> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "requesterId", ignore = true)
    @Mapping(target = "status", constant = "PENDING")
    Friendship createDtoToEntity(FriendshipCreateDTO dto);

    void updateDtoToEntity(FriendshipUpdateDTO dto, @MappingTarget Friendship friendship);
}
