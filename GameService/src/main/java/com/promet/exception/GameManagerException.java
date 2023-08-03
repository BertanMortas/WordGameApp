package com.promet.exception;

import lombok.Getter;

@Getter
public class GameManagerException extends RuntimeException{

    private final ErrorType errorType;

    public GameManagerException(ErrorType errorType, String customMessage) {
        super(customMessage);
        this.errorType = errorType;
    }

    public GameManagerException(ErrorType errorType){
        this.errorType = errorType;
    }
}
