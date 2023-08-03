package com.promet.controller;

import com.promet.dto.request.ChangeKeyWordRequestDto;
import com.promet.dto.request.CreateGameRequestDto;
import com.promet.repository.entity.Game;
import com.promet.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.promet.constant.ApiUrls.*;

@RestController
@RequestMapping(GAME)
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class GameController {
    private final GameService gameService;

    @GetMapping(FIND_ALL)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<Game>> findAll(){
        return ResponseEntity.ok(gameService.findAll());
    }

    @DeleteMapping(DELETE_BY_ID+"/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){ // token eklenebilir
        gameService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping(CREATE)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Boolean> createGame(@RequestBody @Valid CreateGameRequestDto dto){
        return ResponseEntity.ok(gameService.createGame(dto));
    }
    @PutMapping(CHANGE_KEYWORD)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<String> changeKeyword(@RequestBody ChangeKeyWordRequestDto dto){
        return ResponseEntity.ok(gameService.changeKeyWord(dto));
    }
}
