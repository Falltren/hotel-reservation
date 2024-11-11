package com.fallt.hotel_reservation.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("max_people_count")
    private Integer maxPeopleCount;

}
