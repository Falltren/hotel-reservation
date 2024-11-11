package com.fallt.hotel_reservation.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponse {

    private String city;

    @JsonProperty("hotel_name")
    private String hotelName;

    @JsonProperty("room_number")
    private Integer roomNumber;

    @JsonProperty("arrival_date")
    private LocalDate arrivalDate;

    @JsonProperty("departure_date")
    private LocalDate departureDate;

}
