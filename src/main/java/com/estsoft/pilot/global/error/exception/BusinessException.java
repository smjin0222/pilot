package com.estsoft.pilot.global.error.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private int status;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.status = errorCode.getStatus();
    }

}
