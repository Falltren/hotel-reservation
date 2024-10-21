package com.fallt.hotel_reservation.repository;

import com.fallt.hotel_reservation.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
