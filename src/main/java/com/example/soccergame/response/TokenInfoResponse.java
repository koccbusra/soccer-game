package com.example.soccergame.response;

import lombok.Getter;
import lombok.Setter;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class TokenInfoResponse {
    private String token;

    public TokenInfoResponse() {
        this.token = new StringBuilder().append(Instant.now().toEpochMilli()).append("-").append(UUID.randomUUID()).toString();
    }
}
