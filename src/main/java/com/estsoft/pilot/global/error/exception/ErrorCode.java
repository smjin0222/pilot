package com.estsoft.pilot.global.error.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // 인증
    MEMBER_NOT_EXISTS(400, "존재하지 않는 회원입니다."),
    HAS_NOT_AUTHORIZATION(401, "권한이 없습니다."),
    NOT_EXISTS_AUTHORIZATION(401, "Authorization Header가 빈값입니다."),
    NOT_VALID_BEARER_GRANT_TYPE(401, "인증 타입이 Bearer 타입이 아닙니다."),
    NOT_VALID_TOKEN(401, "유효하지않은 토큰 입니다."),
    ACCESS_TOKEN_EXPIRED(401, "해당 access token은 만료됐습니다."),

    NOT_ACCESS_TOKEN_TYPE(401, "tokenType이 access token이 아닙니다."),
    REFRESH_TOKEN_EXPIRED(401, "해당 refresh token은 만료됐습니다."),
    REFRESH_TOKEN_NOT_FOUND(400, "해당 refresh token은 존재하지 않습니다."),


    // 게시판
    NOT_EXISTS_BOARD(400, "게시글 정보가 존재하지 않습니다."),
    IS_DELETED_BOARD(400, "삭제 처리 된 게시물입니다."),
    DELETED_BOARD_NOT_REPLY(400, "삭제 처리 된 게시물에는 답글을 달 수 없습니다"),
    NOT_EXISTS_COMMENT(400, "댓글 정보가 존재하지 않습니다,"),
    INVALID_BOARD_ID(400, "수정하려는 board의 id값이 일치하지 않습니다."),
    MISMATCHED_BOARD_COMMENT_ID(400, "수정하려는 comment는 board_id에 해당하는 게시글의 댓글이 아닙니다.");

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private int status;
    private String message;

}
