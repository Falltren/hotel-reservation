package com.fallt.hotel_reservation.service;

import com.fallt.hotel_reservation.domain.entity.RefreshToken;
import com.fallt.hotel_reservation.exception.RefreshTokenException;
import com.fallt.hotel_reservation.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    @Value("${app.security.jwt.refreshTokenExpiration}")
    private Duration refreshTokenExpiration;

    private final RefreshTokenRepository refreshTokenRepository;

    public Optional<RefreshToken> findByRefreshToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Long userId) {
        var refreshToken = RefreshToken.builder()
                .userId(userId)
                .expiryDate(Instant.now().plusMillis(refreshTokenExpiration.toMillis()))
                .token(UUID.randomUUID().toString())
                .build();
        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken checkRefreshToken(RefreshToken refreshToken) {
        if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refreshToken);
            throw new RefreshTokenException(refreshToken.getToken(), "Refresh token was expired. Repeat login action");
        }
        return refreshToken;
    }

    public void deleteByUserId(Long userId) {
        refreshTokenRepository.deleteByUserId(userId);
    }

}
