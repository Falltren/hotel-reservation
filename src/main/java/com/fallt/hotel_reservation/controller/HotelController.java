package com.fallt.hotel_reservation.controller;

import com.fallt.hotel_reservation.domain.dto.request.UpsertHotelRequest;
import com.fallt.hotel_reservation.domain.dto.response.HotelListResponse;
import com.fallt.hotel_reservation.domain.dto.response.HotelResponse;
import com.fallt.hotel_reservation.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/{id}")
    public HotelResponse getById(@PathVariable Long id) {
        return hotelService.getById(id);
    }

    @GetMapping
    public HotelListResponse getAllHotels() {
        return hotelService.getAllHotels();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public HotelResponse createHotel(@RequestBody @Valid UpsertHotelRequest request) {
        return hotelService.createHotel(request);
    }

    @PutMapping("/{id}")
    public HotelResponse updateHotel(@PathVariable Long id, @RequestBody UpsertHotelRequest request) {
        return hotelService.updateHotel(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
    }

}
