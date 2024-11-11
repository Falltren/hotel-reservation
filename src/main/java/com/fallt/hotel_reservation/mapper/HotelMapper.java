package com.fallt.hotel_reservation.mapper;

import com.fallt.hotel_reservation.dto.response.HotelListResponse;
import com.fallt.hotel_reservation.dto.response.HotelResponse;
import com.fallt.hotel_reservation.dto.request.UpsertHotelRequest;
import com.fallt.hotel_reservation.entity.Hotel;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    HotelMapper INSTANCE = getMapper(HotelMapper.class);

    HotelResponse toResponse(Hotel hotel);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ranking", ignore = true)
    @Mapping(target = "count", ignore = true)
    Hotel toEntity(UpsertHotelRequest request);

    @Mapping(target = "hotels", source = "hotels")
    List<HotelResponse> toListResponse(List<Hotel> hotels);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ranking", ignore = true)
    @Mapping(target = "count", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateHotelFromDto(UpsertHotelRequest request, @MappingTarget Hotel hotel);

    default HotelListResponse hotelListToHotelListResponse(List<Hotel> hotels) {
        HotelListResponse response = new HotelListResponse();
        response.setHotels(toListResponse(hotels));
        return response;
    }

}
