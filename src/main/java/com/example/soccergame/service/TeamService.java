package com.example.soccergame.service;

import com.example.soccergame.enumeration.EnumPlayerPosition;
import com.example.soccergame.exception.TeamNotFoundException;
import com.example.soccergame.generator.TeamNameGenerator;
import com.example.soccergame.model.Team;
import com.example.soccergame.model.User;
import com.example.soccergame.repository.TeamRepository;
import com.example.soccergame.request.TeamInfoRequest;
import com.example.soccergame.response.TeamInfoResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final PlayerService playerService;

    private TeamService(TeamRepository teamRepository, PlayerService playerService){
        this.teamRepository = teamRepository;
        this.playerService = playerService;
    }

    public Team create(User user){
        Team team = new Team();
        team.setTeamName(TeamNameGenerator.getRandomTeamName());
        team.setUser(user);

        for (int i = 0; i < 3; i++) team.getPlayerSet().add(playerService.create(EnumPlayerPosition.GOALKEEPER,team));
        for (int i = 0; i < 6; i++) team.getPlayerSet().add(playerService.create(EnumPlayerPosition.DEFENDER,team));
        for (int i = 0; i < 6; i++) team.getPlayerSet().add(playerService.create(EnumPlayerPosition.MIDFIELDER,team));
        for (int i = 0; i < 5; i++) team.getPlayerSet().add(playerService.create(EnumPlayerPosition.ATTACKER,team));

        return team;
    }

    public TeamInfoResponse create(TeamInfoRequest request){
        Team team = new Team();
        team.setTeamName(request.getTeamName());
        team.setCountry(request.getCountry());
        return new TeamInfoResponse(teamRepository.save(team));
    }

    public TeamInfoResponse update(Integer teamId, TeamInfoRequest request) {
        Optional<Team> team = teamRepository.findById(teamId);
        if(!team.isPresent())
            throw new TeamNotFoundException("Invalid teamId:" + teamId);

        if(request.getTeamName() != null) team.get().setTeamName(request.getTeamName());
        if(request.getCountry() != null) team.get().setCountry(request.getCountry());

        Team updatedTeam = teamRepository.save(team.get());
        return new TeamInfoResponse(updatedTeam);
    }

    public TeamInfoResponse read(Integer teamId) {
        Optional<Team> team = teamRepository.findById(teamId);
        if(!team.isPresent())
            throw new TeamNotFoundException("Invalid teamId:" + teamId);

        return new TeamInfoResponse(team.get());
    }

    public Boolean delete(Integer teamId) {
        Optional<Team> team = teamRepository.findById(teamId);
        if(!team.isPresent())
            throw new TeamNotFoundException("Invalid teamId:" + teamId);

        teamRepository.delete(team.get());
        return true;
    }
}

