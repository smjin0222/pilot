package com.estsoft.pilot.api.oauth.controller;

import com.estsoft.pilot.api.oauth.client.EstAuthFeignClient;
import com.estsoft.pilot.api.oauth.dto.OAuth2TokenRequestDto;
import com.estsoft.pilot.api.oauth.dto.OAuth2TokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/oauth")
@RestController
public class OAuth2TokenController {

    private final EstAuthFeignClient estAuthFeignClient;

//    @Value("${estsoft.oauth.client.id}")
//    private String clientId;
//
//    @Value("${estsoft.oauth.client.secret}")
//    private String clientSecret;

    @PostMapping("/login")
    public String login(@RequestBody OAuth2TokenRequestDto requestDto) {
        OAuth2TokenResponseDto responseDto = estAuthFeignClient.getOAuthJwtToken(requestDto);
        return responseDto.getAccess_token();
    }
}