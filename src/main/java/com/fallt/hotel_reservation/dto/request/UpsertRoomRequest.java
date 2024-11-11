package com.fallt.hotel_reservation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpsertRoomRequest {

    @NotBlank(message = "Room name must be not empty")
    private String name;

    @NotBlank(message = "Room description must not be empty")
    private String description;

    @Positive(message = "Room number must be positive")
    private Integer number;

    @Positive(message = "Room price must be positive")
    private Double price;

    @Positive(message = "People count must be positive")
    private Integer maxPeopleCount;

    @Positive(message = "Hotel id must be positive")
    private Long hotelId;

}
