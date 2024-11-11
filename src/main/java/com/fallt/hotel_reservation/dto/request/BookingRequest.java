package com.fallt.hotel_reservation.dto.request;

import jakarta.validation.constraints.AssertTrue;
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

    @AssertTrue(message = "Даты должны быть указаны. Дата выезда должна быть позже даты заезда")
    public boolean isCorrectPeriod(){
        return arrivalDate != null && departureDate != null && departureDate.isAfter(arrivalDate);
    }

}
