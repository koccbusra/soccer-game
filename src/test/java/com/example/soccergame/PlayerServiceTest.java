package com.example.soccergame;

import com.example.soccergame.exception.PlayerNotFoundException;
import com.example.soccergame.model.Player;
import com.example.soccergame.repository.PlayerRepository;
import com.example.soccergame.request.PlayerInfoRequest;
import com.example.soccergame.response.PlayerInfoResponse;
import com.example.soccergame.service.PlayerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    @Test
    public void validatePlayerInfoResponse_whenPlayerServiceCreateMethodCalled_withPlayerInfoRequest() {

        // Expected objects
        Player persistedPlayer = new Player();
        persistedPlayer.setFirstName("Tester Name");
        persistedPlayer.setId(12345);

        // Mockito expectations
        when(playerRepository.save(any(Player.class))).thenReturn(persistedPlayer);

        // Execute the method being tested
        PlayerInfoRequest request = new PlayerInfoRequest();
        request.setFirstName("Tester Name");

        PlayerInfoResponse response = playerService.create(request);

        // Validation
        Assert.assertNotNull(response);
        Assert.assertEquals("Tester Name",response.getFirstName());
        Assert.assertEquals(Integer.valueOf(12345), Integer.valueOf(response.getPlayerId()));
    }

    @Test(expected = PlayerNotFoundException.class)
    public void throwPlayerNotFoundException_whenPlayerServiceDeleteMethodCalled_withInvalidPlayerId() {
        playerService.delete(726);
    }
}
