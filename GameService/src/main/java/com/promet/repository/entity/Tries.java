package com.promet.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tries extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long triesId;
    private Long gameId;
    private Long playerAuthId;
    private String triedWords;
    @Builder.Default
    private Integer remainingLife = 20;
    private int correctlyGuessedLetters;
    private int correctlyGuessedLettersInTheWrongPlace;
}
