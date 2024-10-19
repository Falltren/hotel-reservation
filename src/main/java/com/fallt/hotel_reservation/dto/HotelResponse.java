package com.fallt.hotel_reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelResponse {

    private String name;

    private String title;

    private String city;

    private String address;

    private Integer distance;

    private Float ranking;

    private Integer count;
}
