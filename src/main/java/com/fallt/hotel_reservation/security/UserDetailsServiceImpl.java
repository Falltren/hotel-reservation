package com.fallt.hotel_reservation.security;

import com.fallt.hotel_reservation.domain.entity.User;
import com.fallt.hotel_reservation.exception.EntityNotFoundException;
import com.fallt.hotel_reservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("User with name: {0} not found", username)));
        return new AppUserDetails(user);
    }

}
