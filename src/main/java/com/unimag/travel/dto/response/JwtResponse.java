package com.unimag.travel.dto.response;

import com.unimag.travel.entities.ERole;

import java.util.List;

public record JwtResponse(
        String token,
        String type,
        Long idCliente,
        //String email,
        List<String> roles
) {
}
