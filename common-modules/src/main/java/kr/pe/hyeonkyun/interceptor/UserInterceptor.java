package kr.pe.hyeonkyun.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.pe.hyeonkyun.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;

@Slf4j
@Component
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String[] paths = request.getRequestURI().split("/");

        if( paths.length > 1 ) {
            long isAuthCount = Arrays.stream(paths).filter(path -> path.contains("auth")).count();
            if(isAuthCount > 0) return true;

            User user = new User();
            user.setId(1L);
            user.setUsername("test");
            user.setPassword("test");
            user.setRole("ROLE_ADMIN");
            request.setAttribute("userContext", user);
        }
        return true;
    }
}
