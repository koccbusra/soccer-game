package com.example.soccergame.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserInfoRequest {
    private String emailAddress;
    private String password;
}
