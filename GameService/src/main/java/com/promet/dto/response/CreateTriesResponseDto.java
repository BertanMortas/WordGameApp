package com.promet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTriesResponseDto {
    private String guessedWord;
    private String letterInTheRightPlace ;
    private int correctlyGuessedLetters;
    private int correctlyGuessedLettersInTheWrongPlace;
    private int remainingLife;
}
