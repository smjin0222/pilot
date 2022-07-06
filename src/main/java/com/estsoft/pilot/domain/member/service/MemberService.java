package com.estsoft.pilot.domain.member.service;

import com.estsoft.pilot.domain.member.entity.Member;
import com.estsoft.pilot.domain.member.repository.MemberRepository;
import com.estsoft.pilot.global.error.exception.BusinessException;
import com.estsoft.pilot.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void saveMember(Member member) {
        // 기존 회원인지 체크
        Optional<Member> result = memberRepository.findByEmail(member.getEmail());
        if(result.isPresent()){
            throw new BusinessException(ErrorCode.ALREADY_REGISTERED_MEMBER);
        }

        memberRepository.save(member);
    }
}
