package io.github.prjkmo112.schedulerappdevelop.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import io.github.prjkmo112.schedulerappdevelop.common.annotation.UserInfo;
import io.github.prjkmo112.schedulerappdevelop.exception.ApiException;

@Component
@RequiredArgsConstructor
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Value("${spring.session.key.user-id}")
    private String sessionKeyUserId;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasParam = parameter.hasParameterAnnotation(UserInfo.class);
        boolean isLong = parameter.getParameterType() == Long.class;

        return hasParam && isLong;
    }

    @Override
    public @Nullable Object resolveArgument(
            MethodParameter parameter,
            @Nullable ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            @Nullable WebDataBinderFactory binderFactory
    ) {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        if (request == null)
            throw new ApiException("HttpServletRequest is null", HttpStatus.INTERNAL_SERVER_ERROR);

        HttpSession session = request.getSession(false);
        if (session == null)
            throw new ApiException("Login Required", HttpStatus.UNAUTHORIZED);

        Long userId = (Long) session.getAttribute(sessionKeyUserId);
        if (userId == null)
            throw new ApiException("Login Required", HttpStatus.UNAUTHORIZED);

        return userId;
    }

}
