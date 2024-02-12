package study.playground.springboot.core.api.support.common;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import study.playground.springboot.db.core.repository.UserRepository;

public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    private static final String LOGIN_USER_HEADER = "X-USER-ID";
    private final UserRepository userRepository;

    public LoginUserArgumentResolver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAnnotation = parameter.hasParameterAnnotation(LoginUser.class);
        boolean hasLongType = Long.class.isAssignableFrom(parameter.getParameterType());
        return hasAnnotation && hasLongType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Long loginUserId = Long.parseLong(webRequest.getHeader(LOGIN_USER_HEADER));
        return userRepository.findById(loginUserId)
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 유저 정보입니다."))
                .getId();
    }
}
