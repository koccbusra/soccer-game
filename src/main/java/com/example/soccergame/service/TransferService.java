package com.example.soccergame.service;

import com.example.soccergame.exception.PlayerNotFoundException;
import com.example.soccergame.exception.TeamNotFoundException;
import com.example.soccergame.exception.UnacceptableTransferException;
import com.example.soccergame.model.Player;
import com.example.soccergame.model.Team;
import com.example.soccergame.model.Transfer;
import com.example.soccergame.repository.PlayerRepository;
import com.example.soccergame.repository.TeamRepository;
import com.example.soccergame.repository.TransferRepository;
import com.example.soccergame.request.TransferOfferRequest;
import com.example.soccergame.request.TransferListRequest;
import com.example.soccergame.response.TransferListResponse;
import com.example.soccergame.response.TransferOfferResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransferService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final TransferRepository transferRepository;

    public TransferService(PlayerRepository playerRepository, TeamRepository teamRepository, TransferRepository transferRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.transferRepository = transferRepository;
    }

    public  List<TransferListResponse> getTransferList(TransferListRequest transferRequest) {
        return playerRepository.findAllInTransferList().stream()
                .filter(player -> transferRequest.getCountry() == null ? true: player.getCountry().equals(transferRequest.getCountry()))
                .filter(player -> transferRequest.getFirstName() == null ? true: player.getFirstName().equals(transferRequest.getFirstName()))
                .filter(player -> transferRequest.getTeamName() == null ? true: player.getTeam().getTeamName().equals(transferRequest.getTeamName()))
                .filter(player -> transferRequest.getAge() == null ? true: player.getAge() == transferRequest.getAge())
                .map(player -> new TransferListResponse(player))
                .collect(Collectors.toList());
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public TransferOfferResponse offerToPLayer(TransferOfferRequest request) {

        Optional<Player> player = playerRepository.findById(request.getPlayerId());
        if(!player.isPresent())
            throw new PlayerNotFoundException("Invalid playerId: " + request.getPlayerId());

        Optional<Team> teamBuyer = teamRepository.findById(request.getTeamId());
        if(!teamBuyer.isPresent())
            throw new TeamNotFoundException("Invalid teamId: " + request.getTeamId());

        if(teamBuyer.get().getId() == player.get().getTeam().getId())
            throw new UnacceptableTransferException("The team[id:" + teamBuyer.get().getId() + "] already owns the player[id:" + player.get().getId() + "].");

        if(!player.get().getIsInTransferList())
            throw new UnacceptableTransferException("The player[id:" + player.get().getId() + "] isn't in transfer list.");

        if(teamBuyer.get().getBudgetInUSD() < player.get().getMarketValueInUSD())
            throw new UnacceptableTransferException("The team[id:" + teamBuyer.get().getId() + "] transfer budget has only " + teamBuyer.get().getBudgetInUSD() + "USD. " +
                    "The player[id:" + player.get().getId() + "] price is " + player.get().getMarketValueInUSD() + "USD.");


        Optional<Team> teamSeller = teamRepository.findById(player.get().getTeam().getId());
        teamSeller.get().setBudgetInUSD(teamSeller.get().getBudgetInUSD() + player.get().getMarketValueInUSD());
        teamRepository.save(teamSeller.get());

        teamBuyer.get().setBudgetInUSD(teamBuyer.get().getBudgetInUSD() - player.get().getMarketValueInUSD());
        Team updatedBuyerTeam = teamRepository.save(teamBuyer.get());

        Integer transferPriceInUSD = player.get().getMarketValueInUSD();
        player.get().setMarketValueInUSD((int) (player.get().getMarketValueInUSD() * (new Random().nextInt(10) + 11) * 0.1));
        player.get().setTeam(updatedBuyerTeam);
        player.get().setIsInTransferList(false);
        playerRepository.save(player.get());

        Transfer transfer = new Transfer();
        transfer.setBuyerTeamId(teamBuyer.get().getId());
        transfer.setSellerTeamId(teamSeller.get().getId());
        transfer.setPlayerId(player.get().getId());
        transfer.setPriceInUSD(transferPriceInUSD);
        transferRepository.save(transfer);

        return new TransferOfferResponse(updatedBuyerTeam.getBudgetInUSD(), player.get().getFirstName(),player.get().getLastName(),transferPriceInUSD);
    }
}
