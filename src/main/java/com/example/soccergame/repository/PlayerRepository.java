package com.example.soccergame.repository;

import com.example.soccergame.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer>, PagingAndSortingRepository<Player, Integer> {

    @Query(value="SELECT * FROM \"player\" where \"is_in_transfer_list\" = TRUE", nativeQuery=true)
    List<Player> findAllInTransferList();
}
