package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    public Member login(String loginId, String password){

        /*Optional<Member> findMemberOptional = memberRepository.findByLoginId(loginId);
        Member member = findMemberOptional.get(); //get 통해 값 꺼내옴. 값이 없으면 예외 터짐
        if(member.getPassword().equals(password)) return member;
        else return null;*/

        return memberRepository.findByLoginId(loginId).
                filter(m->m.getPassword().equals(password)).orElse(null);
        //위의 코드와 동일한 기능이지만 Optional 기능 통해 두줄로 줄임
    }
}
