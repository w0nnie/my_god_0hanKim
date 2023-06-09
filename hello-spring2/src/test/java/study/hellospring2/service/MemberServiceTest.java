package study.hellospring2.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import study.hellospring2.domain.Member;
import study.hellospring2.repository.MemoryMemberRepository;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 순수한 단위테스트 (JVM 내에서 가능한 테스트)가 훨씬더 좋은 테스트 일 확률이 높다.
 * spring 컨테이너 없이 순수한 java로 이루어진 단위 테스트 환경에서 테스트하는법을 더 공부하자.
 */
class MemberServiceTest {

    MemberService service;
    MemoryMemberRepository repository;

    @BeforeEach
    void beforeEach() {
        repository = new MemoryMemberRepository();
        service = new MemberService(repository);
    }

    @AfterEach
    void afterEach() {
        repository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long id = service.join(member);

        //then
        Member findMember = service.findOne(id).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외() {
        //given
        Member member = new Member();
        member.setName("spring");

        Member member1 = new Member();
        member1.setName("spring");

        //when
        service.join(member);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> service.join(member1));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


//        try {
//            service.join(member1);
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }


        //then
    }

    @Test
    void finMembers() {
    }

    @Test
    void findOne() {
    }
}