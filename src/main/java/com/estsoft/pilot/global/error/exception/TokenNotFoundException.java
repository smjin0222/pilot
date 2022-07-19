package com.estsoft.pilot.global.error.exception;

public class TokenNotFoundException extends BusinessException {

    public TokenNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

}