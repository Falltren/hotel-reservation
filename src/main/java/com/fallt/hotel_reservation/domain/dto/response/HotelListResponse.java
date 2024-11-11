package com.fallt.hotel_reservation.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelListResponse {

    @Builder.Default
    private List<HotelResponse> hotels = new ArrayList<>();

}
