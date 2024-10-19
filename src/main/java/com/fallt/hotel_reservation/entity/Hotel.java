package com.fallt.hotel_reservation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    private Long id;

    private String name;

    private String title;

    private String city;

    private String address;

    private Integer distance;

    private Float ranking;

    private Integer count;

}
