package com.fallt.hotel_reservation.repository;

import com.fallt.hotel_reservation.domain.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
