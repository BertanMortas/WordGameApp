package com.promet.repository;

import com.promet.repository.entity.Tries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ITriesRepository extends JpaRepository<Tries, Long> {
    Boolean existsByPlayerAuthIdAndGameId(Long playerAuthId,Long gameId);
    @Query(value = "SELECT * FROM tries t WHERE t.game_id = :gameId AND t.player_auth_id = :playerAuthId ORDER BY t.remaining_life ASC LIMIT 1", nativeQuery = true)
    Optional<Tries> findTriesMinLifeByGameAndPlayerId(Long gameId, Long playerAuthId);

    @Query("SELECT MIN(t.remainingLife) FROM Tries t WHERE t.gameId = :gameId AND t.playerAuthId = :playerAuthId")
    Optional<Integer> findMinLifeByGameAndPlayerAuthId(Long gameId, Long playerAuthId);
}
