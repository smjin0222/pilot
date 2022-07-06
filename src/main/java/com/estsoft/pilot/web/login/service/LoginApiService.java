package com.estsoft.pilot.web.login.service;

import com.estsoft.pilot.domain.member.service.MemberService;
import com.estsoft.pilot.web.login.dto.MemberRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class LoginApiService {

    private final MemberService memberService;
    @Transactional
    public void registerMember(MemberRegisterDto memberRegisterDto) {
        memberService.saveMember(memberRegisterDto.toEntity());
    }
}
