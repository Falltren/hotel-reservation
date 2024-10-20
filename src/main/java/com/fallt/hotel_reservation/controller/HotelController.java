package com.fallt.hotel_reservation.controller;

import com.fallt.hotel_reservation.dto.HotelListResponse;
import com.fallt.hotel_reservation.dto.HotelResponse;
import com.fallt.hotel_reservation.dto.UpsertHotelRequest;
import com.fallt.hotel_reservation.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public void deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
    }
}
