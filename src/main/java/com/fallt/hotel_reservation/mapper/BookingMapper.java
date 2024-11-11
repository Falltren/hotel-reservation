package com.fallt.hotel_reservation.mapper;

import com.fallt.hotel_reservation.dto.request.BookingRequest;
import com.fallt.hotel_reservation.dto.response.BookingResponse;
import com.fallt.hotel_reservation.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    BookingMapper INSTANCE = getMapper(BookingMapper.class);

    @Mapping(target = "city", source = "room.hotel.city")
    @Mapping(target = "hotelName", source = "room.hotel.name")
    @Mapping(target = "roomNumber", source = "room.number")
    BookingResponse toResponse(Booking booking);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "room", ignore = true)
    @Mapping(target = "user", ignore = true)
    Booking toEntity(BookingRequest request);

    List<BookingResponse> toListResponse(List<Booking> bookingList);

}
