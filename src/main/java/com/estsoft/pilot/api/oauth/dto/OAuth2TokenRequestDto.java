package com.estsoft.pilot.api.oauth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class OAuth2TokenRequestDto {
    private String grant_type;
    private String username;
    private String password;
//    private String client_id;
//    private String client_secret;
    private String refresh_token;
}