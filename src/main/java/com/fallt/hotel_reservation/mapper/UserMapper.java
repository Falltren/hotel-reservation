package com.fallt.hotel_reservation.mapper;

import com.fallt.hotel_reservation.dto.request.UpsertUserRequest;
import com.fallt.hotel_reservation.dto.response.UserResponse;
import com.fallt.hotel_reservation.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", expression = "java(Role.valueOf(request.getRole()))")
    User toEntity(UpsertUserRequest request);

    UserResponse toResponse(User user);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UpsertUserRequest request, @MappingTarget User user);

}
