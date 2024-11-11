package com.fallt.hotel_reservation.mapper;

import com.fallt.hotel_reservation.dto.request.UpsertRoomRequest;
import com.fallt.hotel_reservation.dto.response.RoomResponse;
import com.fallt.hotel_reservation.entity.Room;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    RoomMapper INSTANCE = getMapper(RoomMapper.class);

    RoomResponse toResponse(Room room);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "hotel", ignore = true)
    Room toEntity(UpsertRoomRequest upsertRoomRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "hotel", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRoomFromDto(UpsertRoomRequest request, @MappingTarget Room room);

}
