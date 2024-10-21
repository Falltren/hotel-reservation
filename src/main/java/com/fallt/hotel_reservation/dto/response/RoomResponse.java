package com.fallt.hotel_reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomResponse {

    private String name;

    private String description;

    private Integer number;

    private Double price;

    private Integer maxPeopleCount;
}
