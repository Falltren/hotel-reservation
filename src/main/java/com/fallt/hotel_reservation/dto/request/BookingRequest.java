package com.fallt.hotel_reservation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingRequest {

    private LocalDate arrivalDate;

    private LocalDate departureDate;

    private Long hotelId;

    private Long roomId;
}
