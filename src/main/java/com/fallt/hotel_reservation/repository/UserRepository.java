package com.fallt.hotel_reservation.repository;

import com.fallt.hotel_reservation.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

    boolean existsByName(String name);

    boolean existsByEmail(String email);

}
