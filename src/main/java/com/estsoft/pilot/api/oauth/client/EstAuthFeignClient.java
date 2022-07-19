package com.estsoft.pilot.api.oauth.client;

import com.estsoft.pilot.api.oauth.dto.OAuth2TokenRequestDto;
import com.estsoft.pilot.api.oauth.dto.OAuth2TokenResponseDto;
import com.estsoft.pilot.global.error.exception.FeignClientException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "eauth", url = "${estsoft.oauth.server}")
public interface EstAuthFeignClient {

    @PostMapping(value = "/oauth/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    OAuth2TokenResponseDto getOAuthJwtToken(OAuth2TokenRequestDto requestDto) throws FeignClientException;
}
