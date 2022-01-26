package com.example.soccergame.repository;


import com.example.soccergame.model.Player;
import com.example.soccergame.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value="SELECT * FROM \"user\" where \"email_address\" = :emailAddress", nativeQuery=true)
    User findByEmailAddress(String emailAddress);
}
