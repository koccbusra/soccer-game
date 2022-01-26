package com.example.soccergame.controller;

import com.example.soccergame.request.TeamInfoRequest;
import com.example.soccergame.response.TeamInfoResponse;
import com.example.soccergame.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/team")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService){
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<TeamInfoResponse> create(@RequestBody TeamInfoRequest request){
        return new ResponseEntity(teamService.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/{team-id}")
    public ResponseEntity<TeamInfoResponse> read(@PathVariable(name="team-id") Integer teamId){
        return new ResponseEntity(teamService.read(teamId), HttpStatus.OK);
    }

    @PutMapping("/{team-id}")
    public ResponseEntity<TeamInfoResponse> update(@PathVariable(name="team-id") Integer teamId, @RequestBody TeamInfoRequest request){
        return new ResponseEntity(teamService.update(teamId, request), HttpStatus.OK);
    }

    @DeleteMapping("/{team-id}")
    public ResponseEntity<Boolean> delete(@PathVariable(name="team-id") Integer teamId){
        return new ResponseEntity(teamService.delete(teamId), HttpStatus.OK);
    }
}