package com.joongsu.test.domain.application;

import com.joongsu.test.domain.member.Member;
import com.joongsu.test.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;


    public Member findById(Long memberID) {

        return memberRepository.findById(memberID).orElseThrow();
    }
}
