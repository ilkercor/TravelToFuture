package com.amadeusproject.TravelToFuture.Service.JWT;

import org.springframework.security.core.Authentication;

import java.util.Date;

public class JwtService {

    private final int JWT_EXPIRATION = 70000;


    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + JWT_EXPIRATION);

        return "x";

    }
}
