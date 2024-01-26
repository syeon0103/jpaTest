package jpabook.jpashop.service;

import jpabook.jpashop.Repository.MemberRepository;
import jpabook.jpashop.domain.Member;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //읽기가 많을 경우엔 여기에 넣어두면 됨
//@AllArgsConstructor
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository; //final : 컴파일 시점 체크할 수 있기 때문에 넣는 걸 추천
    //RequiredArgsConstructor
    //파이널 있는 필드만 가지고 생성자 만들어줌
    //생성자에서 끝나는 애들은 final로 잡음


 /*   @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    //생성자 인젝션
    //@Autowired //요즘엔 이거 안 써도 스프링에서 알아서 해줌
    //AllArgsConstructor 이거 사용하면 아래 필요없음
    /*public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    /**
     * 회웍가입
     * @param member
     * @return
     */
    @Transactional //쓰기일 경우에만 따로 설정해줌
    public Long join(Member member) {
        validateDuplicationMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicationMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());

        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);
    }

    //void 대신 member를 반환할 수도 있음
    //커민드와 쿼리 분리하기
}
