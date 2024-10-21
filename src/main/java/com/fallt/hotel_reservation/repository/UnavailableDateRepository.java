package com.fallt.hotel_reservation.repository;

import com.fallt.hotel_reservation.entity.UnavailableDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnavailableDateRepository extends JpaRepository<UnavailableDate, Long> {
}
