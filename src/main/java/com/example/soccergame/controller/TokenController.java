package com.example.soccergame.controller;

import com.example.soccergame.request.TokenInfoRequest;
import com.example.soccergame.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/token")
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService){
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody TokenInfoRequest request){
        return new ResponseEntity(tokenService.getToken(request), HttpStatus.CREATED);
    }
}
