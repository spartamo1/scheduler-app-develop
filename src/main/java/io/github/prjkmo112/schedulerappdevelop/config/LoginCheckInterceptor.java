package io.github.prjkmo112.schedulerappdevelop.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import io.github.prjkmo112.schedulerappdevelop.exception.ApiException;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Value("${spring.session.key.user-id}")
    private String sessionKeyUserId;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpSession session = request.getSession(false);
        if (session == null)
            throw new ApiException("Unauthorized", HttpStatus.UNAUTHORIZED);

        Long userId = (Long) session.getAttribute(sessionKeyUserId);
        if (userId == null)
            throw new ApiException("Unauthorized", HttpStatus.UNAUTHORIZED);

        return true;
    }

}
