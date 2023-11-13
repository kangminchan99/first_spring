package first.first_spring.service;

import first.first_spring.domain.Member;
import first.first_spring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional
public class MemberService {

    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원 가입
     */
    public Long join(Member member){

        long start = System.currentTimeMillis();

        try {
            // 같은 이름이 있는 중복 회원 X
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish -start;
            System.out.println("join = " + timeMs + "ms");

        }


    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).
                ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체회원조회
     */
    public List<Member> findMembers(){
        long start = System.currentTimeMillis();
        try {
            return memberRepository.findAll();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers = " + timeMs + "ms");
        }

    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
