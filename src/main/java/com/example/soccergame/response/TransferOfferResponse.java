package com.example.soccergame.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransferOfferResponse {
    private Integer teamBudgetInDollar;
    private String playerFirstName;
    private String playerLastName;
    private Integer transferBudgetInDollar;
}
