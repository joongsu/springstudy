package com.joongsu.mock;

import com.joongsu.test.domain.application.MemberService;
import com.joongsu.test.domain.member.Member;
import com.joongsu.test.domain.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MemberServiceTest {

    @Mock   // mock 객체 자동 생성
    private MemberRepository memberRepository;

    @InjectMocks       // 인스턴스 생성 후 Mock 객체 관련 의존관계 자동 주입
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // mock 객체 초기화
    }

    @Test
    @DisplayName("Mock 예제 테스트")
    public void testService() {
        //given
        String name = "홍길동";
        int age = 20;
        String email = "hong@naver.com";
        Member member = Member.builder()
                .name(name)
                .age(age)
                .email(email)
                .build();

        when(memberRepository.findById(1L)).thenReturn(Optional.ofNullable(member));

        //when

        Member foundMember = memberService.findById(1L);
        //then

        // 결과 검증 -> JUnit
        assertEquals(name, foundMember.getName());
        // 결과 검증 -> AssertJ
        assertThat(foundMember.getAge()).isEqualTo(age);

        //메서드 호출 검증
        // memberRepository.findById(1L)가 실제로 1번 호출되었는지 검증
        verify(memberRepository, times(1)).findById(1L);
    }

    /*@Test
    @DisplayName("Mock 예제 테스트")
    public void testService() {

        //given

        // 의존성 객체 모킹
        // 진짜 DB 없이도 memberRepository.findById() 호출을 가짜로 흉내낼 수 있게 된다.
        MemberRepository memberRepository = mock(MemberRepository.class);

        //Member 엔티티 생성
        String name = "홍길동";
        int age = 20;
        String email = "hong@naver.com";
        Member member = Member.builder()
                .name(name)
                .age(age)
                .email(email)
                .build();
        // 메서드 스텁 설정 -> 임의로 생성한 member 리턴
        //findById(1L)가 호출되면 , 이전에 만든 member를 담은 Optional 리턴하라고 지정
        //DB는 없지만 , DB에서 찾은 것 처럼 동작
        when(memberRepository.findById(1L)).thenReturn(Optional.ofNullable(member));

        // 테스트할 객체 생성 -> 모킹한 의존성 객체 주입
        MemberService memberService = new MemberService(memberRepository);
        //when

        Member foundMember = memberService.findById(1L);
        //then

        // 결과 검증 -> JUnit
        assertEquals(name, foundMember.getName());
        // 결과 검증 -> AssertJ
        assertThat(foundMember.getAge()).isEqualTo(age);

        //메서드 호출 검증
        // memberRepository.findById(1L)가 실제로 1번 호출되었는지 검증
        verify(memberRepository, times(1)).findById(1L);
    }*/
}
