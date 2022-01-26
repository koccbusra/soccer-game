package com.example.soccergame.response;

import com.example.soccergame.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoResponse {
    private String emailAddress;

    public UserInfoResponse(User user) {
        this.emailAddress = user.getEmailAddress();
    }
}
