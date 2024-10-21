package com.fallt.hotel_reservation.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Integer number;

    private Double price;

    @Column(name = "max_people_count")
    private Integer maxPeopleCount;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
