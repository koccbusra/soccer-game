package com.example.soccergame.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;

@Setter
@Getter
@Entity
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer sellerTeamId;
    private Integer buyerTeamId;
    private Integer playerId;

    @CreationTimestamp
    private Date transferDate;

    private Integer priceInUSD;
}
