package com.fallt.hotel_reservation.service;

import com.fallt.hotel_reservation.dto.request.UpsertRoomRequest;
import com.fallt.hotel_reservation.dto.response.RoomResponse;
import com.fallt.hotel_reservation.entity.Hotel;
import com.fallt.hotel_reservation.entity.Room;
import com.fallt.hotel_reservation.exception.EntityNotFoundException;
import com.fallt.hotel_reservation.mapper.RoomMapper;
import com.fallt.hotel_reservation.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final HotelService hotelService;

    public RoomResponse getRoomById(Long id) {
        Room room = getRoom(id);
        return RoomMapper.INSTANCE.toResponse(room);
    }

    public RoomResponse createRoom(UpsertRoomRequest request) {
        Hotel hotel = hotelService.getHotelById(request.getHotelId());
        Room room = RoomMapper.INSTANCE.toEntity(request);
        room.setHotel(hotel);
        return RoomMapper.INSTANCE.toResponse(roomRepository.save(room));
    }

    public RoomResponse updateRoom(Long id, UpsertRoomRequest request) {
        Room room = getRoom(id);
        RoomMapper.INSTANCE.updateRoomFromDto(request, room);
        return RoomMapper.INSTANCE.toResponse(roomRepository.save(room));
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    public Room getRoom(Long id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isEmpty()) {
            throw new EntityNotFoundException(MessageFormat.format("Room with ID: {0} not found", id));
        }
        return optionalRoom.get();
    }

}
