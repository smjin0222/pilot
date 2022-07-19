package com.estsoft.pilot.domain.member.service;

import com.estsoft.pilot.domain.member.entity.Member;
import com.estsoft.pilot.domain.member.repository.MemberRepository;
import com.estsoft.pilot.global.error.exception.EntityNotFoundException;
import com.estsoft.pilot.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> {
                    throw new EntityNotFoundException(ErrorCode.MEMBER_NOT_EXISTS);
                });
    }
}
