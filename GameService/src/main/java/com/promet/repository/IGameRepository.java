package com.promet.repository;

import com.promet.repository.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IGameRepository extends JpaRepository<Game, Long> {
    @Query("SELECT g.keyWord FROM Game g WHERE g.gameId = :gameId")
    Optional<String> findKeywordsByGameId(Long gameId);
}
