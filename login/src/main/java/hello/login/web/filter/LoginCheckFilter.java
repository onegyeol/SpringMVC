package hello.login.web.filter;

import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] whiteList={"/", "/members/add", "/login", "/logout", "/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            log.info("인증 체크 필터 시작{}", requestURI);

            if (isLoginCheckPath(requestURI)) { //whitelist면 바로 doFilter로 넘어감
                log.info("인증 체크 로직 실행 {}", requestURI);
                HttpSession session = httpRequest.getSession(false);
                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) { //세션이 없거나 세션 데이터가 없다 == 로그인 안함
                    log.info("미인증 사용자 요청 {}", requestURI);
                    //로그인으로 redirect
                    httpResponse.sendRedirect("/login?redirectURL=" + requestURI); //로그인했으면 requestURI로 돌아가게 해줌.
                    return; //리다이렉트하고 끝. 다음 서블릿으로 넘어가지 않음.
                }
            }
            chain.doFilter(request, response);
        }catch(Exception e){
            throw e; //톰캣까지 예외를 보내줘야 함.
        }finally{
            log.info("인증 체크 필터 종료 {}", requestURI);
        }
    }

    /**
     * 화이트 리스트의 경우 인증체크하지 않음
     */
    private boolean isLoginCheckPath(String requestURI){ //화이트 리스트에 들지 않으면 패쓰
        return !PatternMatchUtils.simpleMatch(whiteList, requestURI);
    }

}
