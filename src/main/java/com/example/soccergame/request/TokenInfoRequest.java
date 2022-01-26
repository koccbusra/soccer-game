package com.example.soccergame.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenInfoRequest {
    private String emailAddress;
    private String password;
}
