package com.joongsu.test.domain.repository;

import com.joongsu.test.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface MemberRepository extends JpaRepository<Member, Long> {
}
