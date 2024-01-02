package hello.login.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {
    private Long id; //데이터베이스에 저장되고 관리되는 아이디
    @NotEmpty
    private String loginId; //로그인 아이디
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;

}
