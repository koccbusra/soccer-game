package com.example.soccergame;

import com.example.soccergame.exception.UnacceptableTransferException;
import com.example.soccergame.model.Player;
import com.example.soccergame.model.Team;
import com.example.soccergame.repository.PlayerRepository;
import com.example.soccergame.repository.TeamRepository;
import com.example.soccergame.request.PlayerInfoRequest;
import com.example.soccergame.request.TransferOfferRequest;
import com.example.soccergame.response.PlayerInfoResponse;
import com.example.soccergame.response.TransferOfferResponse;
import com.example.soccergame.service.TransferService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransferServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TransferService transferService;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void throwUnacceptableTransferException_whenTransferServiceOfferToPlayerMethodCalled_withOwnPlayer() {
        Team persistedTeam = new Team();
        persistedTeam.setTeamName("Tester Team");
        persistedTeam.setId(1);
        when(teamRepository.findById(any(Integer.class))).thenReturn(Optional.of(persistedTeam));

        Player persistedPlayer = new Player();
        persistedPlayer.setFirstName("Tester Name");
        persistedPlayer.setId(23);
        persistedPlayer.setTeam(persistedTeam);
        when(playerRepository.findById(any(Integer.class))).thenReturn(Optional.of(persistedPlayer));

        TransferOfferRequest request = new TransferOfferRequest();
        request.setPlayerId(persistedPlayer.getId());
        request.setTeamId(persistedTeam.getId());

        String message = Assert.assertThrows(UnacceptableTransferException.class, () ->
        {
            TransferOfferResponse response = transferService.offerToPLayer(request);
        }).getMessage();
        Assert.assertEquals("The team[id:1] already owns the player[id:23].",message);
    }

    @Test
    public void throwUnacceptableTransferException_whenTransferServiceOfferToPlayerMethodCalled_withPlayerNotInTransferList() {
        Team persistedTeam = new Team();
        persistedTeam.setTeamName("Tester Team");
        persistedTeam.setId(1);
        when(teamRepository.findById(any(Integer.class))).thenReturn(Optional.of(persistedTeam));

        Team ownerTeam = new Team();
        ownerTeam.setTeamName("Owner Team");
        ownerTeam.setId(11);

        Player persistedPlayer = new Player();
        persistedPlayer.setFirstName("Tester Name");
        persistedPlayer.setId(23);
        persistedPlayer.setTeam(ownerTeam);
        persistedPlayer.setIsInTransferList(false);
        when(playerRepository.findById(any(Integer.class))).thenReturn(Optional.of(persistedPlayer));

        TransferOfferRequest request = new TransferOfferRequest();
        request.setPlayerId(persistedPlayer.getId());
        request.setTeamId(persistedTeam.getId());

        String message = Assert.assertThrows(UnacceptableTransferException.class, () ->
        {
            TransferOfferResponse response = transferService.offerToPLayer(request);
        }).getMessage();
        Assert.assertEquals("The player[id:23] isn't in transfer list.",message);
    }

    @Test
    public void throwUnacceptableTransferException_whenTransferServiceOfferToPlayerMethodCalled_withInsufficientBudget() {

        Team persistedTeam = new Team();
        persistedTeam.setTeamName("Tester Team");
        persistedTeam.setId(1);
        persistedTeam.setBudgetInUSD(2000000);
        when(teamRepository.findById(any(Integer.class))).thenReturn(Optional.of(persistedTeam));

        Team ownerTeam = new Team();
        ownerTeam.setTeamName("Owner Team");
        ownerTeam.setId(11);

        Player persistedPlayer = new Player();
        persistedPlayer.setFirstName("Tester Name");
        persistedPlayer.setId(23);
        persistedPlayer.setMarketValueInUSD(9000000);
        persistedPlayer.setTeam(ownerTeam);
        persistedPlayer.setIsInTransferList(true);
        when(playerRepository.findById(any(Integer.class))).thenReturn(Optional.of(persistedPlayer));

        TransferOfferRequest request = new TransferOfferRequest();
        request.setPlayerId(persistedPlayer.getId());
        request.setTeamId(persistedTeam.getId());

        String message = Assert.assertThrows(UnacceptableTransferException.class, () ->
        {
            TransferOfferResponse response = transferService.offerToPLayer(request);
        }).getMessage();
        Assert.assertEquals("The team[id:1] transfer budget has only 2000000USD. The player[id:23] price is 9000000USD.",message);

    }
}
