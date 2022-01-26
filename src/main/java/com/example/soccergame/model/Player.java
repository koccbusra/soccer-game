package com.example.soccergame.model;

import com.example.soccergame.enumeration.EnumPlayerPosition;
import com.example.soccergame.generator.CountryNameGenerator;
import com.example.soccergame.generator.PlayerNameGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Random;

@Setter
@Getter
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private Integer age = new Random().nextInt(23) + 18;
    private String position;
    private String country;
    private Integer marketValueInUSD = 1000000;
    private Boolean isInTransferList = false;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;
}
