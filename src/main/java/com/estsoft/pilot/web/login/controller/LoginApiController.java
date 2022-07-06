package com.estsoft.pilot.web.login.controller;

import com.estsoft.pilot.global.error.exception.BusinessException;
import com.estsoft.pilot.global.error.exception.ErrorCode;
import com.estsoft.pilot.web.login.dto.MemberRegisterDto;
import com.estsoft.pilot.web.login.service.LoginApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
public class LoginApiController {

    private final LoginApiService loginApiService;

    @PostMapping("/join")
    public void join(@Valid @RequestBody MemberRegisterDto memberRegisterDto) {
        if (!Objects.equals(memberRegisterDto.getPassword(), memberRegisterDto.getPassword2())) {
            throw new BusinessException(ErrorCode.MISMATCHED_PASSWORD);
        }
        loginApiService.registerMember(memberRegisterDto);
    }
}

