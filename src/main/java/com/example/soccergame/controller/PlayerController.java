package com.example.soccergame.controller;

import com.example.soccergame.request.PlayerInfoRequest;
import com.example.soccergame.response.PlayerInfoResponse;
import com.example.soccergame.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<PlayerInfoResponse> create(@RequestBody PlayerInfoRequest request){
        return new ResponseEntity(playerService.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/{player-id}")
    public ResponseEntity<PlayerInfoResponse> read(@PathVariable(name="player-id") Integer playerId){
        return new ResponseEntity(playerService.read(playerId), HttpStatus.OK);
    }

    @PutMapping("/{player-id}")
    public ResponseEntity<PlayerInfoResponse> update(@PathVariable(name="player-id") Integer playerId, @RequestBody PlayerInfoRequest request){
        return new ResponseEntity(playerService.update(playerId, request), HttpStatus.OK);
    }

    @DeleteMapping("/{player-id}")
    public ResponseEntity<Boolean> delete(@PathVariable(name="player-id") Integer playerId){
        return new ResponseEntity(playerService.delete(playerId), HttpStatus.OK);
    }
}
