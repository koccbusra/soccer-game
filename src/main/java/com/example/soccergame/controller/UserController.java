package com.example.soccergame.controller;

import com.example.soccergame.model.User;
import com.example.soccergame.request.UserInfoRequest;
import com.example.soccergame.response.UserInfoResponse;
import com.example.soccergame.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserInfoRequest request){
        return new ResponseEntity(userService.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<UserInfoResponse> read(@PathVariable(name="user-id") Integer userId){
        return new ResponseEntity(userService.read(userId), HttpStatus.OK);
    }

    @PutMapping("/{user-id}")
    public ResponseEntity<UserInfoResponse> update(@PathVariable(name="user-id") Integer userId, @RequestBody UserInfoRequest request){
        return new ResponseEntity(userService.update(userId, request), HttpStatus.OK);
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<Boolean> delete(@PathVariable(name="user-id") Integer userId){
        return new ResponseEntity(userService.delete(userId), HttpStatus.OK);
    }
}
