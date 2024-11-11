package com.fallt.hotel_reservation.service;

import com.fallt.hotel_reservation.entity.Booking;
import com.fallt.hotel_reservation.entity.UnavailableDate;
import com.fallt.hotel_reservation.repository.UnavailableDateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UnavailableDateService {

    private final UnavailableDateRepository unavailableDateRepository;

    public void addUnavailableDate(Booking booking) {
        List<UnavailableDate> listDates = new ArrayList<>();
        LocalDate current = booking.getArrivalDate();
        while (!current.isAfter(booking.getDepartureDate())) {
            UnavailableDate unavailableDate = new UnavailableDate();
            unavailableDate.setRoom(booking.getRoom());
            unavailableDate.setReserved(current);
            listDates.add(unavailableDate);
            current = current.plusDays(1);
        }
        unavailableDateRepository.saveAll(listDates);
    }

}
