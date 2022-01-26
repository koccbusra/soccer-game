package com.example.soccergame.response;

import com.example.soccergame.model.Player;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlayerInfoResponse {
    private Integer playerId;
    private String firstName;
    private String lastName;
    private String country;
    private Integer age;

    public PlayerInfoResponse(Player player) {
        this.playerId = player.getId();
        this.firstName = player.getFirstName();
        this.lastName = player.getLastName();
        this.country = player.getCountry();
        this.age = player.getAge();
    }
}
