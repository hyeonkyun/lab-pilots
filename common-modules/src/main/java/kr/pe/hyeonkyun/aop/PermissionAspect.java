package kr.pe.hyeonkyun.aop;

import kr.pe.hyeonkyun.annotation.CheckPermission;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PermissionAspect {

    @Around("@annotation(checkPermission)")
    public Object checkUserPermission(ProceedingJoinPoint joinPoint, CheckPermission checkPermission) throws Throwable {
        // 권한 이름
        String requiredRole = checkPermission.value();

        // 현재 사용자 정보 조회 (예시)
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            throw new AccessDeniedException("인증되지 않은 사용자입니다.");
        }

        // 권한 검사 (Simple GrantedAuthority 기준)
        boolean hasRole = auth.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(requiredRole));

        if (!hasRole) {
            throw new AccessDeniedException("권한이 없습니다: 필요 권한 = " + requiredRole);
        }

        return joinPoint.proceed();
    }
}
