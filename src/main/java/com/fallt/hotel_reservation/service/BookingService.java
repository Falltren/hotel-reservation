package com.fallt.hotel_reservation.service;

import com.fallt.hotel_reservation.dto.request.BookingRequest;
import com.fallt.hotel_reservation.dto.response.BookingResponse;
import com.fallt.hotel_reservation.entity.Booking;
import com.fallt.hotel_reservation.entity.Room;
import com.fallt.hotel_reservation.entity.User;
import com.fallt.hotel_reservation.mapper.BookingMapper;
import com.fallt.hotel_reservation.repository.BookingRepository;
import com.fallt.hotel_reservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    private final UnavailableDateService unavailableDateService;

    private final RoomService roomService;

    private final UserRepository userRepository;

    public List<BookingResponse> getAllBookings() {
        return BookingMapper.INSTANCE.toListResponse(bookingRepository.findAll());
    }

    @Transactional
    public BookingResponse createBooking(BookingRequest request) {
        Booking booking = BookingMapper.INSTANCE.toEntity(request);
        Room room = roomService.getRoom(request.getRoomId());
        User user = userRepository.getById(1L); // будет заменено на получение пользователя из контекста безопасности при добавлении Spring Security
        booking.setUser(user);
        booking.setRoom(room);
        unavailableDateService.addUnavailableDate(booking);
        return BookingMapper.INSTANCE.toResponse(bookingRepository.save(booking));
    }
}
