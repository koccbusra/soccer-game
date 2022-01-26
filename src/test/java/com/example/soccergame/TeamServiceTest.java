package com.example.soccergame;

import com.example.soccergame.exception.TeamNotFoundException;
import com.example.soccergame.model.Team;
import com.example.soccergame.repository.TeamRepository;
import com.example.soccergame.request.TeamInfoRequest;
import com.example.soccergame.response.TeamInfoResponse;
import com.example.soccergame.service.TeamService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;

    @Test
    public void validateTeamInfoResponse_whenTeamServiceCreateMethodCalled_withTeamInfoRequest() {

        // Expected objects
        Team persistedTeam = new Team();
        persistedTeam.setTeamName("Tester Team");
        persistedTeam.setId(12345);

        // Mockito expectations
        when(teamRepository.save(any(Team.class))).thenReturn(persistedTeam);

        // Execute the method being tested
        TeamInfoRequest request = new TeamInfoRequest();
        request.setTeamName("Tester Team");

        TeamInfoResponse response = teamService.create(request);

        // Validation
        Assert.assertNotNull(response);
        Assert.assertEquals("Tester Team", response.getTeamName());
        Assert.assertEquals(Integer.valueOf(12345), Integer.valueOf(response.getTeamId()));
    }

    @Test(expected = TeamNotFoundException.class)
    public void throwTeamNotFoundException_whenTeamServiceUpdateMethodCalled_withInvalidTeamId()
    {
        TeamInfoRequest request = new TeamInfoRequest();
        request.setTeamName("Tester Team");

        teamService.update(4578, request);
    }
}
