package com.promet.controller;
import com.promet.dto.request.CreateTriesRequestDto;
import com.promet.dto.response.CreateTriesResponseDto;
import com.promet.service.TriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.promet.constant.ApiUrls.*;

@RestController
@RequestMapping(TRIES)
@RequiredArgsConstructor
public class TriesController {
    private final TriesService triesService;
    @PostMapping(CREATE)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<CreateTriesResponseDto> createTries(@RequestBody CreateTriesRequestDto dto){
        return ResponseEntity.ok(triesService.createTriesFixed(dto));
    }
    @GetMapping("/find-life/{token}/{gameId}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Integer> findRemainingLifeForGames(@PathVariable String token, @PathVariable Long gameId){
        return ResponseEntity.ok(triesService.findRemainingLifeForGames(token, gameId));
    }
}
