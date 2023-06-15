package com.juniza82.party.component.authorization;


import com.juniza82.party.component.excption.DefaultException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


@RequiredArgsConstructor
@Component
public class AuthorizationHeaderArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(AccountAuthorization.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String authorizationHeader = webRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if (Strings.isBlank(authorizationHeader)) {
            throw new DefaultException("Account 인증 정보가 없습니다.", HttpStatus.UNAUTHORIZED);
        }

        Long accountPk;
        try {
            accountPk = Long.valueOf(authorizationHeader);
        } catch (NumberFormatException e) {
            throw new DefaultException("Authorization Header 포맷이 적합하지 않습니다.", HttpStatus.BAD_REQUEST);
        }
        return accountPk;
    }
}
