package com.fallt.hotel_reservation.dto.request;

import com.fallt.hotel_reservation.entity.Role;
import com.fallt.hotel_reservation.validation.RoleValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpsertUserRequest {

    private String name;
    private String password;
    private String email;

    @RoleValidation(enumClass = Role.class)
    private String role;

}
