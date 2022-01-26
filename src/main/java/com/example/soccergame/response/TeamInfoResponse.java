package com.example.soccergame.response;

import com.example.soccergame.model.Team;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamInfoResponse {
    private Integer teamId;
    private String teamName;
    private String country;

    public TeamInfoResponse(Team team) {
        this.teamId = team.getId();
        this.teamName = team.getTeamName();
        this.country = team.getCountry();
    }
}
