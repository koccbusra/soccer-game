package com.example.soccergame.service;

import com.example.soccergame.exception.EmailOrPasswordIsWrongException;
import com.example.soccergame.exception.SameEmailAddressException;
import com.example.soccergame.exception.UserNotFoundException;
import com.example.soccergame.model.User;
import com.example.soccergame.repository.UserRepository;
import com.example.soccergame.request.TokenInfoRequest;
import com.example.soccergame.request.UserInfoRequest;
import com.example.soccergame.response.TokenInfoResponse;
import com.example.soccergame.response.UserInfoResponse;
import com.google.common.hash.Hashing;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TeamService teamService;

    private UserService(UserRepository userRepository, TeamService teamService){
        this.userRepository = userRepository;
        this.teamService = teamService;
    }

    public UserInfoResponse create(UserInfoRequest request){
        User user = new User();
        user.setEmailAddress(request.getEmailAddress());
        user.setPassword(Hashing.sha256().hashString(request.getPassword(), StandardCharsets.UTF_8).toString());
        user.setTeam(teamService.create(user));
        try {
            return new UserInfoResponse(userRepository.save(user));
        } catch(DataIntegrityViolationException e) {
            throw new SameEmailAddressException("emailAddress[" + request.getEmailAddress() + "] already in use!");
        }
    }

    public UserInfoResponse read(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent())
            throw new UserNotFoundException("Invalid userId:" + userId);

        return new UserInfoResponse(user.get());
    }

    public UserInfoResponse update(Integer userId, UserInfoRequest request) {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent())
            throw new UserNotFoundException("Invalid userId:" + userId);

        if(request.getEmailAddress() != null) user.get().setEmailAddress(request.getEmailAddress());

        return new UserInfoResponse(userRepository.save(user.get()));
    }

    public Boolean delete(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent())
            throw new UserNotFoundException("Invalid userId:" + userId);

        userRepository.delete(user.get());
        return true;
    }
}
