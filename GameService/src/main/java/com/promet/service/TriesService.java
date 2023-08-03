package com.promet.service;

import com.promet.dto.request.CreateTriesRequestDto;
import com.promet.dto.response.CreateTriesResponseDto;
import com.promet.exception.ErrorType;
import com.promet.exception.GameManagerException;
import com.promet.mapper.ITriesMapper;
import com.promet.repository.ITriesRepository;
import com.promet.repository.entity.Tries;
import com.promet.utility.JwtTokenProvider;
import com.promet.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TriesService extends ServiceManager<Tries, Long> {
    private final ITriesRepository triesRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final GameService gameService;

    public TriesService(ITriesRepository triesRepository, JwtTokenProvider jwtTokenProvider, GameService gameService) {
        super(triesRepository);
        this.triesRepository = triesRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.gameService = gameService;
    }


    public CreateTriesResponseDto createTriesFixed(CreateTriesRequestDto dto) {
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(dto.getToken());
        if (authId.isEmpty()) {
            throw new GameManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<Tries> tries = Optional.ofNullable(ITriesMapper.INSTANCE.toTries(dto));
        if (triesRepository.existsByPlayerAuthIdAndGameId(authId.get(),dto.getGameId())) {
            //tries = triesRepository.findTriesMinLifeByGameAndPlayerId(dto.getGameId(),authId.get());
            Optional<Integer> remainingLife = triesRepository.findMinLifeByGameAndPlayerAuthId(dto.getGameId(),authId.get());
            tries.get().setRemainingLife(remainingLife.get()-1);
            if (remainingLife.get()<=0) {
                throw new GameManagerException(ErrorType.OUT_OF_LIFE);
            }
        }else{
            tries.get().setRemainingLife(tries.get().getRemainingLife() - 1);
        }

        String keyWord = gameService.getKeywordByGameId(dto.getGameId()).toLowerCase();
        if(keyWord.toLowerCase().equals(dto.getTriedWords().toLowerCase()))
            throw new GameManagerException(ErrorType.WIN);
        LinkedHashMap<Integer, Character> keywordIndexMap = new LinkedHashMap<>();
        LinkedHashMap<Integer, Character> triedWordIndexMap = new LinkedHashMap<>();
        for (int i = 0; i < keyWord.length(); i++) {
            char currentChar = keyWord.charAt(i);
            keywordIndexMap.put(i, currentChar);
        }
        for (int i = 0; i < dto.getTriedWords().length(); i++) {
            char currentChar = dto.getTriedWords().toLowerCase().charAt(i);
            triedWordIndexMap.put(i, currentChar);
        }

        StringBuilder result = new StringBuilder();
        Map<Character, Integer> keywordCharCount = new HashMap<>();
        Map<Character, Integer> triedWordCharCount = new HashMap<>();
        for (char keywordChar : keywordIndexMap.values()) {
            keywordCharCount.put(keywordChar, keywordCharCount.getOrDefault(keywordChar, 0) + 1);
        }
        for (char triedWordChar : triedWordIndexMap.values()) {
            triedWordCharCount.put(triedWordChar, triedWordCharCount.getOrDefault(triedWordChar, 0) + 1);
        }

        int correctlyGuessedLetters = 0;
        int correctlyGuessedLettersInTheWrongPlace = 0;

        int loopCount = 0;
        keyWord.length() > dto.getTriedWords().length() ? loopCount=keyWord.length():loopCount=dto.getTriedWords().length();

        for (int i = 0; i < keyWord.length(); i++) {
            char keywordChar = keywordIndexMap.get(i);
            char triedWordChar = i < dto.getTriedWords().length() ? triedWordIndexMap.get(i) : '*';
            if (triedWordChar == keywordChar) {
                correctlyGuessedLetters++;
                result.append(triedWordChar);
            } else {
                if (keywordCharCount.getOrDefault(triedWordChar, 0) > 0) {
                    correctlyGuessedLettersInTheWrongPlace++;
                    keywordCharCount.put(triedWordChar, keywordCharCount.get(triedWordChar) - 1);
                }
                result.append('*');
            }
        }
        tries.get().setPlayerAuthId(authId.get());
        tries.get().setCorrectlyGuessedLetters(correctlyGuessedLetters);
        tries.get().setCorrectlyGuessedLettersInTheWrongPlace(correctlyGuessedLettersInTheWrongPlace);
        // ilk deneme mi yoksa ikinci denememi kontrolü
        // lowercase yapılacak
        // try kelime uzun olursa hata atıyor
        // todo kelime de correct word but worng place de kelime uzunluğuna kadar bakıyor
        //  eğer kelimeden uzun bir deneme olursa o kısmı doğru yazmıyor
        save(tries.get());
        return CreateTriesResponseDto.builder()
                .guessedWord(dto.getTriedWords())
                .letterInTheRightPlace(result.toString())
                .correctlyGuessedLetters(correctlyGuessedLetters)
                .correctlyGuessedLettersInTheWrongPlace(correctlyGuessedLettersInTheWrongPlace)
                .remainingLife(tries.get().getRemainingLife())
                .build();
    }
}
