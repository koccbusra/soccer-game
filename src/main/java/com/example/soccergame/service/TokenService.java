package com.example.soccergame.service;

import com.example.soccergame.exception.EmailOrPasswordIsWrongException;
import com.example.soccergame.model.User;
import com.example.soccergame.repository.UserRepository;
import com.example.soccergame.request.TokenInfoRequest;
import com.example.soccergame.response.TokenInfoResponse;
import com.google.common.hash.Hashing;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class TokenService {

    private final UserRepository userRepository;

    private TokenService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public TokenInfoResponse getToken(TokenInfoRequest request) {
        User user = userRepository.findByEmailAddress(request.getEmailAddress());
        if(user == null)
            throw new EmailOrPasswordIsWrongException("Error!");
        if(!Hashing.sha256().hashString(request.getPassword(), StandardCharsets.UTF_8).toString().equals(user.getPassword()))
            throw new EmailOrPasswordIsWrongException("Error!");

        return new TokenInfoResponse();
    }

}
