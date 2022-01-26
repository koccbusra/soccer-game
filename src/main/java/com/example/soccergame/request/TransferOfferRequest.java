package com.example.soccergame.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransferOfferRequest {

    private Integer teamId;
    private Integer playerId;

}
