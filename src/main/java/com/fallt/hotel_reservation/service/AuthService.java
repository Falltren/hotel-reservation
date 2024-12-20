package com.fallt.hotel_reservation.service;

import com.fallt.hotel_reservation.domain.dto.request.LoginRequest;
import com.fallt.hotel_reservation.domain.dto.request.RefreshTokenRequest;
import com.fallt.hotel_reservation.domain.dto.response.AuthResponse;
import com.fallt.hotel_reservation.domain.dto.response.RefreshTokenResponse;
import com.fallt.hotel_reservation.domain.entity.RefreshToken;
import com.fallt.hotel_reservation.domain.entity.User;
import com.fallt.hotel_reservation.exception.RefreshTokenException;
import com.fallt.hotel_reservation.repository.UserRepository;
import com.fallt.hotel_reservation.security.AppUserDetails;
import com.fallt.hotel_reservation.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;

    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.createEmptyContext().setAuthentication(authentication);
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        return AuthResponse.builder()
                .token(jwtUtils.generateJwtToken(userDetails))
                .refreshToken(refreshToken.getToken())
                .username(userDetails.getUsername())
                .build();
    }

    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        return refreshTokenService.findByRefreshToken(requestRefreshToken)
                .map(refreshTokenService::checkRefreshToken)
                .map(RefreshToken::getUserId)
                .map(userId -> {
                    User tokenOwner = userRepository.findById(userId).orElseThrow(() ->
                            new RefreshTokenException("Exception trying to get token for userId: " + userId));
                    String token = jwtUtils.generateJwtTokenFromUsername(tokenOwner.getName());
                    return new RefreshTokenResponse(token, request.getRefreshToken());
                }).orElseThrow(() -> new RefreshTokenException(requestRefreshToken, "Refresh token not found"));
    }

    public void logout() {
        var currentPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentPrincipal instanceof AppUserDetails userDetails) {
            Long userId = userDetails.getId();
            refreshTokenService.deleteByUserId(userId);
        }
    }

}
