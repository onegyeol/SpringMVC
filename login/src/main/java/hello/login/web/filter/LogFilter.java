package hello.login.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log filter doFilter");

        HttpServletRequest httpRequest = (HttpServletRequest) request; //HttpServletRequest의 부모가 ServletRequest임. 다운캐스팅 해줌
        String requestURI = httpRequest.getRequestURI();

        String uuid = UUID.randomUUID().toString(); //요청 구분하기 위해 uuid 사용함. 성능 최적화 위해서 사용.

        try{
            log.info("REQUEST [{}][{}]", uuid, requestURI); //로그 찍고
            chain.doFilter(request, response);// 다음 서블릿 호출. 이 로직을 사용하지 않으면 다음 단계로 진행 안됨.
        }catch(Exception e) {
            throw e;
        } finally{
            log.info("RESPONSE [{}][{}]", uuid, requestURI);
        }
    }

    @Override
    public void destroy() {
        log.info("log filter destroy");
    }
}
