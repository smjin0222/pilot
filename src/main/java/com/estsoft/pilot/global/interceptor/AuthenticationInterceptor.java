package com.estsoft.pilot.global.interceptor;


import com.estsoft.pilot.domain.jwt.constant.GrantType;
import com.estsoft.pilot.domain.jwt.service.TokenManager;
import com.estsoft.pilot.global.error.exception.AuthenticationException;
import com.estsoft.pilot.global.error.exception.ErrorCode;
import com.estsoft.pilot.global.error.exception.NotValidTokenException;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // 토큰 유무 확인
        if(!StringUtils.hasText(authorizationHeader))
            throw new AuthenticationException(ErrorCode.NOT_EXISTS_AUTHORIZATION);

        // Bearer Grant Type 검증
        String[] authorizations = authorizationHeader.split(" ");
        if(authorizations.length < 2 || (!GrantType.BEARRER.getType().equals(authorizations[0])))
            throw new AuthenticationException(ErrorCode.NOT_VALID_BEARER_GRANT_TYPE);

        String accessToken = authorizations[1];

        // 토큰 유효성 검증
        if (!tokenManager.validateToken(accessToken))
            throw new NotValidTokenException(ErrorCode.NOT_VALID_TOKEN);

        Claims claims = tokenManager.getTokenClaims(accessToken);


        // 엑세스 토큰의 만료 시간 검증
        if (tokenManager.isTokenExpired(claims.getExpiration()))
            throw new NotValidTokenException(ErrorCode.ACCESS_TOKEN_EXPIRED);

        return true;
    }
}