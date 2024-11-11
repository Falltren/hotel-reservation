package com.fallt.hotel_reservation.repository;

import com.fallt.hotel_reservation.domain.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
