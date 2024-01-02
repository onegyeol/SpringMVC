package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); //static 사용
    private static  long sequence = 0L;

    public Member save(Member member){
        member.setId(++sequence);
        log.info("save : member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId){
        /*List<Member> all = findAll();
        for(Member m : all){
            m.getLoginId().equals(loginId){
                return Optional.of(m);
            }
        }
        return Optional.empty();*/

        return findAll().stream().filter(m -> m.getLoginId().equals(loginId))
                .findFirst(); //위의 코드와 똑같은 처리를 하지만 리스트를 스트림으로 바꿔 코드 길이가 더 짧아짐
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values()); //store 에 저장된 멤버 리스트로 반환
    }

    public void clearStore(){
        store.clear();
    }
}
