package com.promet.service;

import com.promet.dto.request.ChangeKeyWordRequestDto;
import com.promet.dto.request.CreateGameRequestDto;
import com.promet.exception.ErrorType;
import com.promet.exception.GameManagerException;
import com.promet.mapper.IGameMapper;
import com.promet.repository.IGameRepository;
import com.promet.repository.annotation.RoleAnnotation;
import com.promet.repository.entity.Game;
import com.promet.utility.JwtTokenProvider;
import com.promet.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService extends ServiceManager<Game, Long>{
    private final IGameRepository gameRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public GameService(IGameRepository gameRepository, JwtTokenProvider jwtTokenProvider) {
        super(gameRepository);
        this.gameRepository = gameRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @RoleAnnotation(newValue = "ADMIN")
    public Boolean createGame(CreateGameRequestDto dto){
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(dto.getToken());
        if (authId.isEmpty()) {
            throw new GameManagerException(ErrorType.INVALID_TOKEN);
        }
        Game game = IGameMapper.INSTANCE.toGame(dto);
        game.setAuthId(authId.get());
        save(game);
        return true;
    }

    public String changeKeyWord(ChangeKeyWordRequestDto dto){
        Optional<Game> game = findById(dto.getGameId());
        if (game.isEmpty()) {
            throw new GameManagerException(ErrorType.GAME_NOT_FOUND);
        }
        game.get().setKeyWord(dto.getKeyWord());
        update(game.get());
        return game.get().getKeyWord();
    }
    public String getKeywordByGameId(Long gameId){
        Optional<String> keyWord = gameRepository.findKeywordsByGameId(gameId);
        if (keyWord.isPresent()) {
            return keyWord.get();
        }else {
            throw new GameManagerException(ErrorType.GAME_NOT_FOUND);
        }

    }
}
