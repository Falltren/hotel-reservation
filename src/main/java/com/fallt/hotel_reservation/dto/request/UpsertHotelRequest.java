package com.fallt.hotel_reservation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpsertHotelRequest {

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 3, max = 30, message = "The name of the hotel should be between 3 and 30 characters")
    private String name;

    @NotEmpty(message = "Title must not be empty")
    private String title;

    @NotEmpty(message = "City must not be empty")
    private String city;

    @NotEmpty(message = "Hotel address must not be empty")
    private String address;

    @Positive(message = "The distance to the hotel should be positive")
    private Integer distance;
}
