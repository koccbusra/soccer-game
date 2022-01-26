package com.example.soccergame.model;

import com.example.soccergame.generator.CountryNameGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String teamName;
    private Integer budgetInUSD = 5000000;
    private String country = CountryNameGenerator.getRandomCountryName();

    @OneToMany(mappedBy = "team" /*, orphanRemoval = true*/, cascade = CascadeType.ALL)
    private Set<Player> playerSet = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

}
