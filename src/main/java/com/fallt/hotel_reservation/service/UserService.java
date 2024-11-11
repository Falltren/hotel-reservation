package com.fallt.hotel_reservation.service;

import com.fallt.hotel_reservation.domain.dto.request.UpsertUserRequest;
import com.fallt.hotel_reservation.domain.dto.response.UserResponse;
import com.fallt.hotel_reservation.domain.entity.User;
import com.fallt.hotel_reservation.exception.AlreadyExistException;
import com.fallt.hotel_reservation.exception.EntityNotFoundException;
import com.fallt.hotel_reservation.mapper.UserMapper;
import com.fallt.hotel_reservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse getUserById(Long id) {
        User user = getUser(id);
        return UserMapper.INSTANCE.toResponse(user);
    }

    public UserResponse createUser(UpsertUserRequest request) {
        if (userRepository.existsByName(request.getName())) {
            throw new AlreadyExistException(MessageFormat.format("User with name: {0} already exists", request.getName()));
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AlreadyExistException(MessageFormat.format("User with email: {0} already exists", request.getEmail()));
        }
        User user = UserMapper.INSTANCE.toEntity(request);
        return UserMapper.INSTANCE.toResponse(userRepository.save(user));
    }

    public UserResponse updateUser(Long id, UpsertUserRequest request) {
        if (request.getName() != null && userRepository.existsByName(request.getName())) {
            throw new AlreadyExistException(MessageFormat.format("User with name: {0} already exists", request.getName()));
        }
        if (request.getEmail() != null && userRepository.existsByEmail(request.getEmail())) {
            throw new AlreadyExistException(MessageFormat.format("User with email: {0} already exists", request.getEmail()));
        }
        User user = getUser(id);
        UserMapper.INSTANCE.updateUserFromDto(request, user);
        return UserMapper.INSTANCE.toResponse(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private User getUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new EntityNotFoundException(MessageFormat.format("User with ID: {0} not found", id));
        }
        return optionalUser.get();
    }

}
