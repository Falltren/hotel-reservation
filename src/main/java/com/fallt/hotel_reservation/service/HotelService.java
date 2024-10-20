package com.fallt.hotel_reservation.service;

import com.fallt.hotel_reservation.dto.HotelListResponse;
import com.fallt.hotel_reservation.dto.HotelResponse;
import com.fallt.hotel_reservation.dto.UpsertHotelRequest;
import com.fallt.hotel_reservation.entity.Hotel;
import com.fallt.hotel_reservation.exception.EntityNotFoundException;
import com.fallt.hotel_reservation.mapper.HotelMapper;
import com.fallt.hotel_reservation.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;


    public HotelResponse getById(Long id) {
        Hotel hotel = getHotelById(id);
        return HotelMapper.INSTANCE.toResponse(hotel);
    }

    public HotelListResponse getAllHotels() {
        return HotelMapper.INSTANCE.hotelListToHotelListResponse(hotelRepository.findAll());
    }

    public HotelResponse createHotel(UpsertHotelRequest request) {
        Hotel hotel = HotelMapper.INSTANCE.toEntity(request);
        return HotelMapper.INSTANCE.toResponse(hotelRepository.save(hotel));
    }

    public HotelResponse updateHotel(Long id, UpsertHotelRequest request) {
        Hotel hotel = getHotelById(id);
        HotelMapper.INSTANCE.updateHotelFromDto(request, hotel);
        return HotelMapper.INSTANCE.toResponse(hotelRepository.save(hotel));
    }

    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }

    public Hotel getHotelById(Long id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isEmpty()) {
            throw new EntityNotFoundException(MessageFormat.format("Hotel with ID: {0} not found", id));
        }
        return optionalHotel.get();
    }
}
