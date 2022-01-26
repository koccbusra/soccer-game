package com.example.soccergame.service;

import com.example.soccergame.enumeration.EnumPlayerPosition;
import com.example.soccergame.exception.PlayerNotFoundException;
import com.example.soccergame.generator.CountryNameGenerator;
import com.example.soccergame.generator.PlayerNameGenerator;
import com.example.soccergame.model.Player;
import com.example.soccergame.model.Team;
import com.example.soccergame.repository.PlayerRepository;
import com.example.soccergame.request.PlayerInfoRequest;
import com.example.soccergame.response.PlayerInfoResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    private PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public Player create(EnumPlayerPosition position, Team team){
        Player player = new Player();
        player.setFirstName(PlayerNameGenerator.getRandomPlayerFirstName());
        player.setLastName(PlayerNameGenerator.getRandomPlayerLastName());
        player.setCountry(CountryNameGenerator.getRandomCountryName());
        player.setPosition(position.toString());
        player.setTeam(team);
        return player;
    }

    public PlayerInfoResponse create(PlayerInfoRequest request){
        Player player = new Player();
        player.setFirstName(request.getFirstName());
        player.setLastName(request.getLastName());
        player.setCountry(request.getCountry());
        player.setPosition(request.getPosition().toString());
        player.setIsInTransferList(request.getIsInTransferList());
        return new PlayerInfoResponse(playerRepository.save(player));
    }

    public PlayerInfoResponse update(Integer playerId, PlayerInfoRequest request) {
        Optional<Player> player = playerRepository.findById(playerId);
        if(!player.isPresent())
            throw new PlayerNotFoundException("Invalid playerId:" + playerId);

        if(request.getFirstName() != null) player.get().setFirstName(request.getFirstName());
        if(request.getLastName() != null) player.get().setLastName(request.getLastName());
        if(request.getCountry() != null) player.get().setCountry(request.getCountry());
        if(request.getPosition() != null) player.get().setPosition(request.getPosition().toString());
        if(request.getIsInTransferList() != null) player.get().setIsInTransferList(request.getIsInTransferList());

        Player updatedPlayer = playerRepository.save(player.get());
        return new PlayerInfoResponse(updatedPlayer);
    }

    public PlayerInfoResponse read(Integer playerId) {
        Optional<Player> player = playerRepository.findById(playerId);
        if(!player.isPresent())
            throw new PlayerNotFoundException("Invalid playerId:" + playerId);

        return new PlayerInfoResponse(player.get());
    }

    public Boolean delete(Integer playerId) {
        Optional<Player> player = playerRepository.findById(playerId);
        if(!player.isPresent())
            throw new PlayerNotFoundException("Invalid playerId:" + playerId);

        playerRepository.delete(player.get());
        return true;
    }
}
