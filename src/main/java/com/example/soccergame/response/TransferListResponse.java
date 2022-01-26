package com.example.soccergame.response;

import com.example.soccergame.model.Player;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransferListResponse {
    private String firstName;
    private String lastName;
    private Integer age;
    private String position;
    private String country;
    private Integer marketValueInUSD;

    public TransferListResponse(Player player) {
        this.firstName = player.getFirstName();
        this.lastName = player.getLastName();
        this.age = player.getAge();
        this.position = player.getPosition();
        this.country = player.getCountry();
        this.marketValueInUSD = player.getMarketValueInUSD();
    }
}
