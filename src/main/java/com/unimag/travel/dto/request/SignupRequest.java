package com.unimag.travel.dto.request;

import java.util.Set;

public record SignupRequest(
        String correoElectronico,
        String password
){
}
